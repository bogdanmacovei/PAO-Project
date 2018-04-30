package Clase.service;

import java.io.Serializable;

public class UpdateImage implements Serializable {
    private int id;
    private String img;

    public UpdateImage(int ID, String img) {
        this.id = ID;
        this.img = img;
    }

    public UpdateImage() {

    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }
}
