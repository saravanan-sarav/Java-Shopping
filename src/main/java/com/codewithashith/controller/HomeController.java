package com.codewithashith.controller;

import com.codewithashith.controller.impl.IHomeController;
import com.codewithashith.utils.AppException;
import com.codewithashith.utils.StringUtils;
import com.codewithashith.view.HomePage;

import static com.codewithashith.utils.AppInput.enterInt;
import static com.codewithashith.utils.UserUtils.setLoggedInUser;
import static com.codewithashith.utils.Utils.println;

public class HomeController implements IHomeController {

    HomePage homePage;
    AuthController authController;
    CategoryController categoryController;

    public HomeController(AuthController authController) {
        homePage = new HomePage();
        this.authController = authController;
        categoryController = new CategoryController();
    }

    @Override
    public void printMenu() {
        homePage.printMenu();
        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            if (choice == 1) {
                categoryController.printMenu();
            } else if (choice == 2) {

            } else if (choice == 3) {

            } else if (choice == 4) {

            } else if (choice == 5) {
                setLoggedInUser(null);
                authController.authMenu();
            } else {
                invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        printMenu();
    }
}
