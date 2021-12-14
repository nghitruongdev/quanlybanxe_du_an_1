package com.swingx.model;

import java.awt.event.ActionListener;
import javax.swing.Icon;

public class ModelMenu {

    private String menuName;
    private Icon icon;
    private String[] subMenu;
    private ActionListener event;

    public ModelMenu(String menuName, Icon icon, ActionListener event) {
        this.menuName = menuName;
        this.icon = icon;
        this.event = event;
        subMenu = new String[0];
    }

    public ModelMenu(Icon icon, String menuName, ActionListener event, String... subMenu) {
        this.menuName = menuName;
        this.icon = icon;
        this.event = event;
        this.subMenu = subMenu;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String[] getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String... subMenu) {
        this.subMenu = subMenu;
    }

    public ActionListener getEvent() {
        return event;
    }

    public void setEvent(ActionListener event) {
        this.event = event;
    }

    
}
