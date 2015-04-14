package org.nuc.houseai.client.mina;

import java.io.Serializable;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {
    private NioSocketConnector connector;
    private IoSession session;
    private HandlerAggregator handlerAggregator = new HandlerAggregator();

    public MinaClient(String address, int port) throws Exception {
        this.connector = new NioSocketConnector();

        connector.getSessionConfig().setReadBufferSize(2048);
        LoggingFilter restrictedLoggingFilter = new LoggingFilter();
        connector.getFilterChain().addLast("logger", restrictedLoggingFilter);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        connector.setHandler(handlerAggregator);

        final InetSocketAddress remoteAddress = new InetSocketAddress(address, port);
        final ConnectFuture future = connector.connect(remoteAddress);
        future.awaitUninterruptibly();

        if (!future.isConnected()) {
            throw new Exception(String.format("Failed to connect to %s:%d", address, port));
        }

        session = future.getSession();
        session.getConfig().setUseReadOperation(true);
    }

    public void sendMessage(Serializable message) {
        session.write(message);
    }

    public HandlerAggregator getHandleAggregator() {
        return handlerAggregator;
    }
}
