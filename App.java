import java.sql.*;
import BackEnd.Employee;
import UI.Screens;

public class App {
    public static void main(String[] args) throws Exception {
        // Make connection to database
        Connection connection;
        Statement stmt;
        connection = DriverManager.getConnection("");
        stmt = connection.createStatement();
        Screens screen = new Screens(stmt);

        if (stmt == null) {
            System.out.println("object is null");
        } else {
            screen.login(new Employee(stmt));
        }

    }
}

