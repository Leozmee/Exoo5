package org.example.exo5.entity;

import java.util.Date;
import java.util.List;

public class Consultation {
    private int id;
    private Date dateConsultation;
    private String nomMedecin;
    private String prescription;
    private List<FicheDeSoin> fichesDeSoins;

    public void setId(long id) {
    }

    public void setNomMedecin(String nomMedecin) {
    }

    public void setDateConsultation(java.sql.Date dateConsultation) {
    }

    public void setPatientId(long patientId) {

    }
}
