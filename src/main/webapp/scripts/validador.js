/**
 * sistema reserva de hotel, validar formulário
 */
function validarCadastro() {
    let nome = document.frmCadastro.nome.value;
    let telefone = document.frmCadastro.telefone.value;
    let email = document.frmCadastro.email.value;

    if (nome === "") {
        alert('Preencha o campo nome');
        document.frmCadastro.nome.focus();
        return false;
    } else if (telefone === "") {
        alert('Preencha o campo telefone');
        document.frmCadastro.telefone.focus();
        return false;
    } else if (email === "") {
        alert('Preencha o campo email');
        document.frmCadastro.email.focus();
        return false;
    } else {
        // Se todos os campos estiverem preenchidos, envie o formulário
        document.frmCadastro.submit();
    }
}

function validarData() {
    var dataInicio = document.getElementById('data_inicio').value;
    var dataFim = document.getElementById('data_fim').value;
    
    if (dataInicio && dataFim) {
        var dataAtual = new Date().toISOString().split('T')[0]; // Obtém a data atual no formato YYYY-MM-DD
        
        if (dataInicio >= dataAtual && dataFim >= dataInicio) {
            document.getElementById('dataReservaInput').value = dataInicio;
            document.getElementById('reservaForm').submit();
        } else {
            alert('Verifique as datas selecionadas.');
        }
    } else {
        alert('Por favor, selecione ambas as datas.');
    }
}
