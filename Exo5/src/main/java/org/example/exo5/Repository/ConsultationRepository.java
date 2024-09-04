package org.example.exo5.Repository;
import org.example.exo5.entity.Consultation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultationRepository {

    private Connection connection;

    public ConsultationRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Consultation> findByNomMedecin(String nomMedecin) throws SQLException {
        String query = "SELECT * FROM Consultation WHERE nom_medecin = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomMedecin);
            ResultSet rs = stmt.executeQuery();
            return mapConsultations(rs);
        }
    }

    public List<Consultation> findByDateConsultationBetween(Date startDate, Date endDate) throws SQLException {
        String query = "SELECT * FROM Consultation WHERE date_consultation BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            return mapConsultations(rs);
        }
    }

    public List<Consultation> findByPatientId(Long patientId) throws SQLException {
        String query = "SELECT * FROM Consultation WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, patientId);
            ResultSet rs = stmt.executeQuery();
            return mapConsultations(rs);
        }
    }

    private List<Consultation> mapConsultations(ResultSet rs) throws SQLException {
        List<Consultation> consultations = new ArrayList<>();
        while (rs.next()) {
            Consultation consultation = new Consultation();
            consultation.setId(rs.getLong("id"));
            consultation.setNomMedecin(rs.getString("nom_medecin"));
            consultation.setDateConsultation(rs.getDate("date_consultation"));
            consultation.setPatientId(rs.getLong("patient_id"));
            consultations.add(consultation);
        }
        return consultations;
    }
}
