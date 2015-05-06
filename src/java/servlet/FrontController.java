package servlet;

import business.BLException;
import dto.ClubDto;
import dto.EquipeDto;
import dto.RencontreDto;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

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
            switch (cible) {
                case "resultat":
                    page = visuResultat(request, response);
                    break;
                case "connexion":
                    page = ident(request, response);
                    break;

                case "deconnexion":
                    page = "WEB-INF/ident.jsp";
                    request.getSession().removeAttribute("connected");
                    request.getSession().removeAttribute("password");
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
        else
        {
            page = "WEB-INF/Accueil.jsp";
        }
        
        request.getRequestDispatcher(page).forward(request, response);
    }
    
    private String encodage(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
        try {
            setClubsAttribute(request);
        } catch (BLException ex) {
            return handleError(request, ex.getMessage());
        }
        
        String journeeStr = request.getParameter("journee");
        String scoreHStr = request.getParameter("score-home");
        String scoreVStr = request.getParameter("score-visiteur");
        String datePrevueStr = request.getParameter("datePrevue");
        String dateReelleStr = request.getParameter("dateReelle"); 
        String equipeHStr = request.getParameter("equipe-home");
        String equipeVStr = request.getParameter("equipe-visiteur");
        String clubHNumStr = request.getParameter("club-home");
        String clubHNomStr = request.getParameter("nomClubH");
        String clubVNumStr = request.getParameter("club-visiteur");
        String clubVNomStr = request.getParameter("nomClubV");
        String password = (String) request.getSession().getAttribute("password");
        
        if (isNotNull(journeeStr)    && isNotNull(scoreHStr) && 
            isNotNull(scoreVStr)     && isNotNull(datePrevueStr) &&
            isNotNull(dateReelleStr) && isNotNull(equipeHStr) && 
            isNotNull(equipeVStr)    && isNotNull(clubHNomStr) &&
            isNotNull(clubHNumStr)   && isNotNull(clubVNumStr) &&
            isNotNull(clubVNomStr))
        {
            try 
            {
                StringBuilder errorMsg = new StringBuilder();
                int journee = 0, scoreH = 0, scoreV = 0,
                    equipeH = 0, equipeV = 0, clubHNum = 0, clubVNum = 0;
                try 
                {
                    journee = Integer.parseInt(journeeStr);
                    if (journee < 1)
                        errorMsg.append("La journée doit être strictement positive\n");
                } 
                catch (NumberFormatException nfe)
                {
                    errorMsg.append("La journée n'est pas un nombre\n");
                }
                try 
                {
                    scoreH = Integer.parseInt(scoreHStr); 
                    if (scoreH < 1)
                        errorMsg.append("Le score (home) doit être strictement positif\n");
                } 
                catch (NumberFormatException nfe)
                {
                    errorMsg.append("Le score (home) n'est pas un nombre\n");
                }
                
                try
                {
                    scoreV = Integer.parseInt(scoreVStr); 
                    if (scoreV < 1)
                        errorMsg.append("Le score (visiteur) doit être strictement positif\n");
                } 
                catch (NumberFormatException nfe)
                {
                    errorMsg.append("Le score (visiteur) n'est pas un nombre\n");
                }
                
                try 
                {
                    equipeH = Integer.parseInt(equipeHStr);
                    if (equipeH < 0)
                        errorMsg.append("L'id de l'équipe (home) doit être plus grand que zéro\n");
                } 
                catch (NumberFormatException nfe)
                {
                    errorMsg.append("L'équipe (home) n'est pas un nombre\n");
                }
                
                try 
                {
                    equipeV = Integer.parseInt(equipeVStr);
                    if (equipeV < 0)
                        errorMsg.append("L'id de l'équipe (visiteur) doit être plus grand que zéro\n");
                } 
                catch (NumberFormatException nfe)
                {
                    errorMsg.append("L'équipe (visiteur) n'est pas un nombre\n");
                }
                
                try 
                {
                    clubHNum = Integer.parseInt(clubHNumStr);
                    if (clubHNum < 0)
                        errorMsg.append("L'id du club (home) doit être plus grand que zéro\n");
                } 
                catch (NumberFormatException nfe)
                {
                    errorMsg.append("Le numéro du club (home) n'est pas un nombre\n");
                }
                    
                    
                try
                {
                    clubVNum = Integer.parseInt(clubVNumStr);
                    if (clubVNum < 0)
                        errorMsg.append("L'id du club (visiteur) doit être plus grand que zéro\n");
                } 
                catch (NumberFormatException nfe)
                {
                    errorMsg.append("Le numéro du club (visiteur) n'est pas un nombre\n");
                }
                
                
                Date datePrevue = null, dateReelle = null;
                try 
                {
                    DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
                    datePrevue = df.parse(datePrevueStr);
                    dateReelle = df.parse(dateReelleStr);
                }
                catch (ParseException pe)
                {
                    errorMsg.append("Erreur de date \n");
                }
                
                if (!errorMsg.toString().isEmpty())
                {
                    throw new BLException(errorMsg.toString());
                }
                else 
                {
                   // Est-ce bien le password qu'on passe en paramètre ?
                   // Non précisé dans la doc
                   ClubDto clubH =  new ClubDto(clubHNum, clubHNomStr, password);
                   ClubDto clubV = new ClubDto(clubVNum, clubVNomStr, password);
                   EquipeDto equipeHDto = new EquipeDto(clubH, equipeH);
                   EquipeDto equipeVDto = new EquipeDto(clubV, equipeV);
                   int id =0;
                   RencontreDto rencontre = new RencontreDto(id, equipeHDto,
                          equipeVDto, datePrevue, journee, scoreH, scoreV, dateReelle);
                   List<RencontreDto> list = new ArrayList(1);
                   list.add(rencontre);
                   business.EncodageBL.setRencontres(list); 
                   
                }
            }
            catch (BLException ex) 
            {
            }
            
        }
        return "WEB-INF/encodage.jsp";
    }
    
    private boolean isNotNull(String s)
    {
       return s != null && !s.isEmpty();
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
                request.getSession().setAttribute("password", passwd);
                
            } catch (BLException ex) {
                
                request.setAttribute("InvalidIdent", ex.getMessage());
            }
            
        }
        return "WEB-INF/ident.jsp";
    }
    
    private void setClubsAttribute(HttpServletRequest request) throws BLException
    {
        if (request.getAttribute("clubs") == null)
        {
            Collection<ClubDto> liste = business.EncodageBL.getAllClubs();
            request.setAttribute("clubs", liste);
        }
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
        
        try {
            setClubsAttribute(request);
        } catch (BLException ex) {
            return handleError(request, ex.getMessage());
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
                page = handleError(request, ex.getMessage());
            }

        }
        
        return page;
    }
    
    private String handleError(HttpServletRequest request, String msg)
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
