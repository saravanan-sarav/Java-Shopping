package com.codewithashith.view;

import com.codewithashith.models.Category;
import com.codewithashith.utils.StringUtils;

import java.util.ArrayList;

import static com.codewithashith.utils.Utils.println;

public class CategoryPage {
    public void printMenu(ArrayList<Category> categories) {
        try {
            println("#---------------------#");
            println(StringUtils.CATEGORY_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Category category : categories) {
            println(category.getId() + ". " + category.getCategoryName());
        }
        println(StringUtils.BACK_OPTION);
    }
}
