<%-- 
    Document   : pedido
    Created on : Jun 27, 2020, 9:50:05 PM
    Author     : pablo.suazo1
--%>

<%@page import="java.util.ArrayList" %>
<%@page import="com.mycompany.supermercado.dbConnection" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido</title>
    </head>
    <body>
        <h1>Creacion de Pedido</h1>
        <form action="guardarPedido" method="post">
            <label>Codigo de cliente: </label>
            <input type="text" name="codCliente" autofocus/>
            <br/>
            <label>Producto: </label>
            <%
                dbConnection conn = new dbConnection();
                    
                ArrayList listProductos = conn.consultaNomProductos();
                int i = 0;
            %>
            <select name="productos">
                <option>Elija un producto</option>
                <%
                    while(i < listProductos.size()){   
                        %>
                        <option value="<%=listProductos.get(i).toString()%>"/><%=listProductos.get(i).toString()%>
                        <%
                        i++;
                    }
                %>
            </select>
            </br>
            <label>Cantidad: </label>
            <input type="text" name="cantidad"/>
            </br>
            <input type="submit" value="Guardar"/>
            </br>
        </form>
        
    </body>
</html>
