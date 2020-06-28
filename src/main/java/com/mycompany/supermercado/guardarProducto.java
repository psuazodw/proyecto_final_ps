/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.supermercado;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablo.suazo1
 */
@WebServlet(name = "guardarProducto", urlPatterns = {"/guardarProducto"})
public class guardarProducto extends HttpServlet {

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
            out.println("<title>Guardar Producto</title>");            
            out.println("</head>");
            out.println("<body>");
            
            dbConnection conn = new dbConnection();
            
            // Guardando producto
            String cat = request.getParameter("category");
            String prod = request.getParameter("nomProd");
            float precio = Float.parseFloat(request.getParameter("precio"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));
            
            //Guardando nuevo producto
            if (conn.insertProducto(prod, precio, existencia)){
            
                // Obteniendo el id de la categoria seleccionada y del ultimo producto creado
                int idCat = conn.consultaIdCategorias(cat);
                int idProd = conn.consultaMaxIdProducto();
                conn.insertCategoriaProducto(idCat, idProd);
                out.println("<h3>Producto guardada...</h3>");
                
            }
            
            out.println("<br/>");
            out.println("<a href=\"index.jsp\">Regresar</a>");
            out.println("</body>");
            out.println("</html>");
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
