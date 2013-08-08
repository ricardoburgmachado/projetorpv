<%-- 
    Document   : listarnhomologados
    Created on : 07/08/2013, 18:54:56
    Author     : Wolmir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <script type="text/javascript">
function createXMLHttpRequest(){
  // See http://en.wikipedia.org/wiki/XMLHttpRequest
  // Provide the XMLHttpRequest class for IE 5.x-6.x:
  if( typeof XMLHttpRequest === "undefined" ) XMLHttpRequest = function() {
    try { return new ActiveXObject("Msxml2.XMLHTTP.6.0"); } catch(e) {}
    try { return new ActiveXObject("Msxml2.XMLHTTP.3.0");
                        } catch (e) {
                        }
                        try {
                            return new ActiveXObject("Msxml2.XMLHTTP");
                        } catch (e) {
                        }
                        try {
                            return new ActiveXObject("Microsoft.XMLHTTP");
                        } catch (e) {
                        }
                        throw new Error("This browser does not support XMLHttpRequest.")
                    };
                return new XMLHttpRequest();
            }

            var AJAX = createXMLHttpRequest();

            function handler() {
                if (AJAX.readyState === 4 && AJAX.status === 200) {
                    var json = eval('(' + AJAX.responseText + ')');
                    //alert("Tá aqui.");
                    var tbhtml = "";
                    for (var i = 0; i < json.length; i++) {
                        tbhtml += "<tr>";
                        
                        tbhtml += "<th>";
                        tbhtml += json[i].id;
                        tbhtml += "</th>";
                        
                        tbhtml += "<th>";
                        tbhtml += json[i].titulo;
                        tbhtml += "</th>";
                        
                        tbhtml += "<th>";
                        tbhtml += json[i].resumo;
                        tbhtml += "</th>";
                        
                        tbhtml += "<th>";
                        tbhtml += json[i].professor;
                        tbhtml += "</th>";
                        
                        tbhtml += "</tr>";
                    }
                    //alert(tbhtml);
                    document.getElementById("teegoat").innerHTML = tbhtml;
                    
                    //Editar a tabela
                } else if (AJAX.readyState === 4 && AJAX.status !== 200) {
                    alert('Something went wrong... ' + AJAX.status);
                }
            }

            function show() {
                AJAX.onreadystatechange = handler;
                AJAX.open("GET", "http://localhost:8080/projetorpv/view/clpph.jsp");
                AJAX.send("");
                //alert("Here!");
            }
            ;
            
        </script>
    <body onload="show()">
        <h1>GIPA</h1>
        <table>
            <caption>Projetos para Homologar</caption>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Resumo</th>
                    <th>Professor</th>
                </tr>
            </thead>
            <tbody id="teegoat">
                
            </tbody>
        </table>
    </body>
</html>
