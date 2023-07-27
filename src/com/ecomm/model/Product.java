package com.ecomm.model;

public class Product {
    private String id;
    private String name;
    private String inventoryStatus;
    private double mrpPrice;
    private double discount;
    private Integer maxQuantity;

    public Product(String id, String name, String inventoryStatus, double mrpPrice, double discount, Integer maxQuantity) {
        this.id = id;
        this.name = name;
        this.inventoryStatus = inventoryStatus;
        this.mrpPrice = mrpPrice;
        this.discount = discount;
        this.maxQuantity = maxQuantity;
    }

    // Getters and Setters for all fields

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public double getMrpPrice() {
        return mrpPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }
}