package org.example.exo5.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exo5.Repository.PrescriptionRepository;
import org.example.exo5.entity.Prescription;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/prescriptions")
public class PrescriptionServlet extends HttpServlet {

    private PrescriptionRepository prescriptionRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exos", "postgres", "KidPaddle");
            prescriptionRepository = new PrescriptionRepository(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String consultationIdStr = req.getParameter("consultationId");

        try {
            List<Prescription> prescriptions = null;
            if (keyword != null) {
                prescriptions = prescriptionRepository.findByContenuContaining(keyword);
            } else if (consultationIdStr != null) {
                Long consultationId = Long.parseLong(consultationIdStr);
                prescriptions = prescriptionRepository.findByConsultationId(consultationId);
            }

            req.setAttribute("prescriptions", prescriptions);
            req.getRequestDispatcher("views/prescription-list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
