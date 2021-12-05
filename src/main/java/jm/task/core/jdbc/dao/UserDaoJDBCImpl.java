package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, name nvarchar(20) NOT NULL, lastName nvarchar(20) NOT NULL, age smallint NOT NULL)");
            System.out.println("Таблица успешно создана!");
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            System.out.println("Произошла ошибка при создании таблицы!");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Таблица успешно удалена!");
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age){
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных )");
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            System.out.println("Произошла ошибка при сохранении пользователя!");
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM  users WHERE id = " + id);
            System.out.println("Пользователь под номером " + id + " успешно удален!");
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при удалении пользователя!");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                System.out.println(user);
                users.add(user);
            }

        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выборе всех пользователей таблицы!");
            ;
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE table users");
            System.out.println("Таблица успешно очищена!");
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }
}
