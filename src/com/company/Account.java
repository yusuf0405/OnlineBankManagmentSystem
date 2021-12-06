package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private int id;
    private double balance;
    private String name;
    private User accountHolder;
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(double balance, String name, User accountHolder) {
        this.id = User.genUniqueId();
        this.balance = balance;
        this.name = name;
        this.accountHolder = accountHolder;
    }

    static void getAllAccount() {
        for (Account a : Main.accounts) {
            a.getInfo();

        }
    }

    void getInfo() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-ID счета: " + this.id + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Тип счета: " + this.name + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Владелец счета: " + accountHolder.getFirstName() + " " + accountHolder.getLastName() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("__________________________________________________________________________________________________________________________________");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(User accountHolder) {
        this.accountHolder = accountHolder;
    }

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static void setTransactions(ArrayList<Transaction> transactions) {
        Account.transactions = transactions;
    }
}
