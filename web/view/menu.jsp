<%@page import="br.com.model.Permissao"%>
<%@page import="br.com.model.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            Permissao permissao = Permissao.ACESSO;
            if(user != null && user.getPermissoes().contains(permissao)) {
        %>

        <li><a href="<c:url value="/projeto_lista_show"/>" class="mais">Projetos</a>
            <ul>
                    <li><a href="<c:url value="/projeto_adiciona_show"/>">Adicionar projeto</a></li> 	
                    <li><a href="<c:url value="/projeto_lista_show"/>">Listar projetos</a></li>
            </ul> 
        </li>
        
        <li><a href="<c:url value="/edital_lista_show"/>" class="mais">Editais</a>
            <ul>
                    <li><a href="<c:url value="/edital_adiciona_show"/>">Adicionar edital</a></li> 	
                    <li><a href="<c:url value="/edital_lista_show"/>">Listar editais</a></li>
            </ul> 
        </li>
        
        <%
            }
        %>

