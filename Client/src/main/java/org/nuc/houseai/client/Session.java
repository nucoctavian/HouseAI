package org.nuc.houseai.client;

public class Session {
    public static Session buildSession(SessionManager sessionManager, String username, String password) {
        if (sessionManager.login(username, password)) {
            return new Session(sessionManager, username, password);
        }
        return null;
    }

    private String password;
    private String username;
    private SessionManager sessionManager;

    private Session(SessionManager sessionManager, String username, String password) {
        this.sessionManager = sessionManager;
        this.username = username;
        this.password = password;
    }

    public boolean logout() {
        return sessionManager.logout(username, password);
    }

    public boolean changePassword(String newPassword) {
        if (password.equals(newPassword) || newPassword.isEmpty()) {
            return false;
        }

        final boolean changedPassword = sessionManager.changePassword(username, password, newPassword);
        if (changedPassword) {
            password = newPassword;
        }
        return changedPassword;
    }

    public boolean unregister() {
        return sessionManager.unregister(username, password);
    }
}
