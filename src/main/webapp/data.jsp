<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Quarto Seguro</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <form id="reservaForm" action="data">
        <label for="data_inicio">Selecione a data de entrada:</label>
        <input type="date" id="data_inicio" name="data_inicio">
        <label for="data_fim">Selecione a data de saída:</label>
        <input type="date" id="data_fim" name="data_fim">
        <input type="button" id="finalizarReserva" value="Finalizar Reserva" onclick="validarData()">  
        <input type="hidden" name="dataReserva" id="dataReservaInput">
    </form>
    <script src="scripts/validador.js"></script>
</body>
</html>
