
package servlets;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/succes")
public class SuccesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);

        String utilisateur = (session == null)
                ? null
                : (String) session.getAttribute("utilisateur");

        if (utilisateur == null) {
            resp.sendRedirect("formulaire.html");
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");

        resp.getWriter().println("<h1>Bienvenue " + utilisateur + " !</h1>");
    }
}
