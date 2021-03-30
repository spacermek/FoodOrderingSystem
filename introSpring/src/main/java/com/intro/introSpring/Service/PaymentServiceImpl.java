package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Account;
import com.intro.introSpring.Entity.Payment;
import com.intro.introSpring.Enum.Role;
import com.intro.introSpring.Enum.Status;
import com.intro.introSpring.Exceptions.ResourceNotFoundException;
import com.intro.introSpring.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountService accountService;
    private final OrderService orderService;


    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, AccountService accountService, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find payment with id ", id));
    }

    @Override
    public Payment save(Payment payment) {
        Account account1 = accountService.getById(payment.getAccountFrom().getId());
        Account account2 = accountService.getById(payment.getAccountTo().getId());
        if (account1.getUserId().getUserRole().getName().equals(Role.Client) &&
        account2.getUserId().getUserRole().getName().equals(Role.Supplier) &&
        account1.getBalance() >= orderService.getById(payment.getOrderId().getId()).getTotal()) {
            account1.setBalance(account1.getBalance() - orderService.getById(payment.getOrderId().getId()).getTotal());
            account2.setBalance(account2.getBalance() + orderService.getById(payment.getOrderId().getId()).getTotal());
            payment.setStatus(Status.OK);
        }
        else payment.setStatus(Status.FAILED);
        return paymentRepository.save(payment);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment updateById(Payment payment, Long id) {
        return paymentRepository.findById(id)
                .map(newPayment -> {
                    newPayment.setAccountTo(payment.getAccountTo());
                    newPayment.setAccountFrom(payment.getAccountFrom());
                    newPayment.setStatus(payment.getStatus());
                    newPayment.setOrderId(payment.getOrderId());
                    newPayment.setPaymentDate((payment.getPaymentDate()));
                    return paymentRepository.save(newPayment);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find payment with id ", id));
    }
}
