
function constroiHTML(ret) {
    objetoHTML = document.getElementById("inner_editais");
    objetoHTML.style.visibility = "visible";
    var text = "";
    text += " <form:form action=\"detalhes_edital\">";
    text += "<select id=\"selected_editais\">";

    for (var i = 0; i < ret.editais.length; i++) {
        text += " <option value=\"" + ret.editais[i].titulo + "\">" + ret.editais[i].titulo + "</option>";
    }
    text += "</select>";
    text += "<input type=\"submit\" id=\"edital_more\" value=\"\">";
    text += "</form:form>";
    objetoHTML.innerHTML = text;
}


function getEditaisAjax() {

    var select_id = document.getElementById("projetos");
    var idProjeto = select_id.options[select_id.selectedIndex].value;
    //alert("SELECIONADO: " + value);

    $.ajax({
        type: 'GET',
        async: false,
        url: 'carrega_editais_ajax',
        dataType: 'json',
        data: {
            id_projeto: idProjeto
        },
        success: function(oRetorno) {
            constroiHTML(oRetorno);
            //console.log(oRetorno);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(textStatus + " : " + errorThrown);
        }
    });

}

