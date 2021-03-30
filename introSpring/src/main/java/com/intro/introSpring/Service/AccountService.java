package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Account;
import com.intro.introSpring.Exceptions.NotEnoughMoneyException;
import java.util.List;

public interface AccountService {
    List<Account> getAll();
    Account getById(Long id);
    Account save(Account account) throws NotEnoughMoneyException;
    Account updateById(Account account, Long id);
    void deleteById(Long id);
}
