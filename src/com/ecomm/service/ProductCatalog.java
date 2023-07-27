package com.ecomm.service;
import com.ecomm.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> productList;

    public ProductCatalog() {
        this.productList = new ArrayList<>();
}

    public void loadProductsFromCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip the header row
                }
                String[] data = line.split(",");
                if (data.length == 6) {
                    String id = data[0];
                    String name = data[1];
                    String inventoryStatus = data[2];
                    double mrpPrice = Double.parseDouble(data[3]);
                    double discount = Double.parseDouble(data[4]);
                    Integer maxQuantity = data[5].isEmpty() ? null : Integer.parseInt(data[5]);
                    Product product = new Product(id, name, inventoryStatus, mrpPrice, discount, maxQuantity);
                    productList.add(product);
                }
            }
        } catch (IOException e) {
            throw new IOException("Failed to read product data from CSV file: " + e.getMessage());
        }
    }

    public List<Product> searchProducts(String searchTerm) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    // Other methods for product catalog functionality (if needed)
}