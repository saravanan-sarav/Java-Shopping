package com.codewithashith;

import com.codewithashith.controller.AppController;
import com.codewithashith.controller.impl.IAppController;
import com.codewithashith.models.Role;
import com.codewithashith.models.User;
import com.codewithashith.utils.LoadUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AppController appController = new AppController();
        LoadUtils.load();
        appController.init();
    }
}