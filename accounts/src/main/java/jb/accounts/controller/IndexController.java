package jb.accounts.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jb.accounts.model.Account;
import jb.accounts.service.AccountService;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {
    @Autowired
    AccountService accountService;
    private List<Account> accounts;
    private Account selectedAccount;

    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);
    @PostConstruct
    public void init(){
        loadData();
    }

    public void loadData(){
        this.accounts = accountService.listAccounts();
        accounts.forEach((account -> logger.info(account.toString())));
    }

    public void addAccount(){
        this.selectedAccount = new Account();
    }

    public void saveAccount(){
        logger.info("Account to save: " + this.selectedAccount);
        if(this.selectedAccount.getIdAccount() == null){
            this.accountService.saveAccount(this.selectedAccount);
            this.accounts.add(this.selectedAccount);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Added Account"));
        }
        // Hiding the window
        PrimeFaces.current().executeScript("PF('windowModalAccount').hide()");
        // Update the table
        PrimeFaces.current().ajax().update("form-accounts:messages","form-accounts:accounts-table");
    }
}
