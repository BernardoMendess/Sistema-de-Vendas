function aplicarMascara(elemento, funcao) {
    setTimeout(() => {
      elemento.value = funcao(elemento.value);
    }, 1);
  }
  
function formatarCNPJ(cnpj) {
    cnpj = cnpj.replace(/\D/g, ""); // Remove tudo o que não é dígito
    cnpj = cnpj.replace(/^(\d{2})(\d)/, "$1.$2"); // Coloca . em volta dos dois primeiros dígitos
    cnpj = cnpj.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3"); // Coloca ponto entre o terceiro e o quarto dígitos
    cnpj = cnpj.replace(/\.(\d{3})(\d)/, ".$1/$2"); // Coloca barra entre o sexto e o sétimo dígitos
    cnpj = cnpj.replace(/\/(\d{4})(\d)/, "/$1-$2"); // Coloca hífen entre o décimo segundo e o décimo terceiro dígitos
    return cnpj;
}

function formatarTelefone(telefone) {
    telefone = telefone.replace(/\D/g, ""); // Remove tudo o que não é dígito
    telefone = telefone.replace(/^(\d{2})(\d)/g, "($1) $2"); // Coloca parênteses em volta dos dois primeiros dígitos
    telefone = telefone.replace(/(\d)(\d{4})$/, "$1-$2"); // Coloca hífen entre o quarto e o quinto dígitos
    return telefone;
}

window.onload = function () {
    document.getElementById('cnpj').onkeyup = function () {
        aplicarMascara(this, formatarCNPJ);
    };
    
    document.getElementById('telefone').onkeyup = function () {
        aplicarMascara(this, formatarTelefone);
    };
};
  