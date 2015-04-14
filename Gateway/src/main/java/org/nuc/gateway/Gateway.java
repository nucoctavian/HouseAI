package org.nuc.gateway;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.gateway.mina.MinaServer;
import org.nuc.gateway.users.SessionManager;
import org.nuc.gateway.users.UsersManager;
import org.nuc.houseai.core.ChangePasswordRequest;
import org.nuc.houseai.core.LoginRequest;
import org.nuc.houseai.core.LogoutRequest;
import org.nuc.houseai.core.RegisterRequest;
import org.nuc.houseai.core.Request;
import org.nuc.houseai.core.Response;
import org.nuc.houseai.core.UnregisterRequest;
import org.nuc.houseai.service.DistryUtils;

public class Gateway extends DistryService implements IoHandler {
    private static final String SERVICE_NAME = "Gateway";
    private final UsersManager userManager;
    private final SessionManager sessionManager;
    private static final Logger LOGGER = Logger.getLogger(Gateway.class);

    public Gateway(ServiceConfiguration configuration, UsersManager userManager) throws Exception {
        super(SERVICE_NAME, configuration);
        this.userManager = userManager;
        this.sessionManager = new SessionManager(userManager);
    }

    public static void main(String[] args) {
        try {
            final Gateway gateway = new Gateway(DistryUtils.createServiceConfiguration(), new UsersManager());
            gateway.start();
            new MinaServer(gateway);

        } catch (JMSException e) {
            LOGGER.fatal("Failed to connect to broker", e);
        } catch (Exception e) {
            LOGGER.fatal("Failed to start Gateway", e);
        }
    }

    public void exceptionCaught(IoSession session, Throwable throwable) throws Exception {
        LOGGER.error("Caught exception on session " + session, throwable);
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        if (!(message instanceof Request)) {
            return;
        }

        final Request request = (Request) message;
        if (request instanceof LoginRequest) {
            final LoginRequest loginRequest = (LoginRequest) request;
            final String name = loginRequest.getName();
            final String password = loginRequest.getPassword();
            final boolean result = sessionManager.login(name, password);
            final Response<LoginRequest> response;
            if (result) {
                response = new Response<LoginRequest>(loginRequest, true, null);

            } else {
                response = new Response<LoginRequest>(loginRequest, false, "Failed to login");
            }
            session.write(response);
            return;
        }

        if (request instanceof LogoutRequest) {
            final LogoutRequest logoutRequest = (LogoutRequest) request;
            final String name = logoutRequest.getName();
            final String password = logoutRequest.getPassword();
            final boolean result = sessionManager.logout(name, password);
            final Response<LogoutRequest> response;
            if (result) {
                response = new Response<LogoutRequest>(logoutRequest, true, null);

            } else {
                response = new Response<LogoutRequest>(logoutRequest, false, "Failed to logout");
            }
            session.write(response);
            return;
        }

        if (request instanceof RegisterRequest) {
            final RegisterRequest registerRequest = (RegisterRequest) request;
            final String name = registerRequest.getName();
            final String password = registerRequest.getPassword();
            final boolean result = userManager.register(name, password);
            final Response<RegisterRequest> response;
            if (result) {
                response = new Response<RegisterRequest>(registerRequest, true, null);

            } else {
                response = new Response<RegisterRequest>(registerRequest, false, "Failed to register");
            }
            session.write(response);
            return;
        }

        if (request instanceof UnregisterRequest) {
            final UnregisterRequest unregisterRequest = (UnregisterRequest) request;
            final String name = unregisterRequest.getName();
            final String password = unregisterRequest.getPassword();
            final boolean result = userManager.deleteAccount(name, password);
            final Response<UnregisterRequest> response;
            if (result) {
                response = new Response<UnregisterRequest>(unregisterRequest, true, null);

            } else {
                response = new Response<UnregisterRequest>(unregisterRequest, false, "Failed to unregister");
            }
            session.write(response);
            return;
        }

        if (request instanceof ChangePasswordRequest) {
            final ChangePasswordRequest changePasswordRequest = (ChangePasswordRequest) request;
            final String name = changePasswordRequest.getName();
            final String oldPassword = changePasswordRequest.getPassword();
            final String newPassword = changePasswordRequest.getNewPassword();
            final boolean result = userManager.changePassword(name, oldPassword, newPassword);
            final Response<ChangePasswordRequest> response;
            if (result) {
                response = new Response<ChangePasswordRequest>(changePasswordRequest, true, null);

            } else {
                response = new Response<ChangePasswordRequest>(changePasswordRequest, false, "Failed to change password");
            }
            session.write(response);
            return;
        }
    }

    public void messageSent(IoSession arg0, Object arg1) throws Exception {
    }

    public void sessionClosed(IoSession arg0) throws Exception {
    }

    public void sessionCreated(IoSession arg0) throws Exception {
    }

    public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
    }

    public void sessionOpened(IoSession arg0) throws Exception {
    }
}
