package utils;

import datasource.ConcurrentVendingMachine;
import models.VendingMachine;

public class VendingMachineFactory {
    public static VendingMachine createVendingMachine() {
        return ConcurrentVendingMachine.getInstance();
    }
}

