/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.supermercado;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo.suazo1
 */
public class dbConnection {
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    public void setConnection(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("SQLException: " + e);
        }
    }
    
    public void getConnection(){
        
        String url = "jdbc:mysql://localhost:3306/db_market";
        String user = "root";
        String pass = "root";
        
        try {
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connection to Database successfull...\n");
        } catch (Exception e) {
            System.out.println("SQLException: " + e);
        }
    }
    
    /**
     * 
     * @param cat
     * @return true si la categoria se inserto en la BD
     */
    public boolean insertCategoria(String cat){
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
            
            String query = "INSERT INTO mkt_categoria (categoria, creado) VALUES ('" + cat + "',NOW());";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * 
     * @param nomProd
     * @param precio
     * @param existencia
     * @return true si el producto se inserto en la BD
     */
    public boolean insertProducto(String nomProd, float precio, int existencia){
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
            
            //Insertando producto
            String query = "INSERT INTO mkt_producto (producto, precio, creado, existencia) VALUES ('" + nomProd + "'," + precio + ",NOW()," + existencia + ");";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insertCategoriaProducto(int id_categoria, int id_producto){
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
            
            //Insertando relacion categoria_producto
            String query = "INSERT INTO mkt_categoria_producto (id_categoria, id_producto) VALUES (" + id_categoria + "," + id_producto + ");";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insertClienteProducto(int id_cliente, int id_producto, int cantidad){
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
            
            //Insertando relacion cliente_producto
            String query = "INSERT INTO mkt_cliente_producto (id_cliente, id_producto, cantidad, fecha_creacion) VALUES (" + id_cliente + "," + id_producto + "," + cantidad + ",NOW());";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insertCliente(String nombre, String apellido){
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
            
            //Insertando relacion categoria_producto
            String query = "INSERT INTO mkt_cliente (nombre, apellido, creado) VALUES ('" + nombre + "','" + apellido + "',NOW());";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * 
     * @return: Retorna solamente el nombre de la categoria
     */
    public ArrayList consultaNomCategorias(){
        
        ArrayList listCat = new ArrayList();
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
                        
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT categoria FROM mkt_categoria;");
                        
            while (rs.next()){
                listCat.add(rs.getString("categoria"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        
        return listCat;
    }
    
    /**
     * 
     * @return: Retorna solamente el nombre del producto
     */
    public ArrayList consultaNomProductos(){
        
        ArrayList listProd = new ArrayList();
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
                        
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT producto FROM mkt_producto;");
                        
            while (rs.next()){
                listProd.add(rs.getString("producto"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        
        return listProd;
    }
    
    /**
     * 
     * @param categoria
     * @return id de la categoria seleccionada
     */
    public int consultaIdCategorias(String categoria){

        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
            
            int id_cat = 0;
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id_categoria FROM mkt_categoria WHERE categoria = '" + categoria + "';");
                        
            if (rs.next()){
                id_cat = rs.getInt("id_categoria");
            }
                        
            rs.close();
            stmt.close();
            conn.close();
            
            return id_cat;
            
        } catch (Exception e) {
            System.out.println("SQLExceotion: " + e.getMessage());
        }
        
        return 0;
    }
    
    /**
     * 
     * @return id del ultimo producto guardado
     */
    public int consultaMaxIdProducto(){
        
        
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
            
            int id_prod = 0;
            
            String query = "SELECT max(id_producto) as max_idProd FROM mkt_producto;";
            PreparedStatement stmt1 = conn.prepareStatement(query);
            //stmt = conn.createStatement();
            rs = stmt1.executeQuery();
            
            if (rs.next()){
                id_prod = rs.getInt("max_idProd");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            return id_prod;
            
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        
        return 0;
    }
    
    public int consultaIdProducto(String producto){
        try {
            
            // Conectando a la base de datos
            setConnection();
            getConnection();
            
            int id_prod = 0;
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id_producto FROM mkt_producto WHERE producto = '" + producto + "';");
                        
            if (rs.next()){
                id_prod = rs.getInt("id_producto");
            }
                        
            rs.close();
            stmt.close();
            conn.close();
            
            return id_prod;
            
        } catch (Exception e) {
            System.out.println("SQLExceotion: " + e.getMessage());
        }
        
        return 0;
    }
}