package com.codewithashith.controller;

import com.codewithashith.controller.impl.IHomeController;

import static com.codewithashith.utils.Utils.println;

public class HomeController implements IHomeController {

    @Override
    public void printMenu() {
        println("HI");
    }
}
