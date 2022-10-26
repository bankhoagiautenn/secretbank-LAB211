package view;

import controllers.List;
import controllers.Menu;
import java.io.IOException;
import dto.I_List;
import dto.I_Menu;

//@author SE1735933
public class AccountManagement {

    public static void main(String[] args) throws IOException {
        String bankFile = "bank.dat";
        String userFile = "user.dat";
        I_Menu menu = new Menu();
        I_List list = new List();
        list.getDataFromDatabase(userFile, bankFile);
        menu.addItem("");
        menu.addItem("========================== SECRET BANK ==========================");
        menu.addItem("|           1.Create new account                                |");
        menu.addItem("|           2.Login account                                     |");
        menu.addItem("|           3.Withdraw money                                    |");
        menu.addItem("|           4.Deposit money                                     |");
        menu.addItem("|           5.Transfer money                                    |");
        menu.addItem("|           6.Remove account                                    |");
        menu.addItem("|           7.Exit program                                      |");
        menu.addItem("=================================================================");        
        menu.addItem("");
        int choice;
        boolean flag = false;
        do {
            menu.showMenu();
            list.printCurrentUser();
            choice = menu.getChoice(1, 7);
            switch (choice) {
                case 1:
                    list.createAccount();
                    list.saveAccountToDatabase(userFile);
                    list.saveMoneyToDatabase(bankFile);
                    break;
                case 2:
                    list.loginAccount();
                    break;
                case 3:
                    list.withdrawMoney();
                    list.saveMoneyToDatabase(bankFile);
                    break;
                case 4:
                    list.depositMoney();
                    list.saveMoneyToDatabase(bankFile);
                    break;
                case 5:
                    list.transferMoney();
                    list.saveMoneyToDatabase(bankFile);
                    break;
                case 6:
                    list.removeAccount();
                    list.saveAccountToDatabase(userFile);
                    list.saveMoneyToDatabase(bankFile);
                    break;
                case 7:
                    System.out.println("Thank you and hope to see you again!");
                    System.exit(0);
                    break;
            }
        } while (choice >= 1 && choice <= 7 && !flag);
    }
}
