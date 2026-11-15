package ma.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

import ma.app.model.Produit;
import ma.app.model.ProduitDAO;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {
    private ProduitDAO dao;
    @Override
    public void init() {
      dao = (ProduitDAO) getServletContext().getAttribute("produitDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      String action = req.getParameter("action");
        if (action == null) {
            req.setAttribute("produits", dao.findAll());
            req.getRequestDispatcher("/WEB-INF/views/produits/list.jsp")
               .forward(req, resp);
        }
        else if ("new".equals(action)) {

            req.setAttribute("produit", new Produit());

            req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp")
               .forward(req, resp);
        }
        else if ("edit".equals(action)) {
            try {
                Long id = Long.parseLong(req.getParameter("id"));

                Produit produit = dao.findById(id);

                if (produit == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

                req.setAttribute("produit", produit);

                req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp")
                   .forward(req, resp);

            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String action = req.getParameter("action");
    if ("delete".equals(action)) {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            dao.delete(id);
            req.getSession().setAttribute("flash","Produit supprimé avec succès");
            resp.sendRedirect(req.getContextPath() + "/produits");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return;
    }
    String idParam=req.getParameter("id");
    String nom=req.getParameter("nom");
    String description = req.getParameter("description");
    String prixParam= req.getParameter("prix");
    String stockParam = req.getParameter("stock");
    Long id = null;
    if (idParam !=null && !idParam.isBlank()) {
        try {
         id = Long.parseLong(idParam);
            if (dao.findById(id) == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
    }
    Map<String, String> erreurs = new HashMap<>();
    if (nom == null) {
        nom = "";
    } else {
        nom = nom.trim();
    }

    if (nom.length() < 2 || nom.length() > 50) {
        erreurs.put("nom", "Le nom doit contenir entre 2 et 50 caractères!!!");
    }
    if (description==null) {
        description= "";
    }
    if (description.length() > 255) {
        erreurs.put("description","La description ne doit pas dépasser 255 caractères!!");
    }
    BigDecimal prix=null;
    try {
        if (prixParam==null || prixParam.isBlank()) {
            erreurs.put("prix", "Le prix est obligatoire!!");
        } else {
            prix=new BigDecimal(prixParam);
            if (prix.compareTo(BigDecimal.ZERO) <= 0) {
                erreurs.put("prix", "Le prix doit être positif!!");
                 }
        }
    } catch (NumberFormatException e) {
        erreurs.put("prix", "Le prix doit être un nombre décimal valide!!");
    }    int stock =0;

    try {
        if (stockParam != null && !stockParam.isBlank()) {
         stock =Integer.parseInt(stockParam);
            if (stock < 0) {
                erreurs.put("stock", "Le stock doit être supérieur ou égal à 0");
            }
        }
    } catch (NumberFormatException e) {
      erreurs.put("stock", "Le stock doit être un entier valide");
    }
 Produit produit=new Produit(id, nom, description, prix, stock);
    if (!erreurs.isEmpty()) {
        req.setAttribute("erreurs", erreurs);
        req.setAttribute("produit", produit);
        req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp")
           .forward(req, resp);
        return;
    }

    boolean modification = (id != null);
    dao.save(produit);
    if (modification) {
        req.getSession().setAttribute("flash","Produit " + nom + " modifié avec succès.");
    } else {
        req.getSession().setAttribute("flash","Produit "+nom+" enregistré");
    }
  resp.sendRedirect(req.getContextPath() + "/produits");
}

}
