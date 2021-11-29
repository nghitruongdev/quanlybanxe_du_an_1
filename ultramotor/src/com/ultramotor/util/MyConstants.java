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

}
