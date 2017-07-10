package models;

public interface SupplierDemands {
    void addProduct(Product prod);

    void changeQuantity(Product prod);

    void setPrice(Product prod);

    void reset();
}
