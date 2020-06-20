package ua.shuba.stocks.exception;

public class StockException extends RuntimeException {
    public StockException(String message) {
        super(message);
    }

    public StockException(String message, Throwable cause) {
        super(message, cause);
    }
}
