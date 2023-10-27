package com.codewithashith.controller;

import com.codewithashith.controller.impl.ICategoryController;
import com.codewithashith.models.Category;
import com.codewithashith.utils.AppException;
import com.codewithashith.utils.StringUtils;
import com.codewithashith.view.CategoryPage;

import java.util.ArrayList;

import static com.codewithashith.utils.AppInput.enterInt;
import static com.codewithashith.utils.LoadUtils.getCategories;
import static com.codewithashith.utils.Utils.println;

public class CategoryController implements ICategoryController {

    CategoryPage categoryPage;
    ProductController productController;

    public CategoryController() {
        categoryPage = new CategoryPage();
        productController = new ProductController();
    }

    @Override
    public void printMenu() {
        ArrayList<Category> categories = getCategories();
        categoryPage.printMenu(categories);

        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            int validChoice = 0;

            for (Category category : categories) {
                if (category.getId() == choice) {
                    validChoice = category.getId();
                    break;
                }
            }

            if (validChoice != 0) {
                productController.showProducts(validChoice);
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
