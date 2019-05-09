package word_gussing_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gussing_game", "root", "Wlsdud1964");
        return connection;
    }


    public void login_getconnection() throws SQLException{


    }
}
