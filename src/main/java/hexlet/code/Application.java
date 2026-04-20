package hexlet.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

        String sql2 = "INSERT INTO users (username, phone, email) VALUES (?, ?, ?)";
        try(PreparedStatement prepareStatement = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)){
            prepareStatement.setString(1, "Tommy");
            prepareStatement.setString(2, "10029");
            prepareStatement.setString(3, "easyEmail");
            prepareStatement.executeUpdate();

            prepareStatement.setString(1, "Magy");
            prepareStatement.setString(2, "902891");
            prepareStatement.setString(3, "mdk2@mags");
            prepareStatement.executeUpdate();

            prepareStatement.setString(1, "Jack");
            prepareStatement.setString(2, "12894");
            prepareStatement.setString(3, "MKDIR@pochat");
            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                System.out.println(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("DB have not returned an id after saving the entity");
            }
        }

        String sql3 = "DELETE FROM users WHERE username = ?";
        try(PreparedStatement prepareStatement2 = conn.prepareStatement(sql3)){
            prepareStatement2.setString(1, "Tommy");
            prepareStatement2.executeUpdate();
        }


        String sql4 = "SELECT * FROM users";
        try(Statement statement4 = conn.createStatement()){
            ResultSet resultSet = statement4.executeQuery(sql4);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("phone"));
                System.out.println(resultSet.getString("email"));
            }
        }
        }
    }
}