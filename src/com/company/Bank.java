package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {
    private String bankName;
    private final List<User> customers;
    private final List<Account> accounts;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Bank(String bankName, List<User> customers, List<Account> accounts, ArrayList<Transaction> transactions) {
        this.customers = customers;
        this.accounts = accounts;
        this.transactions = transactions;

    }

    static void infoBank() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Информация про банк имени Джозефа-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("*******************************************************************************");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Все клиенты банка и их аккаунты-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("*******************************************************************************");
        User.getAllUsers();
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Количество всех клиентов: " + Main.bank.customers.size() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Количество существующих аккаунтов: " + Main.bank.accounts.size() + "-*-*-*-*-*-*-*-*-*-*-*-");
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }


    public String getBankName() {
        return bankName;
    }

    public List<User> getCustomers() {
        return customers;
    }


    public List<Account> getAccounts() {
        return accounts;
    }


}
