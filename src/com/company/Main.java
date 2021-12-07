package com.company;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("ALL")

public class Main {
    static double kursUsd = 86;
    static Bank bank;
    static User loggedUser;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();



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

        ArrayList<Account> madinaAccounts = new ArrayList<>();
        User madina = new User("Мадина", "Абдувастова", "4", "4", madinaAccounts);
        Account usdMadina = new Account(500, "USD", madina);
        Account kgzMadina = new Account(200, "KGZ", madina);
        madinaAccounts.add(usdMadina);
        madinaAccounts.add(kgzMadina);
        accounts.addAll(madinaAccounts);
        users.add(madina);

        ArrayList<Account> muhabbatAccounts = new ArrayList<>();


        User muhabbat = new User("Мухаббат", "Акбарова", "5", "123", muhabbatAccounts);
        Account usdMuhabbat = new Account(500000, "USD", muhabbat);
        Account kgzMuhabbat = new Account(200000, "KGZ", muhabbat);
        muhabbatAccounts.add(usdMuhabbat);
        muhabbatAccounts.add(kgzMuhabbat);
        accounts.addAll(muhabbatAccounts);
        users.add(muhabbat);


        bank = new Bank("Джозеф", users, accounts, transactions);


        readingFile();
        login();
    }


    private static void login() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (true) {
            try {
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите свой логин:-*-*-*-*-*-*-*-*-*-*-*-");
                String login = scanner.nextLine();
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите свой пароль:-*-*-*-*-*-*-*-*-*-*-*-");
                String password = scanner.nextLine();
                while (true) {
                    for (User user : bank.getCustomers()) {
                        if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                            loggedUser = user;
                            MainMenu();
                            break;
                        }
                    }
                    if (count < 2) {
                        count++;
                        System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Неправильный пароль или логин!!-*-*-*-*-*-*-*-*-*-*-*-" +
                                "\n-*-*-*-*-*-*-*-*-*-*-*-Осталось попыток: " + (3 - count) + "-*-*-*-*-*-*-*-*-*-*-*-");
                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите свой логин:-*-*-*-*-*-*-*-*-*-*-*-");
                        login = scanner.nextLine();
                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Введите свой пароль:-*-*-*-*-*-*-*-*-*-*-*-");
                        password = scanner.nextLine();

                    } else {
                        System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Использовано 3 попыток ввода.-*-*-*-*-*-*-*-*-*-*-*-");
                        System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Вы не смогли войти программа завершена , поробуйте заново.-*-*-*-*-*-*-*-*-*-*-*-");
                        System.exit(0);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ошибка");
            }
        }
    }


    private static void MainMenu() {
        while (true) {
            try {
                boolean n = false;
                while (true) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 1 чтобы посмотреть всех пользователей-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 2 чтобы попольнить счет-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 3 чтобы снять деньги со счета-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 4 чтобы перевети средства на другой счет-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 5 чтобы узнать информацию о своих транзациях и аккаунтах-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 6 чтобы узнать информацию о Банке-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Нажмите 7 чтобы выйти из системы и сохранить изминение-*-*-*-*-*-*-*-*-*-*-*-");
                    int num = scanner.nextInt();
                    switch (num) {
                        case 1:
                            User.getAllUsers();
                            n = true;
                            restart();
                        case 2:
                            Transaction.transferOfFunds();
                            n = true;
                            restart();
                        case 3:
                            Transaction.withdrawMoney();
                            n = true;
                            restart();
                        case 4:
                            Transaction.topUpAccount();
                            n = true;
                            restart();
                        case 5:
                            Transaction.informationTransaction();
                            n = true;
                            restart();
                        case 6:
                            Bank.infoBank();
                            n = true;
                            restart();
                        case 7:
                            savingData();
                    }
                    if (n) {
                        break;
                    }
                    if (!n) {
                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка следуйте инструкциям-*-*-*-*-*-*-*-*-*-*-*-");
                    }
                }
            } catch (Exception e) {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-Ошибка!!!-*-*-*-*-*-*-*-*-*-*-*-" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-Неверный формат ввода!!-*-*-*-*-*-*-*-*-*-*-*-");
                scanner.next();
            }


        }


    }


    static void restart() {
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
            ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("Bank"));
            ois.writeObject(bank);
            ois.close();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Запись не удалась");

        }


    }

    private static void readingFile() {
        try {
            ObjectInputStream oos = new ObjectInputStream(new FileInputStream("Bank"));
            bank = (Bank) oos.readObject();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не удалось прочитать файл!!!");
        }

    }


}

