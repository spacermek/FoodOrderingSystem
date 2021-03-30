package com.intro.introSpring.Controller;

import com.intro.introSpring.Entity.Account;
import com.intro.introSpring.Exceptions.NotEnoughMoneyException;
import com.intro.introSpring.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAll();
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long accountId) {
        return accountService.getById(accountId);
    }

    @PostMapping
    public Account saveAccount(@RequestBody Account account) throws NotEnoughMoneyException {
        return accountService.save(account);
    }

    @PutMapping("/{accountId}")
    public Account updateAccount(@RequestBody Account newAccount, @PathVariable Long accountId) {
        return accountService.updateById(newAccount, accountId);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        accountService.deleteById(accountId);
    }
}
