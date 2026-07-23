/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cleaninginventorysystem.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DBConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException("db.properties file was not found.");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load database configuration.", e
            );
        }
    }

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
    
    public static void main(String[] args) {

    try (Connection conn = getConnection()) {
        System.out.println("Connected successfully!");
    } catch (SQLException e) {
        System.out.println("Connection failed.");
        e.printStackTrace();
    }
}
}
