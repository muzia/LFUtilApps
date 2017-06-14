package com.example.admin.lfutilapp;

/**
 * Created by admin on 2017/6/13.
 */

public class Test {
    public static void main(String args[]) {
        int n = 3;
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
        }
    }

}
