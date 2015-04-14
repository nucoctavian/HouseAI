package org.nuc.houseai.core;

public class RegisterRequest extends CredentialBasedRequest {

    private static final long serialVersionUID = 6144378981101510581L;

    public RegisterRequest(String user, String password) {
        super(user, password);
    }
}
