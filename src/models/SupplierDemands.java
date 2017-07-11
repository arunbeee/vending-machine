package models;

public interface SupplierDemands {
    void addProduct(Product prod);

    void changeQuantity(Product prod, Integer quantity);

    void setPrice(Product oldProd, Product newProduct);

    void reset();
}
