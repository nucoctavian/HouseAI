package org.nuc.houseai.core;

public class LogoutRequest extends CredentialBasedRequest {
    private static final long serialVersionUID = -466997292467510978L;

    public LogoutRequest(String user, String password) {
        super(user, password);
    }

}
