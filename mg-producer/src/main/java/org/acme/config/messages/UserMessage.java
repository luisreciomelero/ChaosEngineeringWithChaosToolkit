package org.acme.config.messages;

import org.acme.config.entities.Usuario;

import java.io.Serializable;

public class UserMessage implements Serializable {

    private String action;
    private Usuario user;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }


}
