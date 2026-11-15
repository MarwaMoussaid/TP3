
package servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // Afficher le formulaire
        resp.sendRedirect("formulaire.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        String nom = req.getParameter("nom");
        String email = req.getParameter("email");
        String ageStr = req.getParameter("age");

        // Validation du nom
        if (nom == null || nom.trim().isEmpty()) {
            resp.sendRedirect("formulaire.html?erreur=nom_vide");
            return;
        }

        // Validation de l'email
        if (email == null || !email.contains("@")) {
            resp.sendRedirect("formulaire.html?erreur=email_invalide");
            return;
        }

        // Validation de l'âge
        int age = 0;

        try {
            age = Integer.parseInt(ageStr);

            if (age < 18 || age > 120) {
                resp.sendRedirect("formulaire.html?erreur=age_min");
                return;
            }

        } catch (NumberFormatException e) {
            resp.sendRedirect("formulaire.html?erreur=age_invalide");
            return;
        }

        // Créer une session
        HttpSession session = req.getSession();

        session.setAttribute("utilisateur", nom);

        // Redirection vers la page de succès
        resp.sendRedirect("succes");
    }
}
