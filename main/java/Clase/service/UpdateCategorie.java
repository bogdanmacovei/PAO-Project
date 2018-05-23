package Clase.service;

import java.io.Serializable;

public class UpdateCategorie implements Serializable {
    private String username;
    private String categorie;

    public UpdateCategorie(String username, String categorie) {
        this.username = username;
        this.categorie = categorie;
    }

    public UpdateCategorie() {

    }

    public String getUsername() {
        return username;
    }

    public String getCategorie() {
        return categorie;
    }
}
