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

	<form style="margin-left: 5%;" action="VerProductos" method="POST">
		<label for="codigo">Introduzca el codigo</label> <input type="text"
			name="codigo"> <input type="submit" value="enviar">
	</form>
	
	<form action="Filtrar" method="POST">
		<input type="text" name="max" placeholder="Precio maximo">
		<input type="text" name="min" placeholder="Precio minimo">
		<input type="Submit" value="Filtrar por precios">
	</form>
	
	<form action="EliminarMultiple" method="GET">
	<label>Introduzca codigos separados por comas para borrar varios productos</label>
		<input type="text" name="codigos">
		<input type="submit" value="Eliminar ID's">
	</form>

	<br>
	<br>
	<form method="post" action="EliminarCompleto">
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
			<td>Eliminar</td>
			<td>Eliminar Completamente</td>
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
				<td><a href="EliminarProducto?id=${producto.id}">Eliminar</a></td>
				<td><input type="checkbox" value="${producto.id}" name="idsproductos">Eliminar Completamente</td>
			</tr>
		</c:forEach>
	</table>

	<br>
		<input type="submit" value="EliminarCompleto">
	</form>
	<br>
	<a style="margin-left: 5%;" href="InsertarProducto">
		<button>Insertar</button>
	</a>
	
</body>
</html>