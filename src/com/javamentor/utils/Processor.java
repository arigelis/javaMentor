package com.javamentor.utils;

import java.io.IOException;
import java.util.*;

public class Processor {

    private static String romanDigitsOneLine = "IiVvXx";
    private static String arabicDigitsOneLine = "0123456789";
    private static Map<String, Integer> equality = new HashMap<String, Integer>() {{
        put("I", 1);
        put("II", 2);
        put("III", 3);
        put("IV", 4);
        put("V", 5);
        put("VI", 6);
        put("VII", 7);
        put("VIII", 8);
        put("IX", 9);
        put("X", 10);
        put("XI", 11);
        put("XII", 12);
        put("XIII", 13);
        put("XIV", 14);
        put("XV", 15);
        put("XVI", 16);
        put("XVII", 17);
        put("XVIII", 18);
        put("XIX", 19);
        put("XX", 20);

        //Сделал до 20, думаю достаточно)

    }};

    public static boolean containsRomanDigits(String exp) {
        boolean res = false;
        for (int i = 0; i < exp.length(); i++) {
            if (romanDigitsOneLine.contains(String.valueOf(exp.charAt(i)))) {
                res = true;
                break;
            }
        }

        return res;
    }

    public static boolean containsArabicDigits(String exp) {
        boolean res = false;
        for (int i = 0; i < exp.length(); i++) {
            if (arabicDigitsOneLine.contains(String.valueOf(exp.charAt(i)))) {
                res = true;
                break;
            }
        }

        return res;
    }

    public static boolean isNumeric(String strNum) {
        try {
            Integer i = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static Integer convertToRoman(String exp) throws IOException {
        if (equality.get(exp.toUpperCase()) == null) {
            throw new IOException("Incorrect data");
        }
        return equality.get(exp.toUpperCase());
    }

    public static Optional<String> reverseConvert(Integer value) {
        return equality
                .entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .findAny();
    }

}
