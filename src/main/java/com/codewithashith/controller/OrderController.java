package com.codewithashith.controller;

import com.codewithashith.controller.impl.IOrderController;
import com.codewithashith.models.Cart;
import com.codewithashith.models.CartProduct;
import com.codewithashith.models.Product;
import com.codewithashith.models.User;

import java.io.FileWriter;
import java.io.IOException;

import static com.codewithashith.utils.FileUtil.getFilePath;
import static com.codewithashith.utils.UserUtils.getLoggedInUser;
import static com.codewithashith.utils.Utils.println;

public class OrderController implements IOrderController {

    private final HomeController homeController;

    public OrderController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void checkout() {
        User loggedInUser = getLoggedInUser();

        try {
            FileWriter fileWriter = new FileWriter(getFilePath() + System.currentTimeMillis() + ".txt");
            fileWriter.write("Your Order are:");
            fileWriter.write("\n");

            double total = 0;
            for (CartProduct cartProduct : loggedInUser.getUserCart().getCartProducts()) {
                total += cartProduct.getCount() * cartProduct.getProduct().getPrice();
                fileWriter.write(cartProduct.getProduct().getTitle() + " x " + cartProduct.getCount() + " = Rs. " + cartProduct.getProduct().getPrice() * cartProduct.getCount());
                fileWriter.write("\n");
            }
            fileWriter.write("Total - Rs. " + total);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLoggedInUser().setUserCart(null);
        homeController.printMenu();
    }
}
