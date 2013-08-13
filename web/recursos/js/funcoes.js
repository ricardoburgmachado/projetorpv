/* inicio aplicado para adição e remoção de campos de texto*/
/* fonte: http://www.mauriciofaustino.com/exemplos/campos%20dinamicos/#*/
/*
 $(function() {
 //document.getElementById('.removerCapital').style.visibility = 'hidden';
 function removeCampoCusteio() {
 $(".removerCusteio").unbind("click");
 $(".removerCusteio").bind("click", function() {
 i = 0;
 $(".custeios p.campoCusteio").each(function() {
 i++;
 });
 //if (i == 1) {
 //    document.getElementById('.removerCapital').style.visibility = 'hidden';
 //}
 //if (i > 1) {
 //    document.getElementById('.removerCapital').style.visibility = 'visible';
 //}
 
 if (i > 1) {
 $(this).parent().remove();
 }
 });
 }
 
 removeCampoCusteio();
 $(".adicionarCusteio").click(function() {
 novoCampo = $(".custeios p.campoCusteio:first").clone();
 novoCampo.find("input").val("");
 novoCampo.insertAfter(".custeios p.campoCusteio:last");
 removeCampoCusteio();
 });
 });
 
 $(function() {
 function removeCampoCapital() {
 $(".removerCapital").unbind("click");
 $(".removerCapital").bind("click", function() {
 i = 0;
 $(".capitais p.campoCapital").each(function() {
 i++;
 });
 if (i > 1) {
 $(this).parent().remove();
 }
 });
 }
 removeCampoCapital();
 $(".adicionarCapital").click(function() {
 novoCampo = $(".capitais p.campoCapital:first").clone();
 novoCampo.find("input").val("");
 novoCampo.insertAfter(".capitais p.campoCapital:last");
 removeCampoCapital();
 });
 });
 */
/* fim aplicado para adição e remoção de campos de texto*/



function AddCapital() {
    var elemento = document.getElementById('campo_novo_capital');
    var qtd_campo_novo_capital = document.getElementsByClassName("campo_capital").length;

    elemento.innerHTML += '<p class="campo_capital" id="campo_capital_' + qtd_campo_novo_capital + '"> \n\
    <input onkeydown="Mascara(this,Valor);" style="width: 75px; display: block; float: left" type="text" name="capital_val_x" /> \n\
    <textarea style="width: 150px; height: 35px; display: block; float: left; clear: none; margin-left: 10px; margin-top: 0px" name=capital_desc_x cols=35 rows=3></textarea> \n\
    <a style="width:100px;text-decoration:none;color:black;display:block;float:left;margin-top:10px;margin-left:5px" href="#" onclick="deletarCapital(\'campo_capital_' + qtd_campo_novo_capital + '\');return false;">Remover Capital</a></p>';
}
function deletarCapital(id) {
    //alert("DELETAR: "+id);
    var campo = document.getElementById(id);
    document.getElementById('campo_novo_capital').removeChild(campo);
}


function AddCusteio() {
    var elemento = document.getElementById('campo_novo_custeio');
    var qtd_campo_novo_custeio = document.getElementsByClassName("campo_custeio").length;

    elemento.innerHTML += '<p class="campo_custeio" id="campo_custeio_' + qtd_campo_novo_custeio + '"> \n\
    <input onkeydown="Mascara(this,Valor);" style="width: 75px; display: block; float: left" type="text" name="custeio_val_x" /> \n\
    <textarea style="width: 150px; height: 35px; display: block; float: left; clear: none; margin-left: 10px; margin-top: 0px" name=custeio_desc_x cols=35 rows=3></textarea> \n\
    <a style="width:100px;text-decoration:none;color:black;display:block;float:left;margin-top:10px;margin-left:5px" href="#" onclick="deletarCusteio(\'campo_custeio_' + qtd_campo_novo_custeio + '\');return false;">Remover Custeio</a></p>';
}
function deletarCusteio(id) {
    var campo = document.getElementById(id);
    document.getElementById('campo_novo_custeio').removeChild(campo);
}








/*Função que padroniza valor monétario
 * http://forum.wmonline.com.br/topic/204101-varios-tipos-de-mascaras-em-javascript-com-expressao-regular/
 * */
function Mascara(o, f) {
    v_obj = o
    v_fun = f
    setTimeout("execmascara()", 1)
}
function Valor(v) {
    v = v.replace(/\D/g, "") //Remove tudo o que não é dígito
    v = v.replace(/^([0-9]{3}\.?){3}-[0-9]{2}$/, "$1.$2");
    //v=v.replace(/(\d{3})(\d)/g,"$1,$2")
    v = v.replace(/(\d)(\d{2})$/, "$1.$2") //Coloca ponto antes dos 2 últimos digitos
    return v
}

/*Função que Executa os objetos*/
function execmascara() {
    v_obj.value = v_fun(v_obj.value)
}

/*Função que Determina as expressões regulares dos objetos*/
function leech(v) {
    v = v.replace(/o/gi, "0")
    v = v.replace(/i/gi, "1")
    v = v.replace(/z/gi, "2")
    v = v.replace(/e/gi, "3")
    v = v.replace(/a/gi, "4")
    v = v.replace(/s/gi, "5")
    v = v.replace(/t/gi, "7")
    return v
}


//-----------------------------------------------------
//Funcao: MascaraMoeda
//Sinopse: Mascara de preenchimento de moeda
//Parametro:
//   objTextBox : Objeto (TextBox)
//   SeparadorMilesimo : Caracter separador de milésimos
//   SeparadorDecimal : Caracter separador de decimais
//   e : Evento
//Retorno: Booleano
//Autor: Gabriel Fróes - www.codigofonte.com.br
//-----------------------------------------------------
function MascaraMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e) {
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if (whichCode == 13)
        return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
    if (strCheck.indexOf(key) == -1)
        return false; // Chave inválida
    len = objTextBox.value.length;
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        objTextBox.value = '';
    if (len == 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len == 2)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    return false;
}

