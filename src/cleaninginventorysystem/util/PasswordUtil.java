/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Provides password hashing and verification methods.
 *
 * @author Mkhanyisi Mqadi
 */
package cleaninginventorysystem.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String hashPassword(String password) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] encodedHash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder hash = new StringBuilder();

            for (byte value : encodedHash) {
                hash.append(String.format("%02x", value));
            }

            return hash.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password hashing failed.", e);
        }
    }

    public static boolean verifyPassword(
            String enteredPassword,
            String storedPasswordHash
    ) {
        String enteredPasswordHash = hashPassword(enteredPassword);

        return enteredPasswordHash.equals(storedPasswordHash);
    }
}