package controllers;

import java.util.ArrayList;
import utils.Utils;
import dto.I_Menu;

//@author SE173593
public class Menu extends ArrayList<String> implements I_Menu {
    
    public Menu() {
        super();
    }

    @Override
    public void addItem(String content) {
        this.add(content);
    }

    @Override
    public int getChoice(int min, int max) {
        int choice;
        choice = Utils.getInt("Enter your choice <" + min + " to " + max + ">: ", min, max);
        return choice;
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }
}
