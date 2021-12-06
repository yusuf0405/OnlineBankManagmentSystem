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


    public Bank(List<User> customers, List<Account> accounts, ArrayList<Transaction> transactions, ArrayList<Integer> ids) {
        this.customers = customers;
        this.accounts = accounts;
        this.ids = ids;
        this.transactions = transactions;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }









    public List<User> getCustomers() {
        return customers;
    }



    public List<Account> getAccounts() {
        return accounts;
    }




}
