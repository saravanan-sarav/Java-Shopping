package com.codewithashith.controller;

import com.codewithashith.controller.impl.IAuthController;
import com.codewithashith.models.Role;
import com.codewithashith.models.User;
import com.codewithashith.utils.AppException;
import com.codewithashith.utils.StringUtils;
import com.codewithashith.view.AuthPage;
import com.codewithashith.view.LoginPage;
import com.codewithashith.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.codewithashith.utils.AppInput.enterInt;
import static com.codewithashith.utils.AppInput.enterString;
import static com.codewithashith.utils.FileUtil.getCredentialsFile;
import static com.codewithashith.utils.UserUtils.setLoggedInUser;
import static com.codewithashith.utils.Utils.println;

public class AuthController implements IAuthController {

    private final HomeController homeController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    private final AuthPage authPage;

    public AuthController() {
        authPage = new AuthPage();
        homeController = new HomeController(this);
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
    }

    @Override
    public void authMenu() {
        authPage.printAuthMenu();
        int choice;
        try {
            choice = enterInt(StringUtils.ENTER_CHOICE);
            if (choice == 99) {
                authPage.printThankYou();
                System.exit(0);
            } else {
                if (choice == 1) {
                    login();
                } else if (choice == 2) {
                    register();
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    @Override
    public void login() {
        String email, password;
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);

        User user = validateUser(email, password);
        if (user != null) {
            setLoggedInUser(user);
            homeController.printMenu();
        } else {
            loginPage.printInvalidCredentials();
            authMenu();
        }
    }


    @Override
    public void register() {
        String name, email, password, c_password;
        name = enterString(StringUtils.ENTER_NAME);
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);
        c_password = enterString(StringUtils.ENTER_PASSWORD_AGAIN);

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
