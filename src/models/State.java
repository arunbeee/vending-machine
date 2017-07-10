package models;

public interface State {
    void insertCoin() throws RuntimeException;

    void pressButton() throws RuntimeException;

    void dispense() throws RuntimeException;
}
