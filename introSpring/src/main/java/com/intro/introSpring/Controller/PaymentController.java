package com.intro.introSpring.Controller;

import com.intro.introSpring.Entity.Payment;
import com.intro.introSpring.Exceptions.NotEnoughMoneyException;
import com.intro.introSpring.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAll();
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentsById(@PathVariable Long paymentId) {
        return paymentService.getById(paymentId);
    }

    @PostMapping
    public Payment savePayments(@RequestBody Payment payment) throws NotEnoughMoneyException {
        return paymentService.save(payment);
    }

    @PutMapping("/{paymentId}")
    public Payment updatePayments(@RequestBody Payment payment, @PathVariable Long paymentId) {
        return paymentService.updateById(payment, paymentId);
    }

    @DeleteMapping("/{paymentId}")
    public void deletePayments(@PathVariable Long paymentId) {
        paymentService.deleteById(paymentId);
    }
}
