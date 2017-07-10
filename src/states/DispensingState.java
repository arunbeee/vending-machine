package states;

import models.State;
import models.VendingMachine;
import utils.CustomException;

public class DispensingState implements State {
    VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() throws RuntimeException {
        throw new RuntimeException(CustomException.PAYMENT_IN_PROGRESS.getMessage());
    }

    public void pressButton() throws RuntimeException {
        throw new RuntimeException(CustomException.PAYMENT_IN_PROGRESS.getMessage());
    }

    public void dispense() throws RuntimeException {
        machine.setMachineState(machine.getNoCoinInsertedState());
    }
}
