package com.ecomm.service;

import com.ecomm.annotations.Nullable;
import com.ecomm.model.Cart;
import com.ecomm.model.CartItem;
import com.ecomm.model.Product;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartManagement {
    private Map<String, Cart> cartMap;
    private final String cartFilePath = "carts.ser"; // Serialized file for cart information

    public CartManagement() {
        this.cartMap = new HashMap<>();
        loadCartsFromDisk();
    }

    public void addCartItem(String sessionId, Product product, int quantity) {
        // Add the given product and quantity to the cart identified by sessionId
        Cart cart = cartMap.getOrDefault(sessionId, new Cart(sessionId));
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                saveCartToDisk(cart); // Save cart to disk after updating
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
        saveCartToDisk(cart); // Save cart to disk after adding new item
    }

    // Implement the rest of the cart functionality: updateCartItem, removeCartItem, getCartItems

    private void loadCartsFromDisk() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cartFilePath))) {
                cartMap = (Map<String, Cart>) ois.readObject();
            } catch (FileNotFoundException | ClassNotFoundException | IOException e) {
                // Handle file not found or corrupted, if any
                cartMap = new HashMap<>();
            }
        });
        executor.shutdown();
    }

    private void saveCartToDisk(Cart cart) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cartFilePath))) {
                oos.writeObject(cartMap);
            } catch (IOException e) {
                // Handle file writing errors, if any
            }
        });
        executor.shutdown();
    }
}