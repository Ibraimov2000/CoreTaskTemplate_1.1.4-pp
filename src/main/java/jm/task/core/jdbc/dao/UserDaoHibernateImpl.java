package jm.task.core.jdbc.dao;

import jdk.javadoc.internal.doclint.Env;
import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class UserDaoHibernateImpl implements UserDao {
    Properties properties;
    Configuration configuration;
    SessionFactory factory;
    Session session;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() throws SQLException {
        properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "1111");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/my_first_db");

        configuration = new Configuration();
        configuration.setProperties(properties);

        configuration.addAnnotatedClass(User.class);
    }

    @Override
    public void dropUsersTable() throws SQLException {
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        factory = configuration.buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User("Mirseit", "Ibraimov", (byte) 21);
        session.persist(user);
        System.out.println("User saved!");
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query deleteQuery = session.createQuery("delete from User e where e.id=?id");
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        factory = configuration.buildSessionFactory();
        session = factory.openSession();
        Query query = session.createQuery("select e from User e");
        List<User> list = query.list();
        for(User use:list) {
            use.toString();
        }
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {

    }
}
