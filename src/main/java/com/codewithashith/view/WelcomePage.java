package com.codewithashith.view;

import com.codewithashith.utils.StringUtils;

import static com.codewithashith.utils.Utils.println;

public class WelcomePage {
    public void welcome() {
        try {
            println(StringUtils.WELCOME_MESSAGE);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
