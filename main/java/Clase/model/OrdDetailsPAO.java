package Clase.model;

import java.io.Serializable;

public class OrdDetailsPAO implements Serializable {
    private int id;
    private int idUtilizator;
    private int idProdus;
    private int cantitate;
    private double pret;

    private OrdDetailsPAO() {

    }

    private OrdDetailsPAO(int id, int idUtilizator, int idProdus, int cantitate, double pret) {
        this.id = id;
        this.idUtilizator = idUtilizator;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }
    public int getIdUtilizator() {
        return idUtilizator;
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

    public static Builder builder () {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private int idUtilizator;
        private int idProdus;
        private int cantitate;
        private double pret;

        private Builder () {

        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withIdUtilizator(int idUtilizator) {
            this.idUtilizator = idUtilizator;
            return this;
        }

        public Builder withIdProdus(int idProdus) {
            this.idProdus = idProdus;
            return this;
        }

        public Builder withCantitate(int cantitate) {
            this.cantitate = cantitate;
            return this;
        }

        public Builder withPret (double pret) {
            this.pret = pret;
            return this;
        }

        public OrdDetailsPAO build () {
            return new OrdDetailsPAO (id, idUtilizator, idProdus, cantitate, pret);
        }
    }
}
