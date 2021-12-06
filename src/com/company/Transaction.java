package com.company;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private double amount;
    private String timestamp;
    private Account account;
    private String typeTransaction;

    public Transaction(String typeTransaction, double amount, String timestamp, Account account) {
        this.typeTransaction = typeTransaction;
        this.amount = amount;
        this.timestamp = timestamp;
        this.account = account;
    }

    void getInfo() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Пользватель: " + getAccount().getAccountHolder().getFirstName() + " " + getAccount().getAccountHolder().getLastName() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Тип операции: " + typeTransaction + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма транзакции: " + getAmount() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Баланс после транзакции: " + getAccount().getBalance() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Дата операции: " + getTimestamp() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Тип счета на котором была совершена операция: " + getAccount().getName() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Id счета: " + getAccount().getId() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("**********************************************************************************************************************************************");
    }


    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
