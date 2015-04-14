package org.nuc.houseai.core;

public class UnregisterRequest extends CredentialBasedRequest {

    private static final long serialVersionUID = -5024785957855897153L;

    public UnregisterRequest(String user, String password) {
        super(user, password);
    }
}