/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Handles user registration and login operations.
 *
 * @author Mkhanyisi Mqadi
 */
package cleaninginventorysystem.service;

import cleaninginventorysystem.dao.UserDAO;
import cleaninginventorysystem.model.User;
import cleaninginventorysystem.util.PasswordUtil;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();
    
    public boolean register(
        String username,
        String password,
        String role
) {
    if (username == null || username.trim().isEmpty()) {
        return false;
    }

    if (password == null || password.isEmpty()) {
        return false;
    }

    if (role == null || role.trim().isEmpty()) {
        return false;
    }

    if (userDAO.usernameExists(username.trim())) {
        return false;
    }

    String passwordHash = PasswordUtil.hashPassword(password);

    User user = new User();
    user.setUsername(username.trim());
    user.setPasswordHash(passwordHash);
    user.setRole(role.trim());

    return userDAO.registerUser(user);
}
    
    public User login(String username, String password) {

    User user = userDAO.findUserByUsername(username);

    if (user == null) {
        return null;
    }

    boolean validPassword = PasswordUtil.verifyPassword(
            password,
            user.getPasswordHash()
    );

    if (!validPassword) {
        return null;
    }

    return user;
}

}
