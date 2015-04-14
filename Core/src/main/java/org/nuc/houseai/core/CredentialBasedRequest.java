package org.nuc.houseai.core;

public class CredentialBasedRequest extends Request {
    private static final long serialVersionUID = 636712394514633345L;

    private final String user;
    private final String password;
    
    public CredentialBasedRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }
    
    public String getName() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
