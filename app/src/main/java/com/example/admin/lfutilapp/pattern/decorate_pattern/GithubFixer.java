package com.example.admin.lfutilapp.pattern.decorate_pattern;

/**
 * Created by admin on 2017/7/25.
 */

public class GithubFixer extends OpenSource {
    public GithubFixer(Coder coder) {
        super(coder);
    }

    @Override
    public void fixBug() {
        System.out.println("修复bug");
    }

    @Override
    public void dev() {
        fixBug();//上班之前先看看开源项目有没有bug需要修复
        super.dev();
    }
}
