/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.BLException;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zyn
 */
public class UpdateServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String cible = request.getParameter("cible");
        
        switch (cible) {
            case "equipe":
                updateEquipe(request, response);
                break;
            case "club":
                updateClub(request, response);
                break;
        }
    }
    
    private void updateEquipe(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException
    {
        String clubStr = request.getParameter("club");
        String options = "<option value='" 
                                + "' >" + "Aucune équipe choisie" + "</option>";
        
        if (clubStr != null && !clubStr.equals("")) 
        {
            try 
            {
                ArrayList<dto.EquipeDto> equipes = 
                        (ArrayList) business.EncodageBL
                                .getEquipeDeClub(Integer.parseInt(clubStr));
                
                options = equipes.stream().map((equipe) -> "<option value='" + equipe.getNum()
                        + "' >" + equipe.toString() + "</option>").reduce(options, String::concat);
                
            }
            catch (business.BLException ex) 
            {
                String page = "WEB-INF/error.jsp";
                request.setAttribute("msg", ex.getMessage());
                request.getRequestDispatcher(page).forward(request, response);
            }
        }
        
        response.getWriter().write(options);
    }
    
    private void updateClub(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
        String options = "<option value='" 
                                + "' >" + "Aucun club choisi" +" </option>";
        try 
        {
            ArrayList<dto.ClubDto> clubs = (ArrayList) business.EncodageBL.getAllClubs();
            
            options = clubs.stream().map((club) -> "<option value='" + club.getNum()
                        + "' >" + club.toString() + "</option>").reduce(options, String::concat);
            
            response.getWriter().write(options);
           
        }
        catch (BLException ex)
        {
            String page = "WEB-INF/error.jsp";
            request.setAttribute("msg", ex.getMessage());
            request.getRequestDispatcher(page).forward(request, response);
        }
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
