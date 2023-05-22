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

	<form style="margin-left: 5%;" action="buscarProducto">
		<label for="codigo">Introduzca el codigo</label> <input type="text"
			name="codigo"> <input type="submit" value="enviar">
	</form>

	<br>
	<br>
	<table style="margin-left: 5%;">

		<tr>
			<td>Id</td>
			<td>Codigo</td>
			<td>Nombre</td>
			<td>Cantidad</td>
			<td>Precio</td>
			<td>Caducidad</td>
			<td>Seccion</td>
			<td>Modificar</td>
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
				<td><a href="ModificarProducto?id=${producto.id}">Modificar Producto</a></td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<a style="margin-left: 5%;" href="InsertarProducto">
		<button>Insertar</button>
	</a>
</body>
</html>