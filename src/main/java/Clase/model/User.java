package Clase.model;

import java.io.Serializable;

public class User implements Serializable {
    private int ID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String joined;
    private String birthDate;
    private String country;
    private String shortDescription;
    private String img;

    private User() {

    }

    private User(int ID, String firstName, String lastName, String username, String password,
                 String joined, String birthDate, String country, String shortDescription, String img) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.joined = joined;
        this.birthDate = birthDate;
        this.country = country;
        this.shortDescription = shortDescription;
        this.img = img;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", joined='" + joined + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", country='" + country + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJoined() {
        return joined;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getCountry() {
        return country;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImg() {
        return img;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int ID;
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String joined;
        private String birthDate;
        private String country;
        private String shortDescription;
        private String img;

        private Builder () {

        }

        public Builder withId(int id) {
            this.ID = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withUserName(String userName) {
            this.username = userName;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withJoined(String joined) {
            this.joined = joined;
            return this;
        }

        public Builder withBirthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder withImg(String img) {
            this.img = img;
            return this;
        }

        public User build() {
            return new User(ID, firstName, lastName, username, password, joined, birthDate,
                    country, shortDescription, img);
        }
    }
}
