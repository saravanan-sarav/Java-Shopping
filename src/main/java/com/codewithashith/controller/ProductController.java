package com.codewithashith.controller;

import com.codewithashith.controller.impl.IProductController;
import com.codewithashith.view.ProductsPage;

public class ProductController implements IProductController {

    ProductsPage productsPage;

    public ProductController() {
        productsPage = new ProductsPage();
    }

    @Override
    public void showProducts(int validChoice) {
//        productsPage.printProducts();
    }
}
