<%-- 
    Document   : ingresarDatos
    Created on : Jun 22, 2020, 11:09:40 PM
    Author     : pablo.suazo1
--%>

<%@page import="java.util.ArrayList" %>
<%@page import="com.mycompany.supermercado.dbConnection" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <h1>Ingreso de Productos</h1>
        <form action="guardarProducto" method="post">
            <label>Categoria: </label>
            <%
                dbConnection conn = new dbConnection();
                    
                ArrayList listCategories = conn.consultaNomCategorias();
                int i = 0;
            %>
            <select name="category">
                <option>Category</option>
                <%
                    while(i < listCategories.size()){   
                        %>
                        <option value="<%=listCategories.get(i).toString()%>"/><%=listCategories.get(i).toString()%>
                        <%
                        i++;
                    }
                %>
            </select>
            <br/>
            <label>Nombre de Producto: </label>
            <input type="text" name="nomProd" autofocus/>
            <br/>
            <label>Precio: </label>
            <input type="text" name="precio"/>
            <br/>
            <label>Existencia: </label>
            <input type="text" name="existencia"/>
            <br/>
            <input type="submit" value="Guardar"/>
        </form>        
    </body>
</html>
