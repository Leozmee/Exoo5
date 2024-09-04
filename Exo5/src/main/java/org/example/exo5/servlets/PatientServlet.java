package org.example.exo5.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exo5.Repository.PatientRepository;
import org.example.exo5.entity.Patient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

    private PatientRepository patientRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exos", "postgres", "KidPaddle");
            patientRepository = new PatientRepository(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String nom = req.getParameter("nom");

        try {
            if (idStr != null) {
                Long id = Long.parseLong(idStr);
                Patient patient = patientRepository.findById(id);
                req.setAttribute("patient", patient);
                req.getRequestDispatcher("views/patient-details.jsp").forward(req, resp);
            } else if (nom != null) {
                List<Patient> patients = patientRepository.findByNom(nom);
                req.setAttribute("patients", patients);
                req.getRequestDispatcher("views/patient-list.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String dateNaissanceStr = req.getParameter("dateNaissance");

        try {
            LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);
            Patient patient = new Patient();
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setDateNaissance(Date.valueOf(dateNaissance));
            patientRepository.save(patient);
            resp.sendRedirect(req.getContextPath() + "/patients?id=" + patient.getId());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String dateNaissanceStr = req.getParameter("dateNaissance");

        try {
            Long id = Long.parseLong(idStr);
            LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);
            Patient patient = new Patient();
            patient.setId(id);
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setDateNaissance(Date.valueOf(dateNaissance));
            patientRepository.update(patient);
            resp.sendRedirect(req.getContextPath() + "/patients?id=" + patient.getId());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");

        try {
            Long id = Long.parseLong(idStr);
            patientRepository.deleteById(id);
            resp.sendRedirect(req.getContextPath() + "/patients");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }



}
