package models;

import java.util.List;
import java.util.Set;

public interface VendingMachine extends SupplierDemands {
    Set<Product> getAvailableProducts();

    int selectProducts(List<Product> products, List<Integer> quantity);

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
