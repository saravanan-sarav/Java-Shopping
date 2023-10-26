package com.codewithashith.controller;

import com.codewithashith.controller.impl.IAuthController;
import com.codewithashith.models.Role;
import com.codewithashith.models.User;
import com.codewithashith.utils.AppException;
import com.codewithashith.utils.StringUtil;
import com.codewithashith.view.LoginPage;
import com.codewithashith.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.codewithashith.utils.AppInput.enterInt;
import static com.codewithashith.utils.AppInput.enterString;
import static com.codewithashith.utils.FileUtil.getCredentialsFile;
import static com.codewithashith.utils.Utils.println;

public class AuthController implements IAuthController {

    private final HomeController homeController;
    private final AppController appController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    public AuthController(AppController appController) {
        this.appController = appController;
        homeController = new HomeController();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
    }

    @Override
    public void authMenu() {
        appController.printAuthMenu();
        int choice;
        try {
            choice = enterInt(StringUtil.ENTER_CHOICE);
            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    @Override
    public void login() {
        String email, password;
        email = enterString(StringUtil.ENTER_EMAIL);
        password = enterString(StringUtil.ENTER_PASSWORD);

        User user = validateUser(email, password);
        if (user != null) {
            homeController.printMenu();
        } else {
            loginPage.printInvalidCredentials();
            authMenu();
        }
    }


    @Override
    public void register() {
        String name, email, password, c_password;
        name = enterString(StringUtil.ENTER_NAME);
        email = enterString(StringUtil.ENTER_EMAIL);
        password = enterString(StringUtil.ENTER_PASSWORD);
        c_password = enterString(StringUtil.ENTER_PASSWORD_AGAIN);

        if (password.equals(c_password)) {
            try {
                FileWriter csvWriter = new FileWriter(getCredentialsFile(), true);
                int id = (int) (Math.random() * 100);
                csvWriter.append("\n");
                csvWriter.append(id + "," + name + "," + email + "," + password);
                csvWriter.flush();
                csvWriter.close();
                registerPage.printRegistrationSuccessful();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            registerPage.passwordMisMatch();
        }
        authMenu();
    }

    @Override
    public void logout() {

    }

    private User validateUser(String email, String password) {
        try {
            Scanner scanner = new Scanner(getCredentialsFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                if (!value.startsWith("id")) {
                    String[] userArray = value.split(",");
                    if (userArray[2].equals(email) && userArray[3].equals(password)) {
                        User user = new User();
                        user.setId(Integer.parseInt(userArray[0]));
                        user.setName(userArray[1]);
                        user.setEmail(userArray[2]);
                        user.setPassword(userArray[3]);
                        if (user.getEmail().equals("admin@admin.com"))
                            user.setRole(Role.ADMIN);
                        else
                            user.setRole(Role.USER);
                        return user;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void invalidChoice(AppException e) {
        println(e.getMessage());
        authMenu();
    }
}
