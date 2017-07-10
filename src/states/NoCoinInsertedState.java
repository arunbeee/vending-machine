package states;

import models.State;
import models.VendingMachine;
import utils.CustomException;

public class NoCoinInsertedState implements State {
    VendingMachine machine;

    public NoCoinInsertedState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() throws RuntimeException {
        if (!machine.isEmpty()) {
            machine.setMachineState(machine.getCoinInsertedState());
        } else {
            throw new RuntimeException(CustomException.SOLD_OUT.getMessage());
        }
    }

    public void pressButton() throws RuntimeException {
        throw new RuntimeException(CustomException.PENDING_PAYMENT.getMessage());
    }

    public void dispense() throws RuntimeException {
        throw new RuntimeException(CustomException.CANNOT_PROCESS.getMessage());
    }
}