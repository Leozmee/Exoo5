package org.example.exo5.Repository;
import org.example.exo5.entity.Prescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionRepository {

    private Connection connection;

    public PrescriptionRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Prescription> findByContenuContaining(String keyword) throws SQLException {
        String query = "SELECT * FROM Prescription WHERE contenu LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            return mapPrescriptions(rs);
        }
    }

    public List<Prescription> findByConsultationId(Long consultationId) throws SQLException {
        String query = "SELECT p.* FROM Prescription p JOIN Consultation c ON p.consultation_id = c.id WHERE c.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, consultationId);
            ResultSet rs = stmt.executeQuery();
            return mapPrescriptions(rs);
        }
    }

    private List<Prescription> mapPrescriptions(ResultSet rs) throws SQLException {
        List<Prescription> prescriptions = new ArrayList<>();
        while (rs.next()) {
            Prescription prescription = new Prescription();
            prescription.setId(rs.getLong("id"));
            prescription.setContenu(rs.getString("contenu"));
            prescription.setConsultationId(rs.getLong("consultation_id"));
            prescriptions.add(prescription);
        }
        return prescriptions;
    }
}
