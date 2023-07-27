package com.ecomm.service;
import com.ecomm.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManagement {
    private Map<String, User> usersMap; // Store users with their emails as keys
    private Map<String, String> sessionMap; // Store sessions with their session IDs as keys
    private final long sessionTimeoutMillis = 30 * 60 * 1000; // 30 minutes in milliseconds

    public UserManagement() {
        usersMap = new HashMap<>();
        sessionMap = new HashMap<>();
    }

    public void signUp(String email, String firstName, String lastName, String password) throws Exception {
        if (usersMap.containsKey(email)) {
            throw new Exception("User with the provided email already exists. Please use a different email.");
        }

        User newUser = new User(generateUserId(), email, firstName, lastName, password);
        usersMap.put(email, newUser);
    }

    public String signIn(String email, String password) throws Exception {
        if (!usersMap.containsKey(email)) {
            throw new Exception("User with the provided email does not exist. Please sign up first.");
        }

        User user = usersMap.get(email);
        if (!user.getPassword().equals(password)) {
            throw new Exception("Invalid password. Please try again.");
        }

        String sessionId = generateSessionId();
        sessionMap.put(sessionId, email);

        return sessionId;
    }

    public boolean isSessionValid(String sessionId) {
        if (!sessionMap.containsKey(sessionId)) {
            return false;
        }

        long sessionCreationTime = getSessionCreationTime(sessionId);
        long currentTime = System.currentTimeMillis();

        return currentTime - sessionCreationTime <= sessionTimeoutMillis;
    }

    public void endSession(String sessionId) {
        sessionMap.remove(sessionId);
    }

    private String generateUserId() {
        // You can implement your own logic to generate a unique user ID
        return UUID.randomUUID().toString();
    }

    private String generateSessionId() {
        // You can implement your own logic to generate a unique session ID
        return UUID.randomUUID().toString();
    }

    private long getSessionCreationTime(String sessionId) {
        // You can store the session creation time when adding it to the sessionMap
        // For simplicity, let's assume the session is created at the current time
        return System.currentTimeMillis();
    }
}
