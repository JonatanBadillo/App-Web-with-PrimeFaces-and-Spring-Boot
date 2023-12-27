package jb.accounts.service;

import jb.accounts.model.Account;

import java.util.List;

public interface IAccountService {

    public List<Account> listAccounts();

    public Account searchAccountById(Integer idAccount);

    public Account saveAccount(Account account);

    public void deleteAccount(Account account);

}
