package pl.jkurowski.voucherstore.sales;

public interface Inventory {
    boolean isAvailable(String productId);
}
