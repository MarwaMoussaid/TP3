package ma.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp")
           .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if ("admin".equals(username) && "password".equals(password)) {
            req.getSession().setAttribute("user", username);
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            req.setAttribute("erreur", "Identifiants incorrects.");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp")
               .forward(req,resp);
        }
    }
}
