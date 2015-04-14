package org.nuc.houseai.core;

public class ChangePasswordRequest extends CredentialBasedRequest {

    private static final long serialVersionUID = 104702286519041143L;
    private final String newPassword;

    public ChangePasswordRequest(String user, String oldPassword, String newPassword) {
        super(user, oldPassword);
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
