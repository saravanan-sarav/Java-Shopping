package com.codewithashith.view;

import com.codewithashith.utils.StringUtil;

import static com.codewithashith.utils.Utils.println;

public class WelcomePage {
    public void welcome() {
        try {
            println(StringUtil.WELCOME_MESSAGE);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAuthMenu() {
        println(StringUtil.AUTH_MENU);
    }
    void check(){
        System.out.println();
    }
}
