package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;

import javax.imageio.spi.ServiceRegistry;
import javax.security.auth.login.AppConfigurationEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    static final String URL = "jdbc:mysql://localhost:3306/my_first_db";
    static final String USER = "root";
    static final String PSW = "1111";

    static SessionFactory sessionFactory;

    public Util() {
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PSW);
        return connection;
    }

    static {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", URL);
        properties.setProperty("hibernate.connection.username", USER);
        properties.setProperty("hibernate.connection.password", PSW);
        properties.setProperty("show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.pool_size", "1");

        Configuration configuration = new Configuration().addAnnotatedClass(User.class).setProperties(properties);

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return  sessionFactory;
    }
}
