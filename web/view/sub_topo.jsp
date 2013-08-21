<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.com.model.Usuario"%>


<%
    Usuario user = (Usuario) request.getSession().getAttribute("usuario");
%>
<c:set value="usuario" var="user"></c:set>
<div class="sub_topo">

    <div class="sub_sub">
        <div class="dados_usuario">
            <b>Usuário logado:</b> ${usuario.nome}
        </div>
    <a title="Sair do sistema" class="btn_sair" href="logoff?id=${usuario.id}"></a>
    </div>

</div>
