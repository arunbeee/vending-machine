package models;

import java.util.List;

public class Billing {
    private Product p;

    private List<Coin> change;

    public Billing(Product p, List<Coin> change) {
        this.p = p;
        this.change = change;
    }

    public Product getP() {
        return p;
    }

    public List<Coin> getChange() {
        return change;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "p=" + p +
                ", change=" + change +
                '}';
    }
}
