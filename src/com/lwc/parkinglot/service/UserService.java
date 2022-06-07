package com.lwc.parkinglot.service;

import com.lwc.parkinglot.entity.User;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Liu Wenchang
 */
public class UserService {
    LinkedList<User> users = new LinkedList<>();
    public UserService() {
        // 管理员
        users.addFirst(new User("admin","ynu#admin",1));
        // 测试用户
        users.add(new User("test","test",2));
    }

    public void updateAdminUserPassword(String pwd){
        User remove = users.removeFirst();
        remove.setPassword(pwd);
        users.addFirst(remove);
    }
    public boolean updateUserPassword(String id, String pwd){
        for (User user : users) {
            if (user.getId().equals(id)){
                users.remove(user);
                users.add(new User(id,pwd,2));
                return true;
            }
        }
        return false;

    }

    public void addUser(String id, String pwd){
        users.add(new User(id,pwd,2));
    }

    public int selectType(String id, String pwd) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(pwd)) {
                if (user.getType() == 1){
                    return 1;
                } else if (user.getType() == 2){
                    return 2;
                }
                return 0;
            }
        }
        return 0;
    }
}
