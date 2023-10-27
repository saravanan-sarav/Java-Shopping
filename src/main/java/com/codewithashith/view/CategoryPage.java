package com.codewithashith.view;

import com.codewithashith.models.Category;
import com.codewithashith.utils.StringUtils;

import java.util.ArrayList;

import static com.codewithashith.utils.Utils.println;

public class CategoryPage {
    public void printMenu(ArrayList<Category> categories) {
        println(StringUtils.CATEGORY_MENU);
        for (Category category : categories) {
            println(category.getId() + ". " + category.getCategoryName());
        }
    }
}
