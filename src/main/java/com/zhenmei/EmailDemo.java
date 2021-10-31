package com.zhenmei;

import com.github.houbb.email.bs.EmailBs;

public class EmailDemo {
    public static void main(String[] args) {
        EmailBs.auth("m5590026@163.com", "m5590026")
                .content("自定义内容")
                .sendTo("1585685035@qq.com");

    }
}
