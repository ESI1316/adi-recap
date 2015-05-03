package servlet;

import business.BLException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                if (cible.equals("identification")) {
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
        
        String clubStr = request.getParameter("club");
        String passwd  = request.getParameter("password");
        
        if (clubStr != null && passwd != null)
        {
            try {
                if (!clubStr.isEmpty()  && !passwd.isEmpty())
                    business.EncodageBL.identification(Integer.parseInt(clubStr), passwd);
                else
                    throw new BLException("Identifiants invalides");

            } catch (BLException ex) {
                request.setAttribute("InvalidIdent", ex.getMessage());
            }
            
        }
        return "WEB-INF/ident.jsp";
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
        String clubStr = request.getParameter("club");
        String equipeStr = request.getParameter("equipe");
        String jourStr = request.getParameter("jour");
     
        if (clubStr == null)
        {
            request.setAttribute("rc", null);
        } 
        else 
        {
            try 
            {
                Integer club = null, equipe = null, jour = null;
                if (!clubStr.equals("")) 
                    club = Integer.parseInt(clubStr);
                
                if (!equipeStr.equals("")) 
                    equipe = Integer.parseInt(equipeStr);
                
                if (!jourStr.equals("")) 
                    jour = Integer.parseInt(jourStr);
                
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
