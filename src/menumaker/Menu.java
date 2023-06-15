package menumaker;

import static java.lang.System.out;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

public class Menu {

    static final String  DEFAULT_MENU_NAME = "Menu";


    public enum InputType {
        STRING,
        INTEGER,
        DOUBLE,
        BOOLEAN,
        DATE,
        FILE
    }

    public interface MenuChangeListener {
        void onMenuChanged(String key);
    }



    private Menu parentMenu;
    private List<MenuItem> menuItems;
    private Scanner scanner;
    private static Map<String, String> values;
    private String menuName = DEFAULT_MENU_NAME;
 



    public Menu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        this.menuItems = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.values = new HashMap<>();
   
    }

    public Menu(Menu parentMenu, String menuName) {
        this.parentMenu = parentMenu;
        this.menuItems = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.values = new HashMap<>();
        this.menuName = menuName;
    
    }
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setValue(String key, String value) {
        values.put(key, value);

    }


    public String getValue(String key) {
        return values.get(key);
    }
      public Map getValues() {
        return values;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = readUserChoice();

            if (choice >= 1 && choice <= menuItems.size()) {
                MenuItem menuItem = menuItems.get(choice - 1);
                if (menuItem.hasSubMenu()) {
                    Menu subMenu = menuItem.getSubMenu();
                    subMenu.run();
                } else {
                    Runnable action = menuItem.getAction();
                    if (action != null) {
                        action.run();
                    }
                }
            } else if (choice == menuItems.size() + 1) {
                exit = true;
            }
        }
    }

    public void close() {
        scanner.close();
    }

    public String getUserInputString() {
        return scanner.nextLine();
    }

    public int getUserInputInt() {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            out.println("Invalid input. Please enter a valid number.");
            out.print("Enter your choice: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    public double getUserInputDouble() {
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            out.println("Invalid input. Please enter a valid number.");
            out.print("Enter your choice: ");
        }
        double choice = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    public boolean getUserInputBoolean() {
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "true":
            case "t":
                return true;
            case "false":
            case "f":
                return false;
            default:
                out.println("Invalid input. Please enter 'true' or 'false'.");
                return getUserInputBoolean();
        }
    }

    public Date getUserInputDate() {
        out.print("Enter date (DD/MM/YYYY): ");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            out.println("Invalid date format. Please enter a date in the format DD/MM/YYYY.");
            return getUserInputDate();
        }
    }

    public File getUserInputFile() {
        out.print("Enter file path or select a file: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        while (!file.exists()) {
            out.println("Invalid file path. Please enter a valid file path or select a file.");
            out.println("You wrote: "+filePath);
            out.print("Enter file path or select a file: ");
            filePath = scanner.nextLine();
            file = new File(filePath);
        }
        return file;
    }

    public void addMenuItemWithKVInput(InputType expectedType, String description, String key) {
        MenuItem menuItem = new MenuItem("Set " + description, () -> {
            out.print("Enter " + description + ": ");
            String value;
            switch (expectedType) {
                case STRING:
                    value = getUserInputString();
                    break;
                case INTEGER:
                    value = Integer.toString(getUserInputInt());
                    break;
                case DOUBLE:
                    value = Double.toString(getUserInputDouble());
                    break;
                case BOOLEAN:
                    value = Boolean.toString(getUserInputBoolean());
                    break;
                case DATE:
                    value = getUserInputDate().toString();
                    break;
                case FILE:
                    value = getUserInputFile().getPath();
                    break;
                default:
                    value = getUserInputString();
                    break;
            }
            out.println("Adding "+value+"With key "+key);
            setValue(key, value);

            out.println(getValue(key));
        });
        addMenuItem(menuItem);
    }

    private void displayMenu() {
        out.println("\n--- " + this.menuName + " ---");
        int index = 1;
        for (MenuItem menuItem : menuItems) {
            if (menuItem.isVisible()) {
                out.println(index + ". " + menuItem.getDescription());
                index++;
            }
        }
        if (parentMenu != null) {
            out.println(index + ". Back");
        } else {
            out.println(index + ". Exit");
        }
        out.print("Enter your choice: ");
    }

    private int readUserChoice() {
        return getUserInputInt();
    }


    //Getters and Setters Start:

    /**
     * @return Menu return the parentMenu
     */
    public Menu getParentMenu() {
        return parentMenu;
    }

    /**
     * @param parentMenu the parentMenu to set
     */
    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    /**
     * @return List<MenuItem> return the menuItems
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * @param menuItems the menuItems to set
     */
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * @return Scanner return the scanner
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * @param scanner the scanner to set
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * @return String return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


}
