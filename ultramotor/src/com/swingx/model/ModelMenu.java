
package com.swingx.model;

import javax.swing.Icon;

public class ModelMenu {
    private String menuName;
    private Icon icon;
    private String[] subMenu;

    public ModelMenu(String menuName, Icon icon) {
        this.menuName = menuName;
        this.icon = icon;
    }

    public ModelMenu(Icon icon, String menuName, String...subMenu) {
        this.menuName = menuName;
        this.icon = icon;
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

    public void setSubMenu(String...subMenu) {
        this.subMenu = subMenu;
    }

  
    
}
