package com.ultramotor.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class MyConstants {

    public static Map<String, Color> colorMap = new HashMap<>();

    static {
        colorMap.put("Trắng", Color.white);
        colorMap.put("Đen", Color.black);
        colorMap.put("Xanh", Color.blue);
        colorMap.put("Vàng", Color.yellow);
        colorMap.put("Đỏ", Color.red);
        colorMap.put("Xám", Color.GRAY);
    }
    
    
    //field pattern validation
    public final static String NAME_PATTERN = "^[\\p{L}\\s]{1,50}$"; //tên kí tự unicode và khoảng trắng
    public final static String PHONE_NUMBER_PATTERN = "[\\d]{10}$"; //số, từ 10 đến 15 số
    public final static String EMAIL_PATTERN = "^[\\w_\\.]+@[\\w\\.]+\\w$"; //chữ, số,-_ và phải có @
    public final static String ADDRESS_PATTERN = "^[\\p{L}\\w\\s,-]{1,50}$"; //kí tự unicode, số, khoảng trắng -
    public final static String ID_PATTERN = "[\\w]{1,50}$"; //kí tự chữ hoặc số
    public final static String DATE_PATTERN = "dd-MM-yyyy";

}
