package com.example.admin.lfutilapp.builderTest.invoke;

import com.example.admin.lfutilapp.builderTest.entity.HotDryNoodles;
import com.example.admin.lfutilapp.builderTest.entity.HotDryNoodlesWithBuilder;

/**
 * Created by admin on 2017/6/21.
 */

public class TestBuilder {
    public static void main(String args[]){
        HotDryNoodles hotDryNoodles=new HotDryNoodles(true,true,true,true);
        System.out.println("hotDryNoodles:"+hotDryNoodles);
        HotDryNoodlesWithBuilder hotDryNoodlesWithBuilder=new HotDryNoodlesWithBuilder.Builder().withChili().withParsley().withSauerkraut().build();
        System.out.println("HotDryNoodlesWithBuilder"+hotDryNoodlesWithBuilder);
    }
}
