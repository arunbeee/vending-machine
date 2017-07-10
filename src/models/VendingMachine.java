package models;

import java.util.List;

public interface VendingMachine extends SupplierDemands {
    List<Product> getAvailableProducts();

    int selectProducts(List<String> name, List<Integer> quantity);

    void pay(List<Coin> cash);

    Billing buy();

    List<Coin> refund();

    boolean isEmpty();

    void setMachineState(State machineState);

    State getNoCoinInsertedState();

    State getCoinInsertedState();

    State getDispensingState();

    State getMachineState();
}
