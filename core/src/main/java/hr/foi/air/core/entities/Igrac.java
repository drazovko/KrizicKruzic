package hr.foi.air.core.entities;

public class Igrac {
    int id;
    String email;
    boolean zauzet;

    public Igrac(String email, boolean zauzet) {
        this.email = email;
        this.zauzet = zauzet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isZauzet() {
        return zauzet;
    }

    public void setZauzet(boolean zauzet) {
        this.zauzet = zauzet;
    }
}
