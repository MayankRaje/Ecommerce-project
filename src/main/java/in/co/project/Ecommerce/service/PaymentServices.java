package in.co.project.Ecommerce.service;

import com.stripe.exception.StripeException;

public interface PaymentServices {
    String makePayments(String orderId,Long amount) throws StripeException;
}
