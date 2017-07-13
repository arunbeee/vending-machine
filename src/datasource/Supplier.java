package datasource;

import models.Product;
import models.SupplierDemands;
import models.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class Supplier implements SupplierDemands {
    private List<VendingMachine> vm = new ArrayList<>();

    private VendingMachine currentVendingMachine;

    public Supplier(VendingMachine currVM) {
        setCurrentVendingMachine(currVM);
        vm.add(currVM);
    }

    public void setCurrentVendingMachine(VendingMachine currentVendingMachine) {
        this.currentVendingMachine = currentVendingMachine;
    }

    @Override
    public void addProduct(Product prod) {
        currentVendingMachine.addProduct(prod);
    }

    @Override
    public void changeQuantity(Product prod, Integer quantity) {
        currentVendingMachine.changeQuantity(prod, quantity);
    }

    @Override
    public void setPrice(Product oldProd, Product newProduct) {
        currentVendingMachine.setPrice(oldProd, newProduct);
    }

    @Override
    public void reset() {
        currentVendingMachine.reset();
    }
}
