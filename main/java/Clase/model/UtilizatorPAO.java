package Clase.model;

import java.io.Serializable;

public class UtilizatorPAO implements Serializable {
    private int id;
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    private String categorie;

    private UtilizatorPAO(int id, String nume, String prenume, String username, String parola, String categorie) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.categorie = categorie;
    }

    private UtilizatorPAO() {

    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getUsername() {
        return username;
    }

    public String getParola() {
        return parola;
    }

    public String getCategorie() {
        return categorie;
    }

    public static Builder builder () {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String nume;
        private String prenume;
        private String username;
        private String parola;
        private String categorie;

        private Builder () {

        }

        public Builder withId (int id) {
            this.id = id;
            return this;
        }

        public Builder withNume (String nume) {
            this.nume = nume;
            return this;
        }

        public Builder withPrenume (String prenume) {
            this.prenume = prenume;
            return this;
        }

        public Builder withUsername (String username) {
            this.username = username;
            return this;
        }

        public Builder withParola (String parola) {
            this.parola = parola;
            return this;
        }

        public Builder withCategorie (String categorie) {
            this.categorie = categorie;
            return this;
        }

        public UtilizatorPAO build () {
            return new UtilizatorPAO (id, nume, prenume, username, parola, categorie);
        }
    }
}
