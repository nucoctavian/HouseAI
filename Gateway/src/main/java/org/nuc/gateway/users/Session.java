package org.nuc.gateway.users;

public class Session {
    private final Long timeStamp;

    public Session() {
        timeStamp = System.currentTimeMillis();
    }
}
