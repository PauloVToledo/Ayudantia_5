<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="estilos.css">
    <title>Tu Aplicación Web</title>
</head>
<body>

<h2>Registro de Usuario</h2>

<form action="<c:url value='/registroUsuario' />" method="post">
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required><br>

    <label for="edad">Edad:</label>
    <input type="number" id="edad" name="edad" required><br>

    <label for="rut">RUT:</label>
    <input type="text" id="rut" name="rut" required><br>

    <label for="direccion">Dirección:</label>
    <input type="text" id="direccion" name="direccion" required><br>

    <label for="numeroTelefono">Número de Teléfono:</label>
    <input type="text" id="numeroTelefono" name="numeroTelefono" required><br>

    <label for="correoElectronico">Correo Electrónico:</label>
    <input type="email" id="correoElectronico" name="correoElectronico" required><br>

    <button type="submit">Registrar</button>
</form>

</body>
</html>
