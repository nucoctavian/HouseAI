package org.nuc.houseai.client;

import static org.junit.Assert.*;
import org.junit.Test;
import org.nuc.houseai.client.mina.MinaClient;

public class TestUser {
    @Test
    public void testNullSession() throws Exception {
        final SessionManager sessionManager = new SessionManager(new MinaClient("127.0.0.1", 6045));
        Session session = Session.buildSession(sessionManager, "andrei", "andrei");
        assertNull(session);

        assertTrue(sessionManager.register("andrei", "andrei"));
        session = Session.buildSession(sessionManager, "andrei", "andrei");
        assertNotNull(session);

        assertTrue(session.logout());
        assertFalse(sessionManager.logout("andrei", "andrei"));

        assertTrue(session.changePassword("newPassword"));
        assertFalse(sessionManager.unregister("andrei", "andrei"));
        assertTrue(session.unregister());
    }
}
