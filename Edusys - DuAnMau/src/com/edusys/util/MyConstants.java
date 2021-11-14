package com.edusys.util;

/**
 *
 * @author nghitvps19009
 */
public class MyConstants {

    //field pattern validation
    public final static String NAME_PATTERN = "^[\\p{L}\\s]{1,50}$"; //tên kí tự unicode và khoảng trắng
    public final static String PHONE_NUMBER_PATTERN = "[\\d]{10}$"; //số, từ 10 đến 15 số
    public final static String EMAIL_PATTERN = "^[\\w_\\.]+@[\\w\\.]+\\w$"; //chữ, số,-_ và phải có @
    public final static String ADDRESS_PATTERN = "^[\\p{L}\\w\\s,-]{1,50}$"; //kí tự unicode, số, khoảng trắng -
    public final static String ID_PATTERN = "[\\w]{1,50}$"; //kí tự chữ hoặc số
    public final static String DATE_PATTERN = "dd/MM/yyyy";
    
    
    
    
    
//    public final static java.awt.Color PURPLE_COLOR = new java.awt.Color(89, 34, 144);
//    public final static java.awt.Color PURPLE_COLOR = new java.awt.Color(154, 38, 173);
    public final static java.awt.Color PURPLE_COLOR = new java.awt.Color(150, 34, 200);
    public final static java.awt.Color GRAY_COLOR = new java.awt.Color(142, 142, 142);
//    public final static java.awt.Color PURPLE_COLOR = new java.awt.Color(149, 59, 195);
//    public final static java.awt.Color PURPLE_COLOR = new java.awt.Color(113,27,165);
//    public final static java.awt.Color PURPLE_COLOR = new java.awt.Color(135, 63, 179);
    public final static java.awt.Color LIGHT_GRAY_COLOR = new java.awt.Color(240, 240, 240);
    public final static java.awt.Color SECONDARY_PURPLE_COLOR = new java.awt.Color(102, 0, 204);
    public final static java.awt.Color BLACK_COLOR = new java.awt.Color(0, 0, 0);
    public final static java.awt.Color RED_COLOR = new java.awt.Color(255, 0, 0);
    public final static java.awt.Color WHITE_COLOR = new java.awt.Color(255, 255, 255);
    //NAV_COLOR 154, 38, 173
    public final static String FONT_NAME = "Segoe UI";
    public final static java.awt.Font DEFAULT_FONT = new java.awt.Font(FONT_NAME, 0, 14);
}
