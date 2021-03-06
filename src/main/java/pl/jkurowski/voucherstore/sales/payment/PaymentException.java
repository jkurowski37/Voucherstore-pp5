package pl.jkurowski.voucherstore.sales.payment;

import pl.jkurowski.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}
