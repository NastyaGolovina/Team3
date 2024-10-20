package UPT_PL.Team_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Note
//Run only once when you create a DB, after running in the xml file
//(<property name="hibernate.hbm2ddl.auto">create</property>) change create to update
public class DatabaseCreator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root"; 
        String password = "password"; // your password

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS Team_3");
            System.out.println("Database created!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
