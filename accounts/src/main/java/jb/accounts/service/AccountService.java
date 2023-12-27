package jb.accounts.service;

import jb.accounts.model.Account;
import jb.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService implements IAccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account searchAccountById(Integer idAccount) {
        Account account = accountRepository.findById(idAccount).orElse(null);
        return account;
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }
}
