import datasource.Supplier;
import models.Billing;
import models.Coin;
import models.Product;
import models.VendingMachine;
import utils.VendingMachineFactory;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Concurrent Vending Machine");
        VendingMachine vm = VendingMachineFactory.createVendingMachine();
        Supplier biscuitSupplier = new Supplier(vm);

        normalPurchase(vm);
        //moreQuantitySelected(vm);
        //insufficientAmountForPurchase(vm);
        //buyingWithoutPaying(vm);
        //insufficientFunds(vm);
        //normalSupplier(vm, biscuitSupplier);
        //customerInProgress(vm, biscuitSupplier);
    }

    private static void normalPurchase(VendingMachine vm) {
        //1.Available
        Set<Product> availableProducts = vm.getAvailableProducts();
        Iterator<Product> itr = availableProducts.iterator();
        System.out.println("Available : " + vm.getAvailableProducts());

        //2.Selection
        List<Product> selectedProducts = new ArrayList<>();
        selectedProducts.add(itr.next());
        selectedProducts.add(itr.next());
        List<Integer> quantity = Arrays.asList(1, 2);
        System.out.println("Selected : " + selectedProducts);
        System.out.println("Quantity : " + quantity);
        System.out.println("Total Price : " + vm.selectProducts(selectedProducts, quantity));

        //3.Pay
        List<Coin> cash = new ArrayList<Coin>() {{
            add(Coin.FIFTY);
            add(Coin.TEN);
        }};
        vm.pay(cash);

        //4.Buy
        Billing bill = vm.buy();
        System.out.println(bill);
    }

    private static void insufficientAmountForPurchase(VendingMachine vm) {
        //1.Available
        Set<Product> availableProducts = vm.getAvailableProducts();
        Iterator<Product> itr = availableProducts.iterator();
        System.out.println("Available : " + vm.getAvailableProducts());

        //2.Selection
        List<Product> selectedProducts = new ArrayList<>();
        selectedProducts.add(itr.next());
        selectedProducts.add(itr.next());
        List<Integer> quantity = Arrays.asList(1, 2);
        System.out.println("Selected : " + selectedProducts);
        System.out.println("Quantity : " + quantity);
        System.out.println("Total Price : " + vm.selectProducts(selectedProducts, quantity));

        //3.Pay
        List<Coin> cash = new ArrayList<Coin>() {{
            add(Coin.TWENTY);
            add(Coin.TEN);
        }};
        vm.pay(cash);
    }

    private static void moreQuantitySelected(VendingMachine vm) {
        //1.Available
        Set<Product> availableProducts = vm.getAvailableProducts();
        Iterator<Product> itr = availableProducts.iterator();
        System.out.println("Available : " + vm.getAvailableProducts());

        //2.Selection
        List<Product> selectedProducts = new ArrayList<>();
        selectedProducts.add(itr.next());
        selectedProducts.add(itr.next());
        List<Integer> quantity = Arrays.asList(1, 8);
        System.out.println("Selected : " + selectedProducts);
        System.out.println("Quantity : " + quantity);
        System.out.println("Total Price : " + vm.selectProducts(selectedProducts, quantity));
    }

    private static void buyingWithoutPaying(VendingMachine vm) {
        //1.Available
        Set<Product> availableProducts = vm.getAvailableProducts();
        Iterator<Product> itr = availableProducts.iterator();
        System.out.println("Available : " + vm.getAvailableProducts());

        //2.Selection
        List<Product> selectedProducts = new ArrayList<>();
        selectedProducts.add(itr.next());
        selectedProducts.add(itr.next());
        List<Integer> quantity = Arrays.asList(1, 2);
        System.out.println("Selected : " + selectedProducts);
        System.out.println("Quantity : " + quantity);
        System.out.println("Total Price : " + vm.selectProducts(selectedProducts, quantity));

        //4.Buy
        Billing bill = vm.buy();
        System.out.println(bill);
    }

    private static void insufficientFunds(VendingMachine vm) {
        //Note change default cash
        //1.Available
        Set<Product> availableProducts = vm.getAvailableProducts();
        Iterator<Product> itr = availableProducts.iterator();
        System.out.println("Available : " + vm.getAvailableProducts());

        //2.Selection
        List<Product> selectedProducts = new ArrayList<>();
        selectedProducts.add(itr.next());
        selectedProducts.add(itr.next());
        List<Integer> quantity = Arrays.asList(1, 2);
        System.out.println("Selected : " + selectedProducts);
        System.out.println("Quantity : " + quantity);
        System.out.println("Total Price : " + vm.selectProducts(selectedProducts, quantity));

        //3.Pay
        List<Coin> cash = new ArrayList<Coin>() {{
            add(Coin.FIFTY);
            add(Coin.TWENTY);
        }};
        vm.pay(cash);

        //4.Buy
        Billing bill = vm.buy();
        System.out.println(bill);
    }

    private static void normalSupplier(VendingMachine vm, Supplier biscuitSupplier) {
        //1. Add Product
        System.out.println("Before: " + vm.getAvailableProducts());
        Product p = new Product("KIT-KAT", 18);
        biscuitSupplier.addProduct(p);
        System.out.println("After: " + vm.getAvailableProducts());

        //2. Change Quantity
        biscuitSupplier.changeQuantity(p, 4);

        //3. Set price
        biscuitSupplier.setPrice(p, new Product("KIT-KAT", 19));
        System.out.println("After Price : " + vm.getAvailableProducts());
    }

    private static void customerInProgress(VendingMachine vm, Supplier biscuitSupplier) {
        //1.Available
        Set<Product> availableProducts = vm.getAvailableProducts();
        Iterator<Product> itr = availableProducts.iterator();
        System.out.println("Available : " + vm.getAvailableProducts());

        //2.Selection
        List<Product> selectedProducts = new ArrayList<>();
        selectedProducts.add(itr.next());
        selectedProducts.add(itr.next());
        List<Integer> quantity = Arrays.asList(1, 2);
        System.out.println("Selected : " + selectedProducts);
        System.out.println("Quantity : " + quantity);
        System.out.println("Total Price : " + vm.selectProducts(selectedProducts, quantity));

        //3.Pay
        List<Coin> cash = new ArrayList<Coin>() {{
            add(Coin.FIFTY);
            add(Coin.TWENTY);
        }};
        vm.pay(cash);

        biscuitSupplier.changeQuantity(availableProducts.iterator().next(), 4);
    }
}
