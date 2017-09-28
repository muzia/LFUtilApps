package com.example.admin.lfutilapp.pattern.builder_pattern.invoke;

import com.example.admin.lfutilapp.pattern.builder_pattern.entity.HotDryNoodles;
import com.example.admin.lfutilapp.pattern.builder_pattern.entity.HotDryNoodlesWithBuilder;

/**
 * Created by lff on 2017/6/21.
 */

public class TestBuilder {
    public static void main(String args[]){
        HotDryNoodles hotDryNoodles=new HotDryNoodles(true,true,true,true);
        System.out.println("hotDryNoodles:"+hotDryNoodles);
        /**
         * 弹性地构造他们需要的对象.
         */
        HotDryNoodlesWithBuilder hotDryNoodlesWithBuilder=new HotDryNoodlesWithBuilder.Builder().withChili().withParsley().withSauerkraut().build();
        System.out.println("HotDryNoodlesWithBuilder"+hotDryNoodlesWithBuilder);
    }
}
