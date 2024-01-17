/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamr
 */
public class Withdraw_srv extends HttpServlet {
    /**
     * Withdraw - value from parameter withdraw
     */
    static int Withdraw;
    /**
     * message to display in page
     */
    static String Message;
    public static Bankomat bank = new Bankomat();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     * @throws jakarta.servlet.ServletException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Bankomat_srv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Bankomat_srv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("Withdraw=" + Withdraw +"<br>");
            out.println(Message + " ");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException , ServletException {
        Enumeration<String> ParamNames = request.getParameterNames();
        List<String> listParamNames = Collections.list(ParamNames);
        for( String ParamName:listParamNames){
            System.out.println("ParamName " + ParamName );
        }
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws jakarta.servlet.ServletException
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException,IOException {
        String Parameter = request.getParameter("Withdraw");
        try{
            Withdraw = Integer.parseInt(request.getParameter("Withdraw"));
            Message = bank.withdraw(Withdraw);
        }
        // if withdraw value is null
        catch (NumberFormatException e){
            Message = "Null value in number field";
        }
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
