package com.codewithashith.models;

import com.codewithashith.utils.AppException;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String title;
    private String description;
    private double price;
    private int stocks;
    private Category category;

    public Product(int id, String title, String description, double price, int stocks, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stocks = stocks;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws AppException {
        if (price <= 0) {
            throw new AppException("Price Cannot be negative");
        }
        this.price = price;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
