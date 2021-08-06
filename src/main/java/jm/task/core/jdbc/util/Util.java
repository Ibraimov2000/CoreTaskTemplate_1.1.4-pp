package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static final String URL = "jdbc:mysql://localhost:3306/my_first_db";
    static final String USER = "root";
    static final String PSW = "1111";

    public Util() {
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PSW);
        return connection;
    }


}
