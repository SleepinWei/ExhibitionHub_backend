package com.exhibition.util;

/**
 * @Author: JudyLou
 * @Date: 2023/4/18 16:25
 */
public class NumericUtil {
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
