package org.nuc.houseai.client.mina;

import java.util.HashSet;
import java.util.Set;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class HandlerAggregator extends IoHandlerAdapter {
    final Set<IoHandler> handlers = new HashSet<IoHandler>();

    public void addHandler(IoHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(IoHandler handler) {
        handlers.remove(handler);
    }

    public void messageReceived(IoSession session, Object object) throws Exception {
        for (IoHandler ioHandler : handlers) {
            ioHandler.messageReceived(session, object);
        }

    }
}
