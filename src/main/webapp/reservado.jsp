<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.JavaBeans" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quarto Seguro</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<H1>reserva efetuada!</H1>
	
	<table border=2>
        <thead>
            <tr>
                <th>ID da Reserva</th>
                <th>Data de Reserva</th>
            </tr>
        </thead>
        <tbody>
            <% 
            ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("reserva");
            for (int i = 0; i < lista.size(); i++) {
            %>
            <tr>
                <td><%= lista.get(i).getReserva_id() %></td>
                <td><%= lista.get(i).getData_inicio() %></td>
                <td><a href="javascript: confirmarCancelamento(<%= lista.get(i).getReserva_id() %>)" class="botao1">Cancelar reserva</a></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <script src="scripts/confirmador.js"></script>
</body>
</html>