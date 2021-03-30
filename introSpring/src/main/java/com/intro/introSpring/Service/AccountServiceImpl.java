package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Account;
import com.intro.introSpring.Exceptions.NotEnoughMoneyException;
import com.intro.introSpring.Exceptions.ResourceNotFoundException;
import com.intro.introSpring.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find account with id ", id));
    }

    @Override
    public Account save(Account account) throws NotEnoughMoneyException {
        if (account.getBalance() < 0) throw new NotEnoughMoneyException("Not enough money in account!");
        return accountRepository.save(account);
    }

    @Override
    public Account updateById(Account account, Long id) {
        return accountRepository.findById(id)
                .map(newAccount -> {
                    newAccount.setAccountNumber(account.getAccountNumber());
                    newAccount.setBalance(account.getBalance());
                    newAccount.setUserId(account.getUserId());
                    newAccount.setCurrency(account.getCurrency());
                    return accountRepository.save(newAccount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find account with id ", id));
    }


    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
