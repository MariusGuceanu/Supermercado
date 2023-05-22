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
	<h1>Modificar Producto</h1>
	<form action="ModificarProducto" method="POST">
		<input type="text" value="${producto.id}" hidden="hidden" name="id">

		<label for="codigo">codigo</label> <input type="text" name="codigo"
			value="${producto.codigo}"> <label for="codigo">Codigo</label>
		<input type="text" name="codigo" value="${producto.codigo}"> <br>
		<label for="nombre">Nombre</label> <input type="text" name="nombre"
			value="${producto.nombre}"> <label for="nombre">Nombre</label>
		<input type="text" name="nombre" value="${producto.nombre}"> <br>
		<label for="cantidad">Cantidad</label> <input type="text"
			name="cantidad" value="${producto.cantidad}"> <label
			for="cantidad">Cantidad</label> <input type="text" name="cantidad"
			value="${producto.cantidad}"> <br> <label for="precio">Precio</label>
		<input type="text" name="precio" value="${producto.precio}"> <label
			for="precio">Precio</label> <input type="text" name="precio"
			value="${producto.precio}"> <br> <label for="caducidad">Caducidad</label>
		<input type="date" name="caducidad"> <select name="seccion">
			<option selected="selected"></option>
			<c:forEach items="${secciones}" var="seccion">
				<option value="${seccion.id}">${seccion.nombre}</option>
			</c:forEach>
		</select> 
	</form>
	<form action="VerUsuarios">
		<input type="submit" value="volver">
	</form>
</body>
</html>