package com.omnius.challenges.extractors.qtyuom.utils;

public class Pair<QTY, UOM> {
    private QTY first;
    private UOM second;

    public Pair(QTY first, UOM second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(QTY first) {
        this.first = first;
    }

    public void setSecond(UOM second) {
        this.second = second;
    }

    public QTY getFirst() {
        return first;
    }

    public UOM getSecond() {
        return second;
    }
}