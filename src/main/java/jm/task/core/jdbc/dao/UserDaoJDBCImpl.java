package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

private final Util util = new Util();

private String create = "CREATE TABLE users(id int AUTO_INCREMENT PRIMARY KEY, name varchar(30), lastname varchar(30), age int)";
private String select = "SELECT * FROM users";
private String drop = "DROP table users";
private String clean = "truncate table users";

@Override
public void createUsersTable() {
    try (Connection connect = util.connect()) {
        connect.prepareStatement(select).execute();
        System.out.println("Такая таблица уже существует");
    } catch (SQLException e) {
        try (Connection connect = util.connect()) {
            connect.prepareStatement(create).execute();
            System.out.println("Таблица создана");
        } catch (SQLException o) {
            System.out.println("ошибка при создании таблицы" + o);
        }
    }
}

@Override
public void dropUsersTable() {
    try (Connection connect = util.connect()) {
        connect.prepareStatement(drop).execute();
        System.out.println("Таблица успешна удалена");
    } catch (SQLException e) {
        System.out.println("Не удалось удалить таблицу по причине: " + e);
    }
}

@Override
public void saveUser(String name, String lastName, byte age) {
    String query = "INSERT users(name, lastname,age) values ('" + name + "', '" + lastName + "', " + age + ")";
    try (Connection connect = util.connect()) {
        connect.prepareStatement(query).execute();
        System.out.println("Пользователь успешно добавлен");
    } catch (SQLException e) {
        System.out.println("Не удалось добавить пользователя по причине: " + e);
    }
}

@Override
public void removeUserById(long id) {
    String query = "DELETE FROM users WHERE id=" + id;
    try (Connection connect = util.connect()) {
        connect.prepareStatement(query).execute();
        System.out.println("Пользователь успешно удален");
    } catch (SQLException e) {
        System.out.println("Не удалось удалить пользователя по причине: " + e);
    }
}

@Override
public List<User> getAllUsers() {
    List<User> list = new ArrayList<>();
    try (Connection connect = util.connect()) {
        ResultSet rs = connect.prepareStatement(select).executeQuery();
        rs.next();
        list.add(new User(rs.getString("name"), rs.getString("lastname"), rs.getByte("age")));
        return list;
    } catch (SQLException e) {
        return list;
    }
}

@Override
public void cleanUsersTable() {
    try (Connection connect = util.connect()) {
        connect.prepareStatement(clean).execute();
        System.out.println("Таблица очищена успешно");
    } catch (SQLException e) {
        System.out.println("Не удалось очистить таблицу по причине: " + e);
    }
}
}
