package com.example.admin.lfutilapp.pattern.decorate_pattern;

/**
 * Created by admin on 2017/7/25.
 */

/**
 * ConcreteComponent:具体组件
 */
public class AndroidCoder extends Coder {
    @Override
    public void dev() {
        System.out.println("AndroidCoder,开发公司的安卓项目");
    }
}
