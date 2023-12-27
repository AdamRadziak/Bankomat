/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;


/**
 *
 * @author adamr
 */
@WebServlet(urlPatterns = {"/Payment_srv"})
public class Payment_srv extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Payment_srv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Payment_srv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            out.println(Message + " ");
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
    static String Message;
    public static Bankomat bank = new Bankomat();
    // list of nominals 
    public ArrayList<Integer> PayNominalList = new ArrayList<>(Arrays.asList(10,20,50,100,200,500));
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Enumeration<String> ParamNames = request.getParameterNames();
        List<String> listParamNames = Collections.list(ParamNames);
        try{
        for( String ParamName:listParamNames){
            System.out.println("ParamName " + ParamName );
        }}
        catch(NumberFormatException e){
            Message = "Null value in number field";
        }
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
        Enumeration<String> ParamNames = request.getParameterNames();
        List<String> listParamNames = Collections.list(ParamNames);
        ArrayList<Integer> PaymentCountList = new ArrayList<>();
        try{
        for (int i=0; i < listParamNames.size();i++){
            // print parameter name
            PaymentCountList.add(Integer.valueOf(request.getParameter(listParamNames.get(i))));
        }
        Message = bank.payment(PayNominalList,PaymentCountList);
        }
        catch(NumberFormatException e){
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
