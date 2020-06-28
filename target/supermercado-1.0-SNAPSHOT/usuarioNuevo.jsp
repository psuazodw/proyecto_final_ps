<%-- 
    Document   : usuarioNuevo
    Created on : Jun 27, 2020, 4:49:05 PM
    Author     : pablo.suazo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Usuario</title>
    </head>
    <body>
        <h1>Creacion de Usuario</h1>
        <form action="guardarUsuario" method="post">
            <label>Nombre: </label>
            <input type="text" name="nomUsuario" autofocus/>
            <br/>
            <label>Apellido: </label>
            <input type="text" name="apeUsuario" autofocus/>
            <br/>
            <input type="submit" value="Guardar"/>
        </form> 
    </body>
</html>
