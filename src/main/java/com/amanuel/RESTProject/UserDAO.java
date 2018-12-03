package com.amanuel.RESTProject;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAO {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;

    static {
        users.add(new User(2103, "John",new Date()));
        users.add(new User(4532, "John", new Date()));
        users.add(new User(2103, "John", new Date()));
    }

    public static List<User> findAll() {
        return users;
    }

    public static User save(User user){
        if (user != null && user.getId() == null)
            user.setId(++userCount);

        users.add(user);
        return user;
    }

    public static User findOne(int id){
        return users.stream().filter(u -> u.getId().equals(id)).findAny().orElse(null);
    }
    public static boolean delete(int id){
        boolean deleted = false;
        for (Iterator iterator = users.iterator(); iterator.hasNext();){
            if (((User)iterator.next()).getId() == id){
                iterator.remove();
                deleted = true;
                break;
            }
            else iterator.next();
        }
        return deleted;
    }
}
