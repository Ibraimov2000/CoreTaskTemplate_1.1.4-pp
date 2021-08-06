package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 5);
        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 5);
        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 5);
        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 5);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
