package in.co.project.Ecommerce.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGatewayImpl implements PaymentServices{

    @Override
    public String makePayments(String orderId, Long amount) throws StripeException {
        Stripe.apiKey ="sk_test_51Qg21I05HIQSOm86BuaeJsU5F2fdZeNUaET5d1R6V2ari6EZPaOdGVq1xXNomrytkFj4ngqyu7Ah73D0hIsdOWOl00VBqll2Cl";

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(orderId).build()
                        )
                        .build();

        Price price = Price.create(params);

  //      2) create payment link
        //Stripe.apiKey ="sk_test_51Qg21I05HIQSOm86BuaeJsU5F2fdZeNUaET5d1R6V2ari6EZPaOdGVq1xXNomrytkFj4ngqyu7Ah73D0hIsdOWOl00VBqll2Cl";

        PaymentLinkCreateParams linkparams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(linkparams);
        return paymentLink.getUrl();

    }
}
