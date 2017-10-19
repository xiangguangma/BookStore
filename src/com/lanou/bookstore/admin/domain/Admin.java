package com.lanou.bookstore.admin.domain;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/23.
 */
public class Admin implements Serializable {
    private String aid;
    private String aname;
    private String password;

    public Admin() {
    }

    public Admin(String aid, String aname, String password) {
        this.aid = aid;
        this.aname = aname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid='" + aid + '\'' +
                ", aname='" + aname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
