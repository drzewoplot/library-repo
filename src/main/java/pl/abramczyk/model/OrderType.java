package pl.abramczyk.model;

public enum OrderType implements Comparable<OrderType> {
    DOSTEPNA(1), WYPOZYCZONA(2);

    private int index;

    OrderType(int index) {
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
