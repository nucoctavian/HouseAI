package org.nuc.gateway.users;

public class User {
    private final String password;
    private final String name;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
