package org.nuc.houseai.client;

import org.nuc.houseai.client.mina.MinaClient;
import org.nuc.houseai.client.mina.RequestResponse;
import org.nuc.houseai.core.ChangePasswordRequest;
import org.nuc.houseai.core.LoginRequest;
import org.nuc.houseai.core.LogoutRequest;
import org.nuc.houseai.core.RegisterRequest;
import org.nuc.houseai.core.Response;
import org.nuc.houseai.core.UnregisterRequest;

public class SessionManager {
    private final RequestResponse<LoginRequest> loginRequestResponse;
    private final RequestResponse<LogoutRequest> logoutRequestResponse;
    private final RequestResponse<RegisterRequest> registerRequestResponse;
    private final RequestResponse<UnregisterRequest> unregisterRequestResponse;
    private final RequestResponse<ChangePasswordRequest> changePasswordRequestResponse;

    public SessionManager(MinaClient minaClient) {
        loginRequestResponse = new RequestResponse<LoginRequest>(minaClient);
        logoutRequestResponse = new RequestResponse<LogoutRequest>(minaClient);
        registerRequestResponse = new RequestResponse<RegisterRequest>(minaClient);
        unregisterRequestResponse = new RequestResponse<UnregisterRequest>(minaClient);
        changePasswordRequestResponse = new RequestResponse<ChangePasswordRequest>(minaClient);
    }

    public boolean login(String username, String password) {
        final Response<LoginRequest> response = loginRequestResponse.getResponse(new LoginRequest(username, password));
        return response != null && response.isSuccessfull();
    }

    public boolean logout(String username, String password) {
        final Response<LogoutRequest> response = logoutRequestResponse.getResponse(new LogoutRequest(username, password));
        return response != null && response.isSuccessfull();
    }

    public boolean register(String username, String password) {
        final Response<RegisterRequest> response = registerRequestResponse.getResponse(new RegisterRequest(username, password));
        return response != null && response.isSuccessfull();
    }

    public boolean unregister(String username, String password) {
        final Response<UnregisterRequest> response = unregisterRequestResponse.getResponse(new UnregisterRequest(username, password));
        return response != null && response.isSuccessfull();
    }

    public boolean changePassword(String username, String password, String newPassword) {
        final Response<ChangePasswordRequest> response = changePasswordRequestResponse.getResponse(new ChangePasswordRequest(username, password, newPassword));
        return response != null && response.isSuccessfull();
    }
}
