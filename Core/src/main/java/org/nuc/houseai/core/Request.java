package org.nuc.houseai.core;

import java.io.Serializable;
import java.util.UUID;

public class Request implements Serializable {
    private static final long serialVersionUID = 1963141029735822613L;
    private final String id = UUID.randomUUID().toString();

    public Request() {

    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Request)) {
            return false;
        }
        final Request that = (Request) object;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
