package com.codewithashith.view;

import com.codewithashith.utils.StringUtils;

import static com.codewithashith.utils.Utils.println;

public class LoginPage {
    public void printInvalidCredentials() {
        try {
            println("#---------------------#");
            println(StringUtils.INVALID_CREDENTIALS);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
