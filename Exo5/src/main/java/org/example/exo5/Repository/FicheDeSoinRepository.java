package org.example.exo5.Repository;
import org.example.exo5.entity.FicheDeSoin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FicheDeSoinRepository {

    private Connection connection;

    public FicheDeSoinRepository(Connection connection) {
        this.connection = connection;
    }

    public List<FicheDeSoin> findByContenuContaining(String keyword) throws SQLException {
        String query = "SELECT * FROM FicheDeSoin WHERE contenu LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            return mapFichesDeSoin(rs);
        }
    }

    public List<FicheDeSoin> findByConsultationId(Long consultationId) throws SQLException {
        String query = "SELECT f.* FROM FicheDeSoin f JOIN Consultation c ON f.consultation_id = c.id WHERE c.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, consultationId);
            ResultSet rs = stmt.executeQuery();
            return mapFichesDeSoin(rs);
        }
    }

    private List<FicheDeSoin> mapFichesDeSoin(ResultSet rs) throws SQLException {
        List<FicheDeSoin> fiches = new ArrayList<>();
        while (rs.next()) {
            FicheDeSoin fiche = new FicheDeSoin();
            fiche.setId(rs.getLong("id"));
            fiche.setContenu(rs.getString("contenu"));
            fiche.setConsultationId(rs.getLong("consultation_id"));
            fiches.add(fiche);
        }
        return fiches;
    }
}
