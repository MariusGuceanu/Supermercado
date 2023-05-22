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

		<label for="codigo">Codigo</label>
		 <input type="text" name="codigo"
			value="${producto.codigo}">
			
				
		<label for="nombre">Nombre</label>
		 <input type="text" name="nombre"
			value="${producto.nombre}">
			 
		
		<label for="cantidad">Cantidad</label> 
		<input type="text"
			name="cantidad" value="${producto.cantidad}"> 
			
			 <br>
		<label for="precio">Precio</label>
			 <input type="text" name="precio"
			value="${producto.precio}"> 
			<br>
			 <label for="caducidad">Caducidad</label>
		<input type="date" name="caducidad" value="${producto.caducidad}">
		
		 <select name="seccion">
			<c:forEach items="${secciones}" var="seccion">
				<c:if test="${seccion.id==producto.seccion.id}">
					<option selected="selected" value="${seccion.id}">${seccion.nombre}</option>
				</c:if>
				<c:if test="${seccion.id!=producto.seccion.id}">
					<option value="${seccion.id}">${seccion.nombre}</option>
				</c:if>
			</c:forEach>
		</select> 
		
		<br>
		<input type="submit" value="modificar"> 
		
	</form>
	<form action="VerUsuarios">
		<input type="submit" value="volver">
	</form>
</body>
</html>