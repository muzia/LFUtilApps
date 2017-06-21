package com.example.admin.lfutilapp.builderTest.entity;

/**
 * Created by admin on 2017/6/21.
 */

public class HotDryNoodles {
    private boolean addShallot;
    private boolean addParsley;
    private boolean addChili;
    private boolean addSauerkraut;

   //构造对象时有大量可选参数
    public HotDryNoodles(boolean addShallot, boolean addParsley, boolean addChili, boolean addSauerkraut) {
        this.addShallot = addShallot;
        this.addParsley = addParsley;
        this.addChili = addChili;
        this.addSauerkraut = addSauerkraut;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("A bowl of hot-dry noodles has:");
        if (this.addShallot) {
            builder.append("葱花.");
        }

        if (this.addParsley) {
            builder.append("香菜.");
        }

        if (this.addChili) {
            builder.append("辣椒.");
        }

        if (this.addSauerkraut) {
            builder.append("酸菜.");
        }
        return  builder.toString();
    }
    //增加配料台 Builder
}
