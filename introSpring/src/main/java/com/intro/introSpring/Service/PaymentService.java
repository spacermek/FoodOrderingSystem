package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Payment;
import com.intro.introSpring.Exceptions.NotEnoughMoneyException;

import java.util.List;

public interface PaymentService {
    List<Payment> getAll();
    Payment getById(Long id);
    Payment save(Payment payment) throws NotEnoughMoneyException;
    void deleteById(Long id);
    Payment updateById(Payment payment, Long id);
}
