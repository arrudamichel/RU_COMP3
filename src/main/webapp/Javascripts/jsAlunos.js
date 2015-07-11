function validaCPF(strCPF){
    var Soma;
    var Resto;
    Soma = 0;   
    //strCPF  = RetiraCaracteresInvalidos(strCPF,11);
    if (strCPF.value == "00000000000"){
	alert("CPF inválido");
        strCPF.focus();
    }
    
    for (i=1; i<=9; i++)
	Soma = Soma + parseInt(strCPF.value.substring(i-1, i)) * (11 - i); 
    Resto = (Soma * 10) % 11;
    if ((Resto == 10) || (Resto == 11)) 
	Resto = 0;
    if (Resto != parseInt(strCPF.value.substring(9, 10)) ){
	alert("CPF inválido");
        campo.focus();
    }
	Soma = 0;
    for (i = 1; i <= 10; i++)
       Soma = Soma + parseInt(strCPF.value.substring(i-1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;
    if ((Resto == 10) || (Resto == 11)) 
	Resto = 0;
    if (Resto != parseInt(strCPF.value.substring(10, 11) ) ){
	alert("CPF inválido");
        campo.focus();
    }
    return true;
}