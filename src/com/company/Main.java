package com.company;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static double kursUsd = 86;
    static Bank bank;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Account> accounts = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static User loggedUser;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        ArrayList<Account> josephAccounts = new ArrayList<>();
        User joseph = new User("Юсуф", "Рузиев", "1", "1", josephAccounts);
        Account kgzJoseph = new Account(4000, "KGZ", joseph);
        Account usdJoseph = new Account(3000, "USD", joseph);
        josephAccounts.add(kgzJoseph);
        josephAccounts.add(usdJoseph);
        accounts.addAll(josephAccounts);
        users.add(joseph);


        ArrayList<Account> mikeAccounts = new ArrayList<>();
        User mike = new User("Maйкл", "Тайсон", "2", "2", mikeAccounts);
        Account kgzMike = new Account(2000, "KGZ", mike);
        Account usdMike = new Account(5000, "USD", mike);
        mikeAccounts.add(kgzMike);
        mikeAccounts.add(usdMike);
        accounts.addAll(mikeAccounts);
        users.add(mike);


        ArrayList<Account> ivanAccounts = new ArrayList<>();
        User ivan = new User("Иван", "Иванов", "3", "3", ivanAccounts);
        Account usdIvan = new Account(5000, "USD", ivan);
        Account kgzIvan = new Account(2000, "KGZ", ivan);
        ivanAccounts.add(usdIvan);
        ivanAccounts.add(kgzIvan);
        accounts.addAll(ivanAccounts);
        users.add(ivan);

        bank = new Bank(users, accounts, transactions, User.ids);

//        kgzJoseph.getInfo();


        readingFile();
        login();
    }


    static void login() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (true) {
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите свой логин:-*-*-*-*-*-*-*-*-*-*-*");
            String login = scanner.nextLine();
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите свой пароль:-*-*-*-*-*-*-*-*-*-*-*");
            String password = scanner.nextLine();
            for (User user : users) {
                if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                    loggedUser = user;
                    MainMenu();
                    break;


                } else {
                    count++;
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Неправильный пароль или логин!!-*-*-*-*-*-*-*-*-*-*-*-" + "\n-*-*-*-*-*-*-*-*-*-*-*-Осталось попыток: " + (3 - count) + "-*-*-*-*-*-*-*-*-*-*-*-");
                }
                if (count == 3) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-использовано 3 попыток ввода.-*-*-*-*-*-*-*-*-*-*-*-" + "\n-*-*-*-*-*-*-*-*-*-*-*-Программа завершена-*-*-*-*-*-*-*-*-*-*-*-");
                    break;

                }

            }
        }
    }

    private static void MainMenu() {
        boolean k = false;
        while (true) {
            try {
                boolean n = false;
                while (true) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 1 чтобы посмотреть всех пользователей-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 2 чтобы попольнить счет-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 3 чтобы снять деньги со счета-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 4 чтобы перевети средства на другой счет-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 5 чтобы узнать информацию о своих транзациях-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 6 чтобы выйти из системы и сохранить изминение-*-*-*-*-*-*-*-*-*-*-*-");
                    int num = scanner.nextInt();
                    switch (num) {
                        case 1:
                            User.getAllUsers();
                            k = true;
                            n = true;
                            restart();
                        case 2:
                            transferOfFunds();
                            k = true;
                            n = true;
                            restart();
                        case 3:
                            withdrawMoney();
                            k = true;
                            n = true;
                            restart();
                        case 4:
                            topUpAccount();
                            k = true;
                            n = true;
                            restart();
                        case 5:
                            informationTransaction();
                            k = true;
                            n = true;
                            restart();
                        case 6:
                            savingData();
                    }
                    if (n) {
                        break;
                    }
                    if (!n) {
                        System.out.println("dd");
                    }
                }
            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
            }
            if (k) {
                break;
            }

        }


    }

    private static void informationTransaction() {
        boolean mistake = false;
        while (true) {
            for (Transaction t : transactions) {
                if (loggedUser.getFirstName().equals(t.getAccount().getAccountHolder().getFirstName())) {
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

    private static void withdrawMoney() {
        while (true) {
            try {
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите id своего счета:-*-*-*-*-*-*-*-*-*-*-*-");
                int id = scanner.nextInt();
                for (Account account : accounts) {
                    if (id == account.getId() && loggedUser.getFirstName().equals(account.getAccountHolder().getFirstName())) {
                        account.getInfo();
                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму:-*-*-*-*-*-*-*-*-*-*-*-");
                        double summa = scanner.nextDouble();
                        if (summa > account.getBalance()) {
                            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-У вас не достаточно денег на балансе чтобы снять денги в таком количестве-*-*-*-*-*-*-*-*-*-*-*-");
                            restart();
                        } else {
                            double newBalanse = account.getBalance() - summa;
                            account.setBalance(newBalanse);
                            Transaction transaction = new Transaction("Снятие денег со счета.", summa, sdf.format(new Date()), account);
                            transactions.add(transaction);
                            transaction.getInfo();
                            restart();
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

    private static void transferOfFunds() {
        while (true) {
            try {
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите id своего счета:-*-*-*-*-*-*-*-*-*-*-*-");
                int id = scanner.nextInt();
                for (Account account : accounts) {
                    if (id == account.getId() && loggedUser.getFirstName().equals(account.getAccountHolder().getFirstName())) {
                        account.getInfo();
                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму поплнение:-*-*-*-*-*-*-*-*-*-*-*-");
                        double summa = scanner.nextDouble();
                        double f = account.getBalance() + summa;
                        account.setBalance(f);
                        Transaction transaction = new Transaction("Пополнение счета.", summa, sdf.format(new Date()), account);
                        transactions.add(transaction);
                        transaction.getInfo();
                        restart();
                    }
                }
            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();

            }
        }

    }

    private static void topUpAccount() {
        while (true) {
            try {
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите id своего счета:-*-*-*-*-*-*-*-*-*-*-*-");
                int i = scanner.nextInt();
                for (Account a : loggedUser.getAccountList()) {
                    if (i == a.getId()) {
                        Account.getAllAccount();
                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите id счета на которую хотите перевести деньги:-*-*-*-*-*-*-*-*-*-*-*-");
                        int id = scanner.nextInt();
                        for (Account account : accounts) {
                            if (id == account.getId() && !loggedUser.getFirstName().equals(account.getAccountHolder().getFirstName())) {
                                account.getInfo();
                                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите сумму для перевода:-*-*-*-*-*-*-*-*-*-*-*-");
                                double summa = scanner.nextDouble();
                                if (summa > a.getBalance()) {
                                    System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Не достаточно денег на счету чтобы сделать перевод-*-*-*-*-*-*-*-*-*-*-*-");
                                    restart();
                                } else {
                                    if (a.getName().equals("KGZ") && account.getName().equals("USD")) {
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Вы переводите с KGZ баланса на USD баланс-*-*-*-*-*-*-*-*-*-*-*-");
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма перевода будет увеличен согласно по курсу USD-*-*-*-*-*-*-*-*-*-*-*-");
                                        summa = summa * kursUsd;
                                        double minus = a.getBalance() - summa;
                                        double popolnenie = account.getBalance() + summa;
                                        a.setBalance(minus);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", summa, sdf.format(new Date()), a);
                                        Transaction transactionP = new Transaction("Перевод средств(Приняте).", summa, sdf.format(new Date()), account);
                                        transactions.add(transactionM);
                                        transactions.add(transactionP);
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Переведено деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionM.getInfo();
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Принято деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionP.getInfo();
                                        transactions.add(transactionM);
                                        transactions.add(transactionP);
                                        restart();
                                    } else if (a.getName().equals("USD") && account.getName().equals("KGZ")) {
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Вы переводите с USD баланса на KGZ баланс-*-*-*-*-*-*-*-*-*-*-*-");
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Сумма перевода будет уменшен согласно по курсу USD-*-*-*-*-*-*-*-*-*-*-*-");
                                        summa = summa / kursUsd;
                                        double f = a.getBalance() - summa;
                                        double popolnenie = account.getBalance() + summa;
                                        a.setBalance(f);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", summa, sdf.format(new Date()), a);
                                        Transaction transactionP = new Transaction("Перевод средств(Приняте).", summa, sdf.format(new Date()), account);
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Переведено деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionM.getInfo();
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Принято деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionP.getInfo();
                                        transactions.add(transactionM);
                                        transactions.add(transactionP);
                                        restart();
                                    } else {
                                        double f = a.getBalance() - summa;
                                        double popolnenie = account.getBalance() + summa;
                                        a.setBalance(f);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", summa, sdf.format(new Date()), a);
                                        Transaction transactionP = new Transaction("Перевод средств(Приняте).", summa, sdf.format(new Date()), account);
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Переведено деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionM.getInfo();
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Принято деньги-*-*-*-*-*-*-*-*-*-*-*-");
                                        transactionP.getInfo();
                                        transactions.add(transactionM);
                                        transactions.add(transactionP);
                                        restart();

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

    private static void restart() {
        boolean d = false;
        System.out.println("\n-*-*-*-*-*-*-*-*-*-*-*-Нажмите любое число чтобы перейти в гланое меню:-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 0 чтобы завершит процесс и сохранит изминение:-*-*-*-*-*-*-*-*-*-*-*-");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int b = scanner.nextInt();
                if (b == 0) {
                    savingData();

                }
                d = true;
                MainMenu();
                break;
//

            } catch (Exception e) {
                System.err.println("\n-*-*-*-*-*-*-*-*-*-*-*-Нажмите любое ЧИСЛО чтобы перейти в гланое меню!!!-*-*-*-*-*-*-*-*-*-*-*-");
            }
            if (d) {
                break;
            }
        }
    }

    private static void savingData() {
        try {
            ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("users"));
            ois.writeObject(users);
            ObjectOutputStream oys = new ObjectOutputStream(new FileOutputStream("accounts"));
            oys.writeObject(accounts);
            ObjectOutputStream ofs = new ObjectOutputStream(new FileOutputStream("ids"));
            ofs.writeObject(User.ids);
            ObjectOutputStream of = new ObjectOutputStream(new FileOutputStream("transactions"));
            of.writeObject(transactions);
            of.close();
            ois.close();
            oys.close();
            ofs.close();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Запись не удалась");

        }


    }

    static void readingFile() {
        try {
            ObjectInputStream oos = new ObjectInputStream(new FileInputStream("users"));
            ObjectInputStream os = new ObjectInputStream(new FileInputStream("accounts"));
            ObjectInputStream osf = new ObjectInputStream(new FileInputStream("transactions"));
            ObjectInputStream oj = new ObjectInputStream(new FileInputStream("ids"));
            User.ids = (ArrayList<Integer>) oj.readObject();
            users = (ArrayList<User>) oos.readObject();
            accounts = (ArrayList<Account>) os.readObject();
            transactions = (ArrayList<Transaction>) osf.readObject();
            oj.close();
            osf.close();
            oos.close();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не удалось прочитать файл!!!");
        }

    }



}

