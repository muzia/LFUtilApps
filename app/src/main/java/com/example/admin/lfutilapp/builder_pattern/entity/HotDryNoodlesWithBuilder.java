package com.example.admin.lfutilapp.builder_pattern.entity;

/**
 * Created by lff on 2017/6/21.
 */

public class HotDryNoodlesWithBuilder {
    /**
     * Buidler模式, 是一种创建型的设计模式.
      通常用来将一个复杂的对象的构造过程分离, 让使用者可以根据需要选择创建过程.
     */
    private boolean addShallot;
    private boolean addParsley;
    private boolean addChili;
    private boolean addSauerkraut;
    public HotDryNoodlesWithBuilder(Builder builder) {
        this.addShallot = builder.addShallot;
        this.addParsley = builder.addParsley;
        this.addChili = builder.addChili;
        this.addSauerkraut = builder.addSauerkraut;
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

        return builder.toString();
    }

    /**
     *  Builder常常作为实际产品的静态内部类来实现(提高内聚性).
     */
    public static class Builder {

        private boolean addShallot;
        private boolean addParsley;
        private boolean addChili;
        private boolean addSauerkraut;

        public Builder() {

        }

        public Builder withShallot() {
            this.addShallot = true;
            return this;
        }

        public Builder withParsley() {
            this.addParsley = true;
            return this;
        }

        public Builder withChili() {
            this.addChili = true;
            return this;
        }

        public Builder withSauerkraut() {
            this.addSauerkraut = true;
            return this;
        }

        public HotDryNoodlesWithBuilder build() {
            return new HotDryNoodlesWithBuilder(this);
        }
    }
}
