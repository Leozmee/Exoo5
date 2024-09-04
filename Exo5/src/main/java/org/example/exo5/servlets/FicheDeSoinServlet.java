package org.example.exo5.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exo5.Repository.FicheDeSoinRepository;
import org.example.exo5.entity.FicheDeSoin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/fiches-de-soin")
public class FicheDeSoinServlet extends HttpServlet {

    private FicheDeSoinRepository ficheDeSoinRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exos", "postgres", "KidPaddle");
            ficheDeSoinRepository = new FicheDeSoinRepository(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String consultationIdStr = req.getParameter("consultationId");

        try {
            List<FicheDeSoin> fichesDeSoin = null;
            if (keyword != null) {
                fichesDeSoin = ficheDeSoinRepository.findByContenuContaining(keyword);
            } else if (consultationIdStr != null) {
                Long consultationId = Long.parseLong(consultationIdStr);
                fichesDeSoin = ficheDeSoinRepository.findByConsultationId(consultationId);
            }

            req.setAttribute("fichesDeSoin", fichesDeSoin);
            req.getRequestDispatcher("views/fiche-de-soin-list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
