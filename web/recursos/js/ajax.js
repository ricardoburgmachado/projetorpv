
function constroiHTML(ret) {
    objetoHTML = document.getElementById("editais");
    var text = "";

    for (var i = 0; i < ret.length; i++) {
        text += " <option value=\"" + ret[i].id + "\">" + ret[i].titulo + "</option>";        
    }
    
    objetoHTML.innerHTML = text;
}


function getEditaisAjax() {
    
    var select_id = document.getElementById("projetos");
    var idProjeto = select_id.options[select_id.selectedIndex].value;
    
    if(idProjeto == 0){
        objetoHTML = document.getElementById("editais");
        var text = "";
        objetoHTML.innerHTML = text;
        return;
    }
    //alert("SELECIONADO: " + value);le Technology education to people everywhere, in order to help them achieve their dreams and change the world.

    $.ajax({
        type: 'GET',
        async: false,
        url: 'edital_filtra_ajax',
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

