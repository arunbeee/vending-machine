package states;

import models.State;
import models.VendingMachine;
import utils.CustomException;

public class CoinInsertedState implements State {
    VendingMachine machine = null;

    public CoinInsertedState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() throws RuntimeException {
        throw new RuntimeException(CustomException.COIN_INSERTED.getMessage());
    }

    public void dispense() throws RuntimeException {
        throw new RuntimeException(CustomException.PAYMENT_IN_PROGRESS.getMessage());
    }

    public void pressButton() throws RuntimeException {
        machine.setMachineState(machine.getDispensingState());
    }

    public void alter() throws RuntimeException {
        throw new RuntimeException(CustomException.PAYMENT_IN_PROGRESS.getMessage());
    }
}

