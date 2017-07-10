package utils;

public enum CustomException {
    SOLD_OUT("Product sold out"),
    INSUFFICIENT_FUNDS("Not enough change in the machine"),
    PENDING_PAYMENT("Pending payment for the product"),
    COIN_INSERTED("Coin is already inserted"),
    CANNOT_PROCESS("No item selected"),
    PAYMENT_IN_PROGRESS("Customer payment in progress for the selected item('s)");

    private String message;

    CustomException(String string) {
        this.message = string;
    }

    public String getMessage() {
        return message;
    }
}
