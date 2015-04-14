package org.nuc.gateway.users;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private final UsersManager usersManager;
    private final Map<String, Session> sessionByName = new HashMap<String, Session>();

    public SessionManager(UsersManager usersManager) {
        this.usersManager = usersManager;
    }

    public boolean login(String name, String password) {
        if (!usersManager.checkCredentials(name, password)) {
            return false;
        }

        if (sessionByName.containsKey(name)) {
            return false;
        }
        sessionByName.put(name, new Session());
        return true;
    }

    public boolean logout(String name, String password) {
        if (!usersManager.checkCredentials(name, password)) {
            return false;
        }
        if (!sessionByName.containsKey(name)) {
            return false;
        }
        sessionByName.remove(name);
        return true;
    }
}
