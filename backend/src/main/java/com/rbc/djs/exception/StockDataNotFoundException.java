package com.rbc.djs.exception;

public class StockDataNotFoundException extends NotFoundException {

    public StockDataNotFoundException(String stockSymbol) {
        super("Stock data not found with symbol " + stockSymbol);
    }

}
