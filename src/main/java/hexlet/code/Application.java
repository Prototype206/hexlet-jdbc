package hexlet.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application{
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")){

        String sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255), email VARCHAR(255))";
        try(Statement statement = conn.createStatement()){
        statement.execute(sql);
        }

        String sql2 = "INSERT INTO users (username, phone, email) VALUES ('tommy', '123456789', 'easyEmail'), ('Richard', '2001', 'HardEmail')";
        try(Statement statement2 = conn.createStatement()){
            statement2.executeUpdate(sql2);
        }

        String sql3 = "SELECT * FROM users";
        try(Statement statement3 = conn.createStatement()){
            ResultSet resultSet = statement3.executeQuery(sql3);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("phone"));
                System.out.println(resultSet.getString("email"));
            }
        }
        }
    }
}