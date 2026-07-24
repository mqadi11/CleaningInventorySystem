/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cleaninginventory.cleaninginventory.models;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String username;
    private String pwdHash; 
    private String role;
    private Timestamp createdAt;

    // Default Constructor
    public User() {}

    // Parameterized Constructor
    public User(int userId, String username, String pwdHash, String role, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.pwdHash = pwdHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPwdHash() { return pwdHash; }
    public void setPwdHash(String pwdHash) { this.pwdHash = pwdHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}