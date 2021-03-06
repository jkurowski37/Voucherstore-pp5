package pl.jkurowski.voucherstore.sales.exceptions;

public class NotEnoughProductsException extends IllegalStateException {
    public NotEnoughProductsException() {
        super("There is not enough products available");
    }
}
