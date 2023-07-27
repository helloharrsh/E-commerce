package com.ecomm;

import com.ecomm.model.Product;
import com.ecomm.service.ProductCatalog;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductCatalog productCatalog = new ProductCatalog();
        try {
            productCatalog.loadProductsFromCSV("products.csv");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            // Gracefully fail the program if there's an issue with the file path
            // or the CSV file is not properly formatted
            return;
        }

        // Test product search functionality
        String searchTerm = "Product";
        List<Product> searchResults = productCatalog.searchProducts(searchTerm);
        if (searchResults.isEmpty()) {
            System.out.println("No products found matching the search term: " + searchTerm);
        } else {
            System.out.println("Products matching the search term '" + searchTerm + "':");
            printProductList(searchResults);
        }
    }

    private static void printProductList(List<Product> productList) {
        System.out.println("ID\tName\tInventoryStatus\tMRP Price\tDiscount\tMax Quantity");
        for (Product product : productList) {
            System.out.println(
                    product.getId() + "\t" +
                            product.getName() + "\t" +
                            product.getInventoryStatus() + "\t" +
                            product.getMrpPrice() + "\t" +
                            product.getDiscount() + "\t" +
                            (product.getMaxQuantity() != null ? product.getMaxQuantity() : "N/A")
            );
        }
    }
}
