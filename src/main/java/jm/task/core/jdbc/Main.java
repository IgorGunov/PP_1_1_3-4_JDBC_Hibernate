package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        //userService.cleanUsersTable();
        //userService.saveUser("Igor", "Gunov", (byte) 21);
        userService.removeUserById(1);
        List<User> list = userService.getAllUsers();
        System.out.println(list);
    }


}
