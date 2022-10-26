package controllers;

import java.util.ArrayList;
import utils.Utils;
import dto.I_Menu;

//@author SE173593
public class Menu extends ArrayList<String> implements I_Menu {

    private final ArrayList<String> menuList = new ArrayList<>();

    public Menu() {
        super();
    }

    @Override
    public void addItem(String s) {
        menuList.add(s);
    }

    @Override
    public int getChoice(int min, int max) {
        int choice;
        choice = Utils.getInt("Enter your choice <" + min + " to " + max + ">: ", min, max);
        return choice;
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(menuList.get(i));
        }
    }
}
