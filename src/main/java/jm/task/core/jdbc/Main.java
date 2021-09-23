package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {//
        UserServiceImpl userDaoHibernate = new UserServiceImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 5);
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 5);
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 5);
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 5);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
