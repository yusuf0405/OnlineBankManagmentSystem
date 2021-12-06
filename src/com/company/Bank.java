package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {
    private String bankName;
    private List<User> customers;
    private List<Account> accounts;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Integer> ids = new ArrayList<>();



    public Bank(List<User> customers, List<Account> accounts,ArrayList<Transaction> transactions,ArrayList<Integer> ids) {
        this.customers = customers;
        this.accounts = accounts;
        this.ids = ids;
        this.transactions = transactions;
    }

    public String getBankName() {
        return bankName;
    }

    public List<User> getCustomers() {
        return customers;
    }

    public void setCustomers(List<User> customers) {
        this.customers = customers;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;

    }


}
