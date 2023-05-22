<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<form  method="POST" action="InsertarProducto">
        <label for="codigo">Codigo</label>
        <input type="text" name="codigo">
        <br> <br>
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre">
        <br> <br>
        <label for="cantidad">Cantidad</label>
        <input type="text" name="cantidad">
        <br> <br>
        <label for="precio">Precio</label>
        <input type="text" name="precio">
        <br><br>
         <label for="caducidad">Caducidad</label>
        <input type="date" name="caducidad">
        <br><br> 
        <select name="seccion">
			<option selected="selected"></option>
			<c:forEach items="${secciones}" var="seccion">
				<option value="${seccion.id}">${seccion.nombre}</option>
			</c:forEach>
		</select>
        <br><br>            
        <input type="submit" value="enviar">
    </form>
    <form action="VerProductos">
            <input type="submit" value="volver">
    </form>
</body>
</html>