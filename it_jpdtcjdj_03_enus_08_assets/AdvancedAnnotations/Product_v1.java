package com.skillsoft.junit;

public class Product {

    String brandName;
    String model;
    Integer id;
    Double price;
    String category;

    public Product(String brandName, String model, int id, double price, String category) {

        this.brandName = brandName;
        this.model = model;
        this.id = id;
        this.price = price;
        this.category = category;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModel() {
        return model;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
