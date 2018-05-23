package Clase.model;

import java.io.Serializable;

public class ProdusPAO implements Serializable {
    private int id;
    private String nume;
    private String categorie;
    private double pret_unitar;
    private int cantitate;
    private String imagine;

    private ProdusPAO() {
    }

    private ProdusPAO(int id, String nume, String categorie, double pret_unitar, int cantitate, String imagine) {
        this.id = id;
        this.nume = nume;
        this.categorie = categorie;
        this.pret_unitar = pret_unitar;
        this.cantitate = cantitate;
        this.imagine = imagine;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getPret_unitar() {
        return pret_unitar;
    }

    public int getCantitate() {
        return cantitate;
    }

    public String getImagine() {
        return imagine;
    }

    public static Builder builder () {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String nume;
        private String categorie;
        private double pret_unitar;
        private int cantitate;
        private String imagine;

        private Builder () {

        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withNume(String nume) {
            this.nume = nume;
            return this;
        }

        public Builder withCategorie(String categorie) {
            this.categorie = categorie;
            return this;
        }

        public Builder withPretUnitar (double pret_unitar) {
            this.pret_unitar = pret_unitar;
            return this;
        }

        public Builder withCantitate (int cantitate) {
            this.cantitate = cantitate;
            return this;
        }

        public Builder withImagine (String imagine) {
            this.imagine = imagine;
            return this;
        }

        public ProdusPAO build () {
            return new ProdusPAO (id, nume, categorie, pret_unitar, cantitate, imagine);
        }
    }
}
