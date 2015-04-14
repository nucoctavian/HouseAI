package org.nuc.gateway.users;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class UsersManager {

    private final Map<String, User> usersByName = new HashMap<String, User>();
    private static final Logger LOGGER = Logger.getLogger(UsersManager.class);

    public boolean register(String name, String password) {
        if (usersByName.containsKey(name)) {
            LOGGER.warn("Received an request for an already existing user");
            return false;
        }
        usersByName.put(name, new User(name, password));
        LOGGER.info("New user created");
        return true;
    }

    public boolean checkCredentials(String name, String password) {
        if (!usersByName.containsKey(name)) {
            LOGGER.warn("Credentials don't match:" + name + " " + password);
            return false;
        }

        if (usersByName.get(name).getPassword() == password) {
            LOGGER.warn("Credentials don't match:" + name + " " + password);
            return false;
        }

        LOGGER.info("Credentials match for user  ");
        return true;
    }

    public boolean deleteAccount(String name, String password) {
        if (!usersByName.containsKey(name)) {
            return false;
        }

        if (usersByName.get(name).getPassword().equals(password)) {
            usersByName.remove(name);
            return true;
        }

        return false;
    }

    public boolean changePassword(String name, String oldPassword, String newPassword) {
        if (checkCredentials(name, oldPassword)) {
            deleteAccount(name, oldPassword);
            register(name, newPassword);
            return true;
        }
        return false;
    }
}
