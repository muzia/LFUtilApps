package com.example.admin.lfutilapp.pattern.decorate_pattern;

/**
 * Created by admin on 2017/7/25.
 */

public class TestDector {
    public  static  void main(String args[]){
        AndroidCoder androidCoder = new AndroidCoder();//安卓开发者
        androidCoder.dev();//以前每天上班就是写代码

        //装饰者模式之后
        GithubFixer githubFixer = new GithubFixer(androidCoder);//公司项目没那么紧,每天开发之前可以先维护下开源项目
        githubFixer.dev();
    }
}
