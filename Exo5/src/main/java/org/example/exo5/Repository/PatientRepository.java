package org.example.exo5.Repository;

import org.example.exo5.entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private Connection connection;

    public PatientRepository(Connection connection) {
        this.connection = connection;
    }

    public Patient findById(Long id) throws SQLException {
        String query = "SELECT * FROM Patient WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapPatient(rs);
            }
            return null;
        }
    }

    public List<Patient> findByNom(String nom) throws SQLException {
        String query = "SELECT * FROM Patient WHERE nom LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + nom + "%");
            ResultSet rs = stmt.executeQuery();
            return mapPatients(rs);
        }
    }

    public void save(Patient patient) throws SQLException {
        String query = "INSERT INTO Patient (nom, prenom, date_naissance) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setDate(3, java.sql.Date.valueOf(patient.getDateNaissance()));
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                patient.setId(generatedKeys.getLong(1));
            }
        }
    }

    public void update(Patient patient) throws SQLException {
        String query = "UPDATE Patient SET nom = ?, prenom = ?, date_naissance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setDate(3, java.sql.Date.valueOf(patient.getDateNaissance()));
            stmt.setLong(4, patient.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteById(Long id) throws SQLException {
        String query = "DELETE FROM Patient WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Patient mapPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getLong("id"));
        patient.setNom(rs.getString("nom"));
        patient.setPrenom(rs.getString("prenom"));
        patient.setDateNaissance(Date.valueOf(rs.getDate("date_naissance").toLocalDate()));
        return patient;
    }

    private List<Patient> mapPatients(ResultSet rs) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        while (rs.next()) {
            patients.add(mapPatient(rs));
        }
        return patients;
    }
}
