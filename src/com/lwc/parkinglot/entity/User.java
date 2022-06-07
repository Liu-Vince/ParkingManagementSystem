package com.lwc.parkinglot.entity;

/**
 * @author Liu Wenchang
 */
public class User {
    private String id;
    private String password;
    /*
    管理员：1
    普通用户：2
     */
    private int type;

    public User(String id, String password, int type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
