package com.example.sqlitedemo2;

public class User {

    String Name;
    String Email;
    String Age;
    String Active;

    public User(String name, String email, String age, String active) {
        Name = name;
        Email = email;
        Age = age;
        Active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Age='" + Age + '\'' +
                ", Active='" + Active + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }
}
