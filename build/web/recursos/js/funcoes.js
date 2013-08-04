/* inicio aplicado para adição e remoção de campos de texto*/
/* fonte: http://www.mauriciofaustino.com/exemplos/campos%20dinamicos/#*/

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

/* fim aplicado para adição e remoção de campos de texto*/




