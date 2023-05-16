<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<form  method="POST" action="InsertarProducto">
        <label for="codigo">Codigo</label>
        <input type="text" name="nombre">
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
         <label for="caducidad">caducidad</label>
        <input type="date" name="caducidad">
        <br><br>
        <input type="submit" value="Enviar">
    </form>
</body>
</html>