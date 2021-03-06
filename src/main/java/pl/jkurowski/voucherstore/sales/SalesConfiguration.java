package pl.jkurowski.voucherstore.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jkurowski.payu.http.JavaHttpPayUApiClient;
import pl.jkurowski.payu.PayU;
import pl.jkurowski.payu.PayUCredentials;
import pl.jkurowski.voucherstore.productcatalog.ProductCatalogFacade;
import pl.jkurowski.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.jkurowski.voucherstore.sales.offer.OfferMaker;
import pl.jkurowski.voucherstore.sales.ordering.ReservationRepository;
import pl.jkurowski.voucherstore.sales.payment.PayUPaymentGateway;
import pl.jkurowski.voucherstore.sales.payment.PaymentGateway;
import pl.jkurowski.voucherstore.sales.product.ProductCatalogProductDetailsProvider;
import pl.jkurowski.voucherstore.sales.product.ProductDetailsProvider;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(ProductCatalogFacade productCatalogFacade, OfferMaker offerMaker, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        return new SalesFacade(
                productCatalogFacade,
                new InMemoryBasketStorage(),
                () -> "customer_1",
                (productId) -> true,
                offerMaker,
                paymentGateway,
                reservationRepository);
    }

    @Bean
    PaymentGateway payUPaymentGateway() {
        return new PayUPaymentGateway(new PayU(
                PayUCredentials.productionOfEnv(),
                new JavaHttpPayUApiClient()
        ));
    }

    @Bean
    OfferMaker offerMaker(ProductDetailsProvider productDetailsProvider) {
        return new OfferMaker(productDetailsProvider);
    }

    @Bean
    ProductDetailsProvider productDetailsProvider(ProductCatalogFacade productCatalogFacade) {
        return new ProductCatalogProductDetailsProvider(productCatalogFacade);
    }
}
