package models;

public class ChangePair {
    private Coin coin;

    private Integer position;

    public ChangePair(Coin denomination, Integer position) {
        this.coin = denomination;
        this.position = position;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
