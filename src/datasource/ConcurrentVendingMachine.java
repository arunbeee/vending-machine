package datasource;

import models.*;
import states.CoinInsertedState;
import states.DispensingState;
import states.NoCoinInsertedState;
import utils.CustomException;

import java.util.*;

public class ConcurrentVendingMachine implements VendingMachine {
    //Inventory
    private Inventory<Coin> cashInventory = new Inventory<>();
    private Inventory<Product> productInventory = new Inventory<>();

    //Consumer values
    private List<Product> selectedProducts = new ArrayList<>();
    private int selectedProductsPrice;
    private int totalAmountCollected;

    //State pattern for dispensing cash
    State coinInsertedState = new CoinInsertedState(this);
    State noCoinInsertedState = new NoCoinInsertedState(this);
    State dispensingState = new DispensingState(this);
    State machineState = null;

    private static ConcurrentVendingMachine sInstance = null;

    public static ConcurrentVendingMachine getInstance() {
        if (sInstance == null) {
            sInstance = new ConcurrentVendingMachine();
        }
        return sInstance;
    }

    private ConcurrentVendingMachine() {
        machineState = noCoinInsertedState;
        initialize();
    }

    //Dummy initialization
    private void initialize() {
        for (Coin c : Coin.values()) {
            cashInventory.put(c, 3);
        }
        productInventory.put(new Product("PEPSI", 25), 5);
        productInventory.put(new Product("COKE", 22), 4);
        productInventory.put(new Product("COOKIE", 15), 2);
        productInventory.put(new Product("WATER", 18), 5);
        productInventory.put(new Product("SODA", 35), 8);
    }

    //Supplier demands
    @Override
    public void addProduct(Product prod) {
        productInventory.add(prod);
    }

    @Override
    public void changeQuantity(Product prod, Integer quantity) {
        machineState.alter();
        productInventory.put(prod, quantity);
    }

    @Override
    public void setPrice(Product oldProduct, Product newProduct) {
        machineState.alter();
        productInventory.removeAndAdd(oldProduct, newProduct);
    }

    @Override
    public void reset() {
        machineState.alter();
        productInventory.clear();
        cashInventory.clear();
    }

    private void clearPurchase() {
        selectedProducts = new ArrayList<>();
        totalAmountCollected = selectedProductsPrice = 0;
        machineState = noCoinInsertedState;
    }

    //Vending machine defaults
    @Override
    public Set<Product> getAvailableProducts() {
        return new HashSet<>(productInventory.getItems());
    }

    @Override
    public int selectProducts(List<Product> products, List<Integer> quantities) {
        selectedProductsPrice = 0;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            Integer availableQuantity = productInventory.getQuantity(p);
            if (availableQuantity < quantities.get(i)) {
                throw new RuntimeException(CustomException.SOLD_OUT.getMessage());
            }
            for (int j = 0; j < quantities.get(i); j++) {
                selectedProducts.add(products.get(i));
            }
            selectedProductsPrice += p.getPrice() * quantities.get(i);
        }
        return selectedProductsPrice;
    }

    @Override
    public void pay(List<Coin> cash) {
        machineState.insertCoin();
        totalAmountCollected = cash.stream().mapToInt(Coin::getDenomination).sum();
        if (totalAmountCollected - selectedProductsPrice < 0) {
            throw new RuntimeException(CustomException.PENDING_PAYMENT.getMessage());
        }
        addCashInventory(cash);
    }

    private void addCashInventory(List<Coin> cash) {
        for (Coin c : cash) {
            cashInventory.add(c);
        }
    }

    private void deductCashInventory(List<Coin> cash) {
        for (Coin c : cash) {
            cashInventory.deduct(c);
        }
    }

    @Override
    public Billing buy() {
        machineState.pressButton();
        List<Coin> change = calculateChange(totalAmountCollected - selectedProductsPrice);
        machineState.dispense();
        deductCashInventory(change);
        Billing bill = new Billing(selectedProducts, change);
        clearPurchase();
        return bill;
    }

    public List<Coin> calculateChange(int changeAmount) {
        /* MIN Coin change DP
           limitation : Works for unlimited coins.
           In case of limited coins, even though solution exists, sometimes algorithm returns no solution.
           Sol : Take out the bigger element and re-run the DP
         */
        Map<Coin, Integer> changeMap = new HashMap<>();
        List<Coin> changeList = new ArrayList<>();
        if (changeAmount <= 0) {
            return changeList;
        }

        Map<Integer, ChangePair> coinCountMap = new HashMap<>();
        Integer[] minCoins = new Integer[changeAmount + 1];
        minCoins[0] = 0;
        for (int i = 1; i <= changeAmount; i++) {
            minCoins[i] = Integer.MAX_VALUE;
            for (Coin c : cashInventory.getItems()) {
                if (i - c.getDenomination() >= 0 && minCoins[i - c.getDenomination()] < Integer.MAX_VALUE
                        && minCoins[i] > minCoins[i - c.getDenomination()] + 1) {
                    minCoins[i] = minCoins[i - c.getDenomination()] + 1;
                    coinCountMap.put(i, new ChangePair(c, i - c.getDenomination()));
                }
            }
        }
        if (minCoins[changeAmount] < Integer.MAX_VALUE) {
            Integer start = changeAmount;
            while (start > 0) {
                Integer count = 1;
                ChangePair pair = coinCountMap.get(start);
                if (changeMap.containsKey(pair.getCoin())) {
                    count += changeMap.get(pair.getCoin());
                }
                if (cashInventory.getQuantity(pair.getCoin()) < count) {
                    throw new RuntimeException(CustomException.INSUFFICIENT_FUNDS.getMessage());
                }
                changeMap.put(pair.getCoin(), count);
                changeList.add(pair.getCoin());
                start = pair.getPosition();
            }
        } else {
            throw new RuntimeException(CustomException.INSUFFICIENT_FUNDS.getMessage());
        }
        return changeList;
    }

    @Override
    public List<Coin> refund() {
        List<Coin> refund = calculateChange(totalAmountCollected);
        deductCashInventory(refund);
        clearPurchase();
        return refund;
    }

    @Override
    public boolean isEmpty() {
        return productInventory.getItems().size() == 0;
    }

    @Override
    public State getNoCoinInsertedState() {
        return noCoinInsertedState;
    }

    @Override
    public State getDispensingState() {
        return dispensingState;
    }

    @Override
    public State getCoinInsertedState() {
        return coinInsertedState;
    }

    @Override
    public State getMachineState() {
        return machineState;
    }

    @Override
    public void setMachineState(State machineState) {
        this.machineState = machineState;
    }
}
