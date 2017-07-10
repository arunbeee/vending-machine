package models;

public enum Coin {
    TEN(10),
    FIVE(5),
    ONE(1),
    TWO(2),
    TWENTY(20),
    FIFTY(50);

    private int denomination;

    Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }

    @Override
    public String toString() {
        return "" + denomination + "";
    }
}
