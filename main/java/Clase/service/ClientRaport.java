package Clase.service;

public class ClientRaport {
    private int idUtilizator;
    private int cantitate;
    private double pret;

    public ClientRaport(int idUtilizator, int cantitate, double pret) {
        this.idUtilizator = idUtilizator;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public ClientRaport() {
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public int getCantitate() {
        return cantitate;
    }

    public double getPret() {
        return pret;
    }
}
