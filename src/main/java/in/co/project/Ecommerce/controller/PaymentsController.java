package in.co.project.Ecommerce.controller;

import com.stripe.exception.StripeException;
import in.co.project.Ecommerce.dto.PaymentsRequestDto;
import in.co.project.Ecommerce.service.PaymentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsController {
    PaymentServices paymentServices;

    public PaymentsController(PaymentServices paymentServices) {
        this.paymentServices = paymentServices;
    }

    @PostMapping("/payments")
    ResponseEntity<String> createPaymentsLink(@RequestBody PaymentsRequestDto paymentsRequestDto) throws StripeException {
        String paymentLink=paymentServices.makePayments(paymentsRequestDto.getOrderId(),paymentsRequestDto.getAmount());
        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
    }
    @PostMapping("/webhook")
    public void handleWebhook() {
        System.out.println("Webhook revecied here");
        return;
    }
}
