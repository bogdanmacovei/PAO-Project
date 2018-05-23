package Clase.service;

import java.io.Serializable;

public class UpdateCantitate implements Serializable {
    private int id;
    private int cantitate;

    public UpdateCantitate() {
    }

    public UpdateCantitate(int id, int cantitate) {
        this.id = id;
        this.cantitate = cantitate;
    }

    public int getId() {
        return id;
    }

    public int getCantitate() {
        return cantitate;
    }
}
