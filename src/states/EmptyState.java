package states;

import models.State;
import models.VendingMachine;
import utils.CustomException;

public class EmptyState implements State {
    VendingMachine machine;

    public EmptyState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() throws RuntimeException {
        throw new RuntimeException(CustomException.CANNOT_PROCESS.getMessage());
    }

    public void pressButton() throws RuntimeException {
        throw new RuntimeException(CustomException.CANNOT_PROCESS.getMessage());
    }

    public void dispense() throws RuntimeException {
        throw new RuntimeException(CustomException.CANNOT_PROCESS.getMessage());
    }
}
