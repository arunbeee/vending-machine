package datasource;

import models.*;
import states.CoinInsertedState;
import states.DispensingState;
import states.EmptyState;
import states.NoCoinInsertedState;

import java.util.List;

public class ConcurrentVendingMachine implements VendingMachine {
    private Inventory<Coin> cashInventory = new Inventory<Coin>();
    private Inventory<Product> productInventory = new Inventory<Product>();

    //State pattern for dispensing cash
    State coinInsertedState = new CoinInsertedState(this);
    State emptyState = new EmptyState(this);
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

    }

    @Override
    public void changeQuantity(Product prod) {

    }

    @Override
    public void setPrice(Product prod) {

    }

    @Override
    public void reset() {

    }

    //Vending machine defaults
    @Override
    public List<Product> getAvailableProducts() {
        return null;
    }

    @Override
    public int selectProducts(List<String> name, List<Integer> quantity) {
        return 0;
    }

    @Override
    public void pay(List<Coin> cash) {
        machineState.insertCoin();
    }

    @Override
    public Billing buy() {
        machineState.pressButton();
        calculateChange();
        return null;
    }

    private List<Coin> calculateChange() {
        machineState.dispense();
        return null;
    }

    @Override
    public List<Coin> refund() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
