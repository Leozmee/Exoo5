package org.example.exo5.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exo5.Repository.ConsultationRepository;
import org.example.exo5.entity.Consultation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/consultations")
public class ConsultationServlet extends HttpServlet {

    private ConsultationRepository consultationRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exos", "postgres", "KidPaddle");
            consultationRepository = new ConsultationRepository(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomMedecin = req.getParameter("nomMedecin");
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");
        String patientIdStr = req.getParameter("patientId");

        try {
            List<Consultation> consultations = null;
            if (nomMedecin != null) {
                consultations = consultationRepository.findByNomMedecin(nomMedecin);
            } else if (startDateStr != null && endDateStr != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);
                consultations = consultationRepository.findByDateConsultationBetween(startDate, endDate);
            } else if (patientIdStr != null) {
                Long patientId = Long.parseLong(patientIdStr);
                consultations = consultationRepository.findByPatientId(patientId);
            }

            req.setAttribute("consultations", consultations);
            req.getRequestDispatcher("views/consultation-list.jsp").forward(req, resp);
        } catch (SQLException | ParseException e) {
            throw new ServletException(e);
        }
    }
}
