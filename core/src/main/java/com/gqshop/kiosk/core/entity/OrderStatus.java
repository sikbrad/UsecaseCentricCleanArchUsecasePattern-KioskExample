package com.gqshop.kiosk.core.entity;

public enum OrderStatus {
	ORDER_CREATED(0),
	ORDER_RECEIVED(10),
	COOKING_STARTED(20),
	COOKING_DONE(30),
	FOOD_TAKEN(40);

    private int numVal;

    OrderStatus(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
