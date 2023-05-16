<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Productos</title>
</head>
<body>
		<table>
		
		<tr>
			<td>Id</td>
			<td>Codigo</td>
			<td>Nombre</td>
			<td>Cantidad</td>
			<td>Precio</td>
			<td>Caducidad</td>
			<td>Seccion</td>
		</tr>
		
		<c:forEach items="${productos}" var="producto">
		<tr>
			<td>${producto.id}</td>
			<td>${producto.codigo}</td>
			<td>${producto.nombre}</td>
			<td>${producto.cantidad}</td>
			<td>${producto.precio}</td>
			<td>${producto.caducidad}</td>
			<td>${producto.seccion.nombre}</td>
		</tr>
		</c:forEach>
		</table>
		
		<a href="InsertarForm.jsp">
		<button>Insertar</button>
		</a>
</body>
</html>