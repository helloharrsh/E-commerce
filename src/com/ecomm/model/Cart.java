package com.ecomm.model;

import com.ecomm.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private String sessionId;
    private List<CartItem> cartItems;

    public Cart(String sessionId) {
        this.sessionId = sessionId;
        this.cartItems = new ArrayList<>();
    }

    // Other methods for cart functionality (add, update, remove, get)

    // Getters and Setters

    public String getSessionId() {
        return sessionId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
