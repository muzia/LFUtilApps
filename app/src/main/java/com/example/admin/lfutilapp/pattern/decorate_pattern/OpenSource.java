package com.example.admin.lfutilapp.pattern.decorate_pattern;

/**
 * Created by admin on 2017/7/25.
 */

/**
 * Decorator:装饰者抽象
 */
public  abstract class  OpenSource extends Coder {
    private Coder mCoder;

    public OpenSource(Coder coder) {//注意这里,装饰者会持有待装饰者组件的实例
        mCoder = coder;
    }

    @Override
    public void dev() {
        mCoder.dev();
    }

    public abstract void fixBug();//修复开源项目中的bug
}
