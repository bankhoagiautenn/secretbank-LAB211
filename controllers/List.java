package controllers;

import dto.I_List;
import java.util.ArrayList;
import dto.Account;
import dto.I_Menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import utils.Utils;

//@author SE173593
public class List extends ArrayList<Account> implements I_List {


    // Declare
    private boolean permission = false;
    private int activeAccountIndex = 0;


    // Format
    final int CRYPT_KEY = 20;
    final String ID_FORMAT = "^ACC\\d{3}";
    final String NAME_FORMAT = "[a-z_A-Z_0-9-]{5,15}$";
    final String PASSWORD_FORMAT = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{6,}$";

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public int getActiveAccountIndex() {
        return activeAccountIndex;
    }

    public void setActiveAccountIndex(int activeAccountIndex) {
        this.activeAccountIndex = activeAccountIndex;
    }

    @Override
    public boolean checkExistId(String accountId) {
        boolean check = true;
        if (this.isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getAccountId().equals(accountId)) {
                    System.out.println("-> This ID has already been used!");
                    return !check;
                }
            }
            return check;
        }
    }

    @Override
    public boolean checkExistName(String accountName) {
        boolean check = true;
        if (this.isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getAccountName().equalsIgnoreCase(accountName)) {
                    System.out.println("-> This name has already been used!");
                    return !check;
                }
            }
            return check;
        }
    }

    @Override
    public void createAccount() {
        setPermission(false);
        System.out.println("-> Create new account");
        this.add(inputAccountData());
        System.out.println("-> Account created successfully!");
    }

    @Override
    public Account inputAccountData() {
        boolean isSameId;
        boolean isSameName;
        boolean isSamePassword = false;
        String accountId, accountName, password, confirmPassword;
        do {
            accountId = Utils.getStringRegex("Enter account ID: ", ID_FORMAT, "-> Invalid ID format, valid ID format is <ACC*** with * is a number>");
            isSameId = checkExistId(accountId);
        } while (isSameId == false);
        do {
            accountName = Utils.getStringRegex("Enter account name: ", NAME_FORMAT, "-> Account name length must be from 5 to 15 characters. Space and special characters are not allowed!");
            isSameName = checkExistName(accountName);
        } while (isSameName == false);
        password = Utils.getStringRegex("Enter password: ", PASSWORD_FORMAT, "-> Password must contain at least 6 characters including uppercase letters, lowercase letters, numbers, and special characters!");
        do {
            confirmPassword = Utils.getString("Confirm your password <Password and confirmed password must be the same>: ");
            if (confirmPassword.equals(password)) {
                isSamePassword = true;
            }
        } while (!isSamePassword);
        return new Account(accountId, accountName, password);
    }

    @Override
    public void loginAccount() {
        boolean isExist = false;
        String accountId, password;
        accountId = Utils.getStringRegex("Enter account ID: ", ID_FORMAT, "-> Invalid ID format, valid ID format is <ACC*** with * is a number>");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getAccountId().contains(accountId)) {
                setActiveAccountIndex(i);
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("-> Sorry! This user is not found");
        }
        if (isExist) {
            password = Utils.getString("Enter password: ");
            if (password.equals(this.get(activeAccountIndex).getPassword())) {
                System.out.println("-> WELCOME, " + this.get(activeAccountIndex).getAccountName() + "!");
                setPermission(true);
            } else {
                System.out.println("-> Wrong password!");
            }
        }
    }

    @Override
    public void withdrawMoney() {
        if (!permission) {
            System.out.println("-> Sorry! You are not allowed to access this function");
        } else {
            System.out.println("-> Your current balance: $" + this.get(activeAccountIndex).getMoney());
            int withdrawMoney = Utils.getInt("Enter the money you want to withdraw: $");
            if (withdrawMoney > this.get(activeAccountIndex).getMoney()) {
                System.out.println("-> Sorry! you don't have enough money to withdraw");
            } else if (withdrawMoney <= 0) {
                System.out.println("-> Withdraw money must be greater than 0$");
            } else {
                this.get(activeAccountIndex).setMoney(this.get(activeAccountIndex).getMoney() - withdrawMoney);
                System.out.println("-> Withdraw succesfully! Your new current balance: $" + this.get(activeAccountIndex).getMoney());
            }
        }
    }

    @Override
    public void depositMoney() {
        if (!permission) {
            System.out.println("-> Sorry! You are not allowed to access this function");
        } else {
            System.out.println("-> Your current balance: $" + this.get(activeAccountIndex).getMoney());
            int depositMoney = Utils.getInt("Enter the money you want to deposit: $");
            if (depositMoney <= 0) {
                System.out.println("-> Deposit money must be greater than $0");
                return;
            } 
            I_Menu menu = new Menu();
            menu.addItem("-> Are you sure you want to deposit $" + depositMoney + " to your account?");
            menu.addItem("1.Yes");
            menu.addItem("2.No");
            menu.showMenu();
            int choice = Utils.getInt("Enter your choice <1 or 2>: ", 1, 2);
            switch (choice) {
                case 1:
                    this.get(activeAccountIndex).setMoney(this.get(activeAccountIndex).getMoney() + depositMoney);
                    System.out.println("-> Deposit succesfully! Your new current balance: $" + this.get(activeAccountIndex).getMoney());
                    break;
                case 2:
                    System.out.println("-> Deposit cancelled!");
                    break;
            }
        }
    }

    @Override
    public void transferMoney() {
        boolean isExist = false;
        if (!permission) {
            System.out.println("-> Sorry! You are not allowed to access this function");
        } else {
            System.out.println("-> Your current balance: $" + this.get(activeAccountIndex).getMoney());
            String accountId = Utils.getStringRegex("Enter receiver account ID: ", ID_FORMAT, "-> Invalid ID format, valid ID format is <ACC*** with * is a number>");
            if (this.get(activeAccountIndex).getAccountId().equals(accountId)) {
                System.out.println("-> Sorry! You can not transfer money to your own account");
                return;
            }
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getAccountId().equals(accountId)) {
                    isExist = true;
                    System.out.println("-> Transfer money to " + this.get(i).getAccountName());
                    int transferMoney = Utils.getInt("Enter the money you want to transfer: $");
                    if (this.get(activeAccountIndex).getMoney() < transferMoney) {
                        System.out.println("-> Sorry! You don't have enough money to transfer");
                    }else if(transferMoney <= 0) {
                        System.out.println("-> Transfer money must be greater than $0");
                    } else {
                        I_Menu menu = new Menu();
                        menu.addItem("-> Are you sure you want to transfer $"+ transferMoney+" to " + this.get(i).getAccountName() +" ?");
                        menu.addItem("1.Yes");
                        menu.addItem("2.No");
                        menu.showMenu();
                        int choice = Utils.getInt("Enter your choice <1 or 2>: ", 1, 2);
                        switch (choice) {
                            case 1:
                                this.get(activeAccountIndex).setMoney(this.get(activeAccountIndex).getMoney() - transferMoney);
                                this.get(i).setMoney(this.get(i).getMoney() + transferMoney);
                                System.out.println("-> Transfer succesfully! Your new current balance: $" + this.get(activeAccountIndex).getMoney());
                                System.out.println("-> " + this.get(i).getAccountName() + " new current balance: $" + this.get(i).getMoney());
                                break;
                            case 2:
                                System.out.println("-> Transfer cancelled!");
                                break;
                        }
                    }
                }
            } if (!isExist) {
                System.out.println("-> Sorry! This user is not found");
            }
        }
    }

    @Override
    public void removeAccount() {
        I_Menu menu = new Menu();
        menu.addItem("-> Are you sure you want to delete this account?");
        menu.addItem("1.Yes");
        menu.addItem("2.No");
        if (!permission) {
            System.out.println("-> Sorry! You are not allowed to access this function");
        } else {
            menu.showMenu();
            int choice = Utils.getInt("Enter your choice <1 or 2>: ", 1, 2);
            switch (choice) {
                case 1:
                    System.out.println("-> Delete " + this.get(activeAccountIndex).getAccountName() + " successfully!!");
                    this.remove(activeAccountIndex);
                    setPermission(false);
                    break;
                case 2:
                    System.out.println("-> Delete " + this.get(activeAccountIndex).getAccountName() + " failed!");
                    break;
            }
        }
    }

    @Override
    public void saveAccountToDatabase(String fileName) {
        try {
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            this.forEach((x) -> {
                pw.println(x.getAccountId() + "-" + x.getAccountName() + "-" + encrypt(x.getPassword()));
            });
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void saveMoneyToDatabase(String fileName) {
        try {
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            this.forEach((x) -> {
                pw.println(x.getMoney() + "-" + x.getAccountName() + "-" + x.getAccountId());
            });
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void getDataFromDatabase(String firstFileName, String secondFileName) throws IOException {
        try {
            File f = new File(firstFileName);
            File f2 = new File(secondFileName);
            if (!f.exists()) {
                System.out.println("-> File " + firstFileName + " is not found!");
                System.exit(0);
            } else if (!f2.exists()) {
                System.out.println("-> File " + secondFileName + " is not found!");
                System.exit(0);
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            FileReader fr2 = new FileReader(f2);
            BufferedReader bf2 = new BufferedReader(fr2);
            String details;
            String details2;
            while ((details = bf.readLine()) != null && (details2 = bf2.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, "-");
                StringTokenizer stk2 = new StringTokenizer(details2, "-");
                String accountId = stk.nextToken();
                String accountName = stk.nextToken();
                String password = decrypt(stk.nextToken());
                int money = Integer.parseInt(stk2.nextToken());
                Account x = new Account(accountId, accountName, password, money);
                this.add(x);
            }
            bf.close();
            bf2.close();
            fr.close();
            fr2.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public String encrypt(String plainText) {
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            int d = (int) c + CRYPT_KEY;
            c = (char) d;
            cipherText += Character.toString(c);
        }
        return cipherText;
    }

    @Override
    public String decrypt(String cipherText) {
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            int d = (int) c - CRYPT_KEY;
            c = (char) d;
            plainText += Character.toString(c);
        }
        return plainText;
    }

    @Override
    public void printCurrentUser() {
        if (permission) {
            System.out.println("Current user: " + this.get(activeAccountIndex).getAccountName());
        }
    }
}
