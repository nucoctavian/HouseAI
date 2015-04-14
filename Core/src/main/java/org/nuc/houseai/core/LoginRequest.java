package org.nuc.houseai.core;

public class LoginRequest extends CredentialBasedRequest {
    private static final long serialVersionUID = 636712394514633345L;

    public LoginRequest(String user, String password) {
        super(user, password);
    }
}
