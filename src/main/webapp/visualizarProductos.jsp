<%-- 
    Document   : visualizarProductos
    Created on : Jun 27, 2020, 10:00:59 AM
    Author     : pablo.suazo1
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Productos</title>
    </head>
    <body>
    <center>
        <h1>Listado de Productos</h1>
        <sql:setDataSource var="db" 
                           driver="com.mysql.jdbc.Driver" 
                           url="jdbc:mysql://localhost:3306/db_market"
                           user="root" 
                           password="root"/>
        
        <sql:query dataSource="${db}" var="producto">
            SELECT 
                cp.id_categoria,
                c.categoria,
                cp.id_producto,
                p.producto,
                p.precio,
                p.existencia
            FROM mkt_categoria_producto cp
            LEFT JOIN mkt_categoria c on cp.id_categoria = c.id_categoria
            LEFT JOIN mkt_producto p on cp.id_producto = p.id_producto;
        </sql:query>
        <table border="1">
            <tr>
                <th>Id Categoria</th>
                <th>Categoria</th>
                <th>Id Producto</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Existencia</th>
            </tr>
            <c:forEach var="row" items="${producto.rows}">
                <tr>
                    <td><c:out value="${row.id_categoria}"/></td>
                    <td><c:out value="${row.categoria}"/></td>
                    <td><c:out value="${row.id_producto}"/></td>
                    <td><c:out value="${row.producto}"/></td>
                    <td><c:out value="${row.precio}"/></td>
                    <td><c:out value="${row.existencia}"/></td>
                </tr>    
            </c:forEach>
        </table>
        <br/>
        <h3>Busqueda por Producto</h3>
        <form action="visualizarProductos.jsp" method="get">
            <label>Nombre de producto: </label>
            <input type="text" name="nombre_producto" id="nombre_producto_InputText"/>
            <input type="submit" value="Buscar"/>
            <br/>
        </form>
        <sql:query dataSource="${db}" var="un_producto">
            SELECT 
                p.id_producto,
                p.producto,
                c.categoria,
                p.precio,
                p.existencia,
                p.creado    
            FROM mkt_producto p
            LEFT JOIN mkt_categoria_producto cp ON p.id_producto = cp.id_producto
            LEFT JOIN mkt_categoria c ON cp.id_categoria = c.id_categoria
            WHERE producto = '<%= request.getParameter("nombre_producto") %>';
        </sql:query>
        <table border="1">
            <tr>
                <th>Id Producto</th>
                <th>Producto</th>
                <th>Categoria</th>
                <th>Precio</th>
                <th>Existencia</th>
                <th>Fecha de Creacion</th>
            </tr>
            <c:forEach var="row" items="${un_producto.rows}">
                <tr>
                    <td><c:out value="${row.id_producto}"/></td>
                    <td><c:out value="${row.producto}"/></td>
                    <td><c:out value="${row.categoria}"/></td>
                    <td><c:out value="${row.precio}"/></td>
                    <td><c:out value="${row.existencia}"/></td>
                    <td><c:out value="${row.creado}"/></td>
                </tr>    
            </c:forEach>
        </table>
        <br/>
        <a href="index.jsp">Regresar</a>        
    </center>        
    </body>
</html>
