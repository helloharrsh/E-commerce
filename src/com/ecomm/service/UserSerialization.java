package com.ecomm.service;

import com.ecomm.annotations.Nullable;
import com.ecomm.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserSerialization {
    private Map<String, User> userMap;
    private final String userFilePath = "users.ser"; // Serialized file for user information

    public UserSerialization() {
        this.userMap = new HashMap<>();
        loadUsersFromDisk();
    }

    public void addUser(User user) {
        userMap.put(user.getEmail(), user);
        saveUsersToDisk();
    }

    // Other methods for user management

    private void loadUsersFromDisk() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFilePath))) {
                userMap = (Map<String, User>) ois.readObject();
            } catch (FileNotFoundException | ClassNotFoundException | IOException e) {
                // Handle file not found or corrupted, if any
                userMap = new HashMap<>();
            }
        });
        executor.shutdown();
    }

    private void saveUsersToDisk() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFilePath))) {
                oos.writeObject(userMap);
            } catch (IOException e) {
                // Handle file writing errors, if any
            }
        });
        executor.shutdown();
    }
}