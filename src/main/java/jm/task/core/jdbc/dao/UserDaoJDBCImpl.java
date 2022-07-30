package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {

            String query = "CREATE TABLE " + Main.getDBName() +
                    "(`id` INT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(45) ," +
                    " `lastName` VARCHAR(45) ," +
                    " `age` TINYINT(0) UNSIGNED ," +
                    " PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;";

            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Что-то пошло не так! [СОЗДАНИЕ ТАБЛИЦЫ]");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {

            String query = "DROP TABLE " + Main.getDBName();

            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Что-то пошло не так! [УДАЛЕНИЕ ТАБЛИЦЫ]");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {

            String query = "INSERT INTO " + Main.getDBName() + " (name, lastName, age) values ('" + name + "', '" + lastName + "', '" + age + "')";

            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Что-то пошло не так! [СОХРАНЕНИЕ ПОЛЬЗОВАТЕЛЕЙ]");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {

            String query = "DELETE FROM " + Main.getDBName() + " WHERE id = " + id;

            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Что-то пошло не так! [УДАЛЕНИЕ ПОЛЬЗОВАТЕЛЯ]");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM " + Main.getDBName();

            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge(result.getByte("age"));
                userList.add(user);
            }


        } catch (SQLException e) {
            System.out.println("Что-то пошло не так! [ПОЛУЧЕНИЕ СПИСКА ПОЛЬЗОВАТЕЛЕЙ]");
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {

            String query = "DELETE FROM " + Main.getDBName();

            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Что-то пошло не так! [ОТЧИСТКА ТАБЛИЦЫ]");
        }
    }
}
