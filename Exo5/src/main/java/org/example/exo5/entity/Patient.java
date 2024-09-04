package org.example.exo5.entity;

import java.util.Date;
import java.util.List;

public class Patient {
    private int id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String photo;
    private List<Consultation> consultations;

    public void setNom(String nom) {
    }

    public void setPrenom(String prenom) {

    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getDateNaissance() {
        return null;
    }

    public void setId(long aLong) {
    }

    public long getId() {
        return id;
    }

    public void setDateNaissance(java.sql.Date dateNaissance) {
    }
}
