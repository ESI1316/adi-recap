package servlet;

import business.BLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        String cible = request.getParameter("cible");
        String page;
        
        if (cible != null) 
        {
            
            if (cible.equals("resultat")) 
            {
                page = visuResultat(request, response);
            } 
            else 
            {
                if (request.getParameter("cible").equals("identification")) {
                    page = ident(request, response);
                }
                else
                {
                    request.setAttribute("Cible", request.getParameter("cible"));
                    page = "WEB-INF/CulDeSac.jsp";
                }
            }
            
        } 
        else
        {
            page = "WEB-INF/Accueil.jsp";
        }
        
        request.getRequestDispatcher(page).forward(request, response);
    }

    /**
     * gestion d'une demande d'identification d'un secrétaire de club
     *
     * @param request
     * @param response
     * @return
     */
    private String ident(HttpServletRequest request, HttpServletResponse response) {
        String page = null; // ceci générera évidemment une erreur
        return page;
    }

    /**
     * gestion des demandes de visualisation de résultats
     *
     * @param request
     * @param response
     * @return
     */
    private String visuResultat(HttpServletRequest request, HttpServletResponse response) {
        String page = "WEB-INF/liste.jsp";
        
        if (request.getParameter("club") == null)
        {
            request.setAttribute("rc", null);
        } 
        else 
        {
            
            try 
            {
                Integer club = null, equipe = null, jour = null;
                if (!request.getParameter("club").equals("")) 
                    club = Integer.parseInt(request.getParameter("club"));
                
                if (!request.getParameter("equipe").equals("")) 
                    equipe = Integer.parseInt(request.getParameter("equipe"));
                
                if (!request.getParameter("jour").equals("")) 
                    jour = Integer.parseInt(request.getParameter("jour"));
                
                request.setAttribute("rc", business.EncodageBL.getRencontres(club, equipe, jour));
                
            } 
            catch (BLException ex) 
            {
                page = "WEB-INF/error.jsp";
                request.setAttribute("msg", ex.getMessage());
            }

        }
        
        
        return page;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
