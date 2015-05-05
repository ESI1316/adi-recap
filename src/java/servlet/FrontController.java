package servlet;

import business.BLException;
import dto.ClubDto;
import dto.RencontreDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                switch (cible) {
                    case "connexion":
                        page = ident(request, response);
                        break;
                        
                    case "deconnexion":
                        page = "WEB-INF/ident.jsp";
                        request.getSession().removeAttribute("connected");
                        break;
                        
                    case "encodage":
                        page = encodage(request, response);
                        break;
                        
                    default:
                        request.setAttribute("Cible", request.getParameter("cible"));
                        page = "WEB-INF/CulDeSac.jsp";
                        break;
                }
            }
            
        } 
        else
        {
            page = "WEB-INF/Accueil.jsp";
        }
        
        request.getRequestDispatcher(page).forward(request, response);
    }
    
    private String encodage(HttpServletRequest request, HttpServletResponse response)
    {
        String rencontres = request.getParameter("rencontres");
        
        
        if (rencontres != null && !rencontres.isEmpty())
        {
            ArrayList<RencontreDto> list = new ArrayList();
            try {
                JSONArray json = new JSONArray(rencontres);

                for (int i = 0; i < json.length(); ++i)
                {
                    JSONObject object = json.getJSONObject(i);
                    // PAS FINI
                    //Faire la meme chose avec les autres attributs
                    int equipeH = object.getInt("equipeH");
                    int equipeV = object.getInt("equipeV");
                    int scoreH = object.getInt("scoreH");
                    int scoreV = object.getInt("scoreV");
                    // Créer une liste de rencontre
                }

            } catch (JSONException ex) {}
        } 
       
        
        
        return "WEB-INF/encodage.jsp";
    }

    /**
     * gestion d'une demande d'identification d'un secrétaire de club
     *
     * @param request
     * @param response
     * @return
     */
    private String ident(HttpServletRequest request, HttpServletResponse response) 
    {
        String clubStr = request.getParameter("club");
        String passwd  = request.getParameter("password");
        
        if (clubStr != null && passwd != null)
        {
            try {
                if (!clubStr.isEmpty()  && !passwd.isEmpty())
                    business.EncodageBL.identification(Integer.parseInt(clubStr), passwd);
                else
                    throw new BLException("Identifiants invalides");
                
                request.getSession().setAttribute("connected", clubStr);
                
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
        Collection<ClubDto> liste;
        try 
        {
            liste = business.EncodageBL.getAllClubs();
            request.setAttribute("clubs", liste);
        } 
        catch (BLException ex) 
        {
            page = handleError(request, response, ex.getMessage());
            return page;
        }
        
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
                // NumberFormatException à catch
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
                page = handleError(request, response, ex.getMessage());
            }

        }
        
        return page;
    }
    
    private String handleError(HttpServletRequest request, HttpServletResponse response, String msg)
    {
        request.setAttribute("msg", msg);
        return "WEB-INF/error.jsp";
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
