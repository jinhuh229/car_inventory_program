package sample;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

public class DBConnection {




    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car", "root", "Wlsdud1964");

        return connection;
    }

}


