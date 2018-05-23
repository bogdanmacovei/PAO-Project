package Clase.service;

import java.io.Serializable;

public class ProdRaport implements Serializable {
    private int idProdus;
    private int cantitate;
    private double pret;

    public ProdRaport(int idProdus, int cantitate, double pret) {
        this.idProdus = idProdus;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public ProdRaport() {

    }

    public int getIdProdus() {
        return idProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public double getPret() {
        return pret;
    }
}
