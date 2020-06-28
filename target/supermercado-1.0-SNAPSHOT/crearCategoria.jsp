<%-- 
    Document   : crearCategoria
    Created on : Jun 22, 2020, 11:41:36 PM
    Author     : pablo.suazo1
--%>

<%@page import = "com.mycompany.supermercado.dbConnection" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
    </head>
    <body>
        <h1>Creacion de Categorias</h1>
        <form action="guardarCategoria" method="post">
            <label>Nombre de Categoria: </label>
            <input type="text" name="nomCat" autofocus/>
            <br/>            
            <input type="submit" value="Guardar"/>
        </form>        
    </body>
</html>
