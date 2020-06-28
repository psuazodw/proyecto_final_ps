<%-- 
    Document   : factura
    Created on : Jun 28, 2020, 12:47:03 AM
    Author     : pablo.suazo1
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factura</title>
    </head>
    <body>
    <center>
        <h1>Factura</h1>
        <sql:setDataSource var="db" 
                           driver="com.mysql.jdbc.Driver" 
                           url="jdbc:mysql://localhost:3306/db_market"
                           user="root" 
                           password="root"/>
        
        <form action="factura.jsp" method="get">
            <label>Codigo de Cliente: </label>
            <input type="text" name="codigo_cliente" id="nombre_producto_InputText"/>
            <input type="submit" value="Buscar"/>
            <br/>
            <br/>
        </form>
        
        <sql:query dataSource="${db}" var="factura">
            SELECT 
                c.nombre,
                c.apellido,
                p.producto,
                p.precio,
                clip.cantidad,
                p.precio * clip.cantidad AS subtotal,
                clip.fecha_creacion
            FROM mkt_cliente_producto clip
            LEFT JOIN mkt_cliente c on clip.id_cliente = c.id_cliente
            LEFT JOIN mkt_producto p on clip.id_producto = p.id_producto
            WHERE clip.id_cliente = <%= request.getParameter("codigo_cliente") %>;
        </sql:query>
        
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Fecha de Creacion</th>
            </tr>
            <c:forEach var="row" items="${factura.rows}">
                <tr>
                    <td><c:out value="${row.nombre}"/></td>
                    <td><c:out value="${row.apellido}"/></td>
                    <td><c:out value="${row.producto}"/></td>
                    <td><c:out value="${row.precio}"/></td>
                    <td><c:out value="${row.cantidad}"/></td>
                    <td><c:out value="${row.subtotal}"/></td>
                    <td><c:out value="${row.fecha_creacion}"/></td>
                </tr>    
            </c:forEach>
        </table>
        <br/>
        <a href="index.jsp">Regresar</a>
    </center>
        
    </body>
</html>
