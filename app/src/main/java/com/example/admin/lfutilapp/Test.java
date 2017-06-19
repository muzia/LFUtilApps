package com.example.admin.lfutilapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2017/6/13.
 */

public class Test {
    public static boolean isInteger(String data) {
        String pattern="^([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)";
        Pattern p = Pattern
                .compile(pattern);
        Matcher m = p.matcher(data);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
    public static void main(String args[]) {
        /*int n = 3;
        int m = 4;
        int[] start = new int[]{0, 5, 2};
        int[] end = new int[]{4, 7, 8};
        int[] query = new int[]{1, 9, 4, 3};
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (start[j] >= end[j]) {
                    if (start[j] > query[i] && query[i] >= end[j]) {
                        count++;
                    }
                } else {
                    if (start[j] <= query[i] && query[i] < end[j]) {
                        count++;
                    }
                }
            }
            System.out.print("," + count);
            result[i] = count;
        }*/
        isInteger("12,3");


    }

}
