package models;

import java.util.List;

public class Billing {
    private List<Product> selectedProducts;

    private List<Coin> change;

    public Billing(List<Product> selectedProducts, List<Coin> change) {
        this.selectedProducts = selectedProducts;
        this.change = change;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public List<Coin> getChange() {
        return change;
    }

    public void setChange(List<Coin> change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "Bill {" +
                "selectedProducts=" + selectedProducts +
                ", change=" + change +
                '}';
    }
}
