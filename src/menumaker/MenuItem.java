package menumaker;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MenuItem {

 
    private String description;
    private Menu subMenu;
    private Runnable action;
    private boolean isVisible;
   

    /**
     * Constructs a menu item with a sub-menu.
     *
     * @param description The description of the menu item.
     * @param subMenu     The sub-menu associated with the menu item.
     */
    public MenuItem(String description, Menu subMenu) {
        this.description = description;
        this.subMenu = subMenu;
        this.action = null;
        this.isVisible = true;
       
    }

    /**
     * Constructs a menu item with an action.
     *
     * @param description The description of the menu item.
     * @param action      The action associated with the menu item.
     */

    
    public MenuItem(String description, Runnable action) {
        this.description = description;
        this.subMenu = null;
        this.action = action;
        this.isVisible = true;
        
    }

 


    /**
     * Returns the description of the menu item.
     *
     * @return The description of the menu item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the menu item.
     *
     * @param description The description to set for the menu item.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the sub-menu associated with the menu item.
     *
     * @return The sub-menu associated with the menu item.
     */
    public Menu getSubMenu() {
        return subMenu;
    }

    /**
     * Sets the sub-menu associated with the menu item.
     *
     * @param subMenu The sub-menu to set for the menu item.
     */
    public void setSubMenu(Menu subMenu) {
        this.subMenu = subMenu;
    }

    /**
     * Returns the action associated with the menu item.
     *
     * @return The action associated with the menu item.
     */
    public Runnable getAction() {
        return action;
    }

    /**
     * Sets the action associated with the menu item.
     *
     * @param action The action to set for the menu item.
     */
    public void setAction(Runnable action) {
        this.action = action;
    }

    /**
     * Checks if the menu item is visible.
     *
     * @return {@code true} if the menu item is visible, {@code false} otherwise.
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Sets the visibility of the menu item.
     *
     * @param visible {@code true} to make the menu item visible, {@code false} to hide it.
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Check if this is a submenu.
     *
     */
    public boolean hasSubMenu() {
    return subMenu != null;
    }


}
