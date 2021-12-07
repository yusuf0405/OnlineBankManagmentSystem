package com.company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Transaction implements Serializable {
    private final double amount;
    private final String timestamp;
    private final Account account;
    private final String typeTransaction;
    static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


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
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Баланс после транзакции: " + (getAccount().getBalance()) + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Дата операции: " + getTimestamp() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Тип счета на котором была совершена операция: " + getAccount().getName() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Id счета: " + getAccount().getId() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("**********************************************************************************************************************************************");
    }

    static void informationTransaction() {
        boolean mistake = false;
        while (true) {
            Account.accaunts();
            for (Transaction t : Main.bank.getTransactions()) {
                if (Main.loggedUser.getId() == t.getAccount().getAccountHolder().getId()) {
                    t.getInfo();
                    mistake = true;

                }

            }
            if (mistake) {
                break;
            }
            if (!mistake) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-У вас пока что нет транзакциий-*-*-*-*-*-*-*-*-*-*-*-");
                break;
            }
        }


    }

    static void withdrawMoney() {
        while (true) try {
            Account.accaunts();
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите id своего счета:-*-*-*-*-*-*-*-*-*-*-*-");
            int id = scanner.nextInt();
            for (Account account : Main.loggedUser.getAccountList()) {
                if (id == account.getId() && account.getName().equals("KGZ")) {
                    withdrawMoneySettingKGZ(account);
                } else if (id == account.getId() && account.getName().equals("USD")) {
                    withdrawMoneySettingUSD(account);
                }
            }
        } catch (Exception e) {
            System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                    "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
            scanner.next();

        }


    }

    private static void withdrawMoneyKgz(Account account) {
        while (true) {
            try {
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму:-*-*-*-*-*-*-*-*-*-*-*-");
                double summa = scanner.nextDouble();
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Вы снимате деньги с USD на KGZ валюту-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма будет умножена согласно по курсу USD-*-*-*-*-*-*-*-*-*-*-*-");
                summa = summa * Main.kursUsd;
                summa = Math.round(summa);
                if (summa > account.getBalance()) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-У вас не достаточно денег на балансе чтобы снять денги в таком количестве-*-*-*-*-*-*-*-*-*-*-*-");
                    Main.restart();
                } else {
                    double newBalanse = account.getBalance() - summa;
                    account.setBalance(newBalanse);
                    Transaction transaction = new Transaction("Снятие денег со счета.", summa, sdf.format(new Date()), account);
                    Main.bank.getTransactions().add(transaction);
                    transaction.getInfo();
                    Main.restart();

                }


            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();

            }
        }


    }

    private static void withdrawMoneyNO(Account account) {
        while (true) {
            try {
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму:-*-*-*-*-*-*-*-*-*-*-*-");
                double summa = scanner.nextDouble();
                if (summa > account.getBalance()) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-У вас не достаточно денег на балансе чтобы снять денги в таком количестве-*-*-*-*-*-*-*-*-*-*-*-");
                    Main.restart();
                } else {
                    double newBalanse = account.getBalance() - summa;
                    account.setBalance(newBalanse);
                    Transaction transaction = new Transaction("Снятие денег со счета.", summa, sdf.format(new Date()), account);
                    Main.bank.getTransactions().add(transaction);
                    transaction.getInfo();
                    Main.restart();

                }


            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();

            }
        }

    }

    private static void withdrawMoneyUsd(Account account) {
        while (true) {
            try {
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму:-*-*-*-*-*-*-*-*-*-*-*-");
                double summa = scanner.nextDouble();
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Вы снимате деньги с KGZ счета на USD валюту-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма будет уменшена согласно по курсу USD-*-*-*-*-*-*-*-*-*-*-*-");
                summa = summa / Main.kursUsd;
                summa = Math.round(summa);
                if (summa > account.getBalance()) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-У вас не достаточно денег на балансе чтобы снять денги в таком количестве-*-*-*-*-*-*-*-*-*-*-*-");
                    Main.restart();
                } else {
                    double newBalanse = account.getBalance() - summa;
                    account.setBalance(newBalanse);
                    Transaction transaction = new Transaction("Снятие денег со счета.", summa, sdf.format(new Date()), account);
                    Main.bank.getTransactions().add(transaction);
                    transaction.getInfo();
                    Main.restart();

                }
            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();
            }
        }


    }

    private static void withdrawMoneySettingUSD(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Баланс: " + account.getBalance() + "-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("____________________________________________________________________________________");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 1 чтобы вывести деньги на USD-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 2 чтобы вывести деньги на KGZ-*-*-*-*-*-*-*-*-*-*-*-");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        withdrawMoneyNO(account);

                    case 2:
                        withdrawMoneyKgz(account);


                }

            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();
            }


        }

    }

    private static void withdrawMoneySettingKGZ(Account account) {

        while (true) {
            try {
                account.getInfo();

                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Баланс: " + account.getBalance() + "-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("____________________________________________________________________________________");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 1 чтобы вывести деньги на USD-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 2 чтобы вывести деньги на KGZ-*-*-*-*-*-*-*-*-*-*-*-");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        withdrawMoneyUsd(account);

                    case 2:
                        withdrawMoneyNO(account);


                }

            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();
            }

        }

    }


    private static void transferOfFundsKgz(Account account) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму поплнение:-*-*-*-*-*-*-*-*-*-*-*-");
        double summa = scanner.nextDouble();
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Вы пополняете USD счет с KGZ валюты-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма будет уменшена согласно по курсу USD-*-*-*-*-*-*-*-*-*-*-*-");

        summa = summa / Main.kursUsd;
        summa = Math.round(summa);
        double f = account.getBalance() + summa;
        account.setBalance(f);
        Transaction transaction = new Transaction("Пополнение счета.", summa, sdf.format(new Date()), account);
        Main.bank.getTransactions().add(transaction);
        transaction.getInfo();
        Main.restart();

    }

    private static void transferOfFundsUsd(Account account) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму поплнение:-*-*-*-*-*-*-*-*-*-*-*-");
        double summa = scanner.nextDouble();
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Вы пополняете KGZ счет с USD валюты-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма будет умножена согласно по курсу USD-*-*-*-*-*-*-*-*-*-*-*-");
        summa = summa * Main.kursUsd;
        summa = Math.round(summa);
        double f = account.getBalance() + summa;
        account.setBalance(f);
        Transaction transaction = new Transaction("Пополнение счета.", summa, sdf.format(new Date()), account);
        Main.bank.getTransactions().add(transaction);
        transaction.getInfo();
        Main.restart();

    }

    private static void transferOfFundsNO(Account account) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму поплнение:-*-*-*-*-*-*-*-*-*-*-*-");
        double summa = scanner.nextDouble();
        double f = account.getBalance() + summa;
        account.setBalance(f);
        Transaction transaction = new Transaction("Пополнение счета.", summa, sdf.format(new Date()), account);
        Main.bank.getTransactions().add(transaction);
        transaction.getInfo();
        Main.restart();

    }

    private static void transferOfFundsUSD(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Баланс: " + account.getBalance() + "-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("____________________________________________________________________________________");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 1 чтобы пополнить деньги на USD-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 2 чтобы попольнить деньги на KGZ-*-*-*-*-*-*-*-*-*-*-*-");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        transferOfFundsNO(account);

                    case 2:
                        transferOfFundsKgz(account);
                }

            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();
            }


        }

    }

    private static void transferOfFundsKGZ(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Баланс: " + account.getBalance() + "-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("____________________________________________________________________________________");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 1 чтобы пополнить деньги на USD-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 2 чтобы попольнить деньги на KGZ-*-*-*-*-*-*-*-*-*-*-*-");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        transferOfFundsUsd(account);

                    case 2:
                        transferOfFundsNO(account);
                }

            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();
            }


        }

    }


    static void transferOfFunds() {
        while (true) {
            try {
                Account.accaunts();
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите id своего счета:-*-*-*-*-*-*-*-*-*-*-*-");
                int id = scanner.nextInt();
                for (Account account : Main.loggedUser.getAccountList()) {
                    if (id == account.getId() && account.getName().equals("KGZ")) {
                        transferOfFundsKGZ(account);
                    } else if (id == account.getId() && account.getName().equals("USD")) {
                        transferOfFundsUSD(account);
                    }
                }
            } catch (
                    Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();

            }
        }

    }


    static void topUpAccount() {
        while (true) {
            try {
                Account.accaunts();
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите id своего счета:-*-*-*-*-*-*-*-*-*-*-*-");
                int i = scanner.nextInt();
                for (Account a : Main.loggedUser.getAccountList()) {
                    if (i == a.getId()) {
                        Account.getAllAccount((ArrayList<Account>) Main.bank.getAccounts());
                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите id счета на которую хотите перевести деньги:-*-*-*-*-*-*-*-*-*-*-*-");
                        int id = scanner.nextInt();
                        for (Account account : Main.bank.getAccounts()) {
                            if (id == account.getId() && id != a.getId()) {
                                account.getInfo();
                                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму для перевода:-*-*-*-*-*-*-*-*-*-*-*-");
                                double summa = scanner.nextDouble();
                                if (summa > a.getBalance()) {
                                    System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Не достаточно денег на счету чтобы сделать перевод-*-*-*-*-*-*-*-*-*-*-*-");
                                    Main.restart();
                                } else {
                                    if (a.getName().equals("KGZ") && account.getName().equals("USD")) {
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Вы переводите с KGZ баланса на USD баланс-*-*-*-*-*-*-*-*-*-*-*-");
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма перевода будет уменшен согласно по курсу USD-*-*-*-*-*-*-*-*-*-*-*-");
                                        summa = summa / Main.kursUsd;
                                        summa = Math.round(summa);
                                        if (summa > a.getBalance()) {
                                            System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Не достаточно денег на счету чтобы сделать перевод-*-*-*-*-*-*-*-*-*-*-*-");
                                            Main.restart();
                                        }
                                        double minus = a.getBalance() - summa;
                                        double popolnenie = account.getBalance() + summa;
                                        a.setBalance(minus);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", summa, sdf.format(new Date()), a);
                                        Transaction transactionP = new Transaction("Перевод средств(Принято).", summa, sdf.format(new Date()), account);
                                        Main.bank.getTransactions().add(transactionM);
                                        Main.bank.getTransactions().add(transactionP);
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Переведено деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionM.getInfo();
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Принято деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionP.getInfo();
                                        Main.bank.getTransactions().add(transactionM);
                                        Main.bank.getTransactions().add(transactionP);
                                        Main.restart();
                                    } else if (a.getName().equals("USD") && account.getName().equals("KGZ")) {
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Вы переводите с USD баланса на KGZ баланс-*-*-*-*-*-*-*-*-*-*-*-");
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма перевода будет увеличен согласно по курсу USD-*-*-*-*-*-*-*-*-*-*-*-");
                                        summa = summa * Main.kursUsd;
                                        summa = Math.round(summa);
                                        double f = a.getBalance() - summa;
                                        double popolnenie = account.getBalance() + summa;
                                        a.setBalance(f);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", summa, sdf.format(new Date()), a);
                                        Transaction transactionP = new Transaction("Перевод средств(Принято).", summa, sdf.format(new Date()), account);
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Переведено деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionM.getInfo();
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Принято деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionP.getInfo();
                                        Main.bank.getTransactions().add(transactionM);
                                        Main.bank.getTransactions().add(transactionP);
                                        Main.restart();
                                    } else {
                                        double f = a.getBalance() - summa;
                                        double popolnenie = account.getBalance() + summa;
                                        a.setBalance(f);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", summa, sdf.format(new Date()), a);
                                        Transaction transactionP = new Transaction("Перевод средств(Принято).", summa, sdf.format(new Date()), account);
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Переведено деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionM.getInfo();
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Принято деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionP.getInfo();
                                        Main.bank.getTransactions().add(transactionM);
                                        Main.bank.getTransactions().add(transactionP);
                                        Main.restart();

                                    }
                                }

                            }
                        }

                    }

                }

            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();

            }
        }


    }


    public double getAmount() {
        return amount;
    }


    public String getTimestamp() {
        return timestamp;
    }


    public Account getAccount() {
        return account;
    }

}
