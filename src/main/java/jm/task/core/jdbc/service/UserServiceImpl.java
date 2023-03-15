package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
private final Util util = new Util();
private final Connection connect = util.connect();

public void createUsersTable() {
    String query = "CREATE TABLE users(id int AUTO_INCREMENT PRIMARY KEY, name varchar(30), lastname varchar(30), age int);";
    String query2 = "SELECT * FROM users";
    try {
        connect.prepareStatement(query2).executeQuery().next();
        System.out.println("Такая таблица уже существует");
    } catch (SQLException e) {
        try {
            connect.createStatement().executeUpdate(query);
            System.out.println("Таблица создана");
        } catch (SQLException o) {
            System.out.println("ошибка при создании таблицы" + o);
        }
    }

}

public void dropUsersTable() {
    String query = "DROP table users";
    try {
        connect.createStatement().executeUpdate(query);
        System.out.println("Таблица успешна удалена");
    } catch (SQLException e) {
        System.out.println("Не удалось удалить таблицу по причине: " + e);
    }
}

public void saveUser(String name, String lastName, byte age) {
    String query = "INSERT users(name, lastname,age) values ('" + name + "', '" + lastName + "', " + age + ")";
    System.out.println(query);
    try {
        connect.createStatement().executeUpdate(query);
        System.out.println("Пользователь успешно добавлен");
    } catch (SQLException e) {
        System.out.println("Не удалось добавить пользователя по причине: " + e);
    }
}

public void removeUserById(long id) {
    String query = "DELETE FROM users WHERE id=" + id;
    try {
        connect.createStatement().executeUpdate(query);
        System.out.println("Пользователь успешно удален");
    } catch (SQLException e) {
        System.out.println("Не удалось удалить пользователя по причине: " + e);
    }
}

public List<User> getAllUsers() {
    String query = "SELECT * FROM users";
    List<User> list = new ArrayList<>();
    try {
        ResultSet rs = connect.prepareStatement(query).executeQuery();
        rs.next();
        list.add(new User(rs.getString("name"),rs.getString("lastname"), rs.getByte("age")));

        return list;
    } catch (SQLException e) {
        return list;
    }
}

public void cleanUsersTable() {
    String query = "truncate table users";
    try {
        connect.createStatement().executeUpdate(query);
        System.out.println("Таблица очищена успешно");
    } catch (SQLException e) {
        System.out.println("Не удалось очистить таблицу по причине: " + e);
    }
}
}
