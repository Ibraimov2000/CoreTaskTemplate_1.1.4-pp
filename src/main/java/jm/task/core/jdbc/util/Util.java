package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {

    // Hibernate Util

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/coretasktemplate_1.1.3?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";

    private static SessionFactory sessionFactory = getSessionFactory();

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Исключение при создании sessionFactory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSession() {
        return sessionFactory;
    }

    // JDBC

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            Properties props = null;
            try {
                connection = DriverManager.getConnection(props.getProperty("jdbc:mysql://localhost:3306/coretasktemplate_1.1.3?autoReconnect=true&useSSL=false"), props.getProperty("root"), props.getProperty("1111"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private Util() {

    }


}