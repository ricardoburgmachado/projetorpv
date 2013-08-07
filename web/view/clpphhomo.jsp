<%-- 
    Document   : clpphhomo
    Created on : 07/08/2013, 18:47:45
    Author     : Wolmir
--%>

<%@page import="br.com.us17model.ItemProjeto"%>
<%@page import="br.com.us17dao.ItemProjetoDAO"%>
<%@page import="br.com.us17dao.ItemProjetoMock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String ids = request.getParameter("item_projeto_id");
    Integer id = Integer.parseInt(ids);
    ItemProjetoDAO pdao = ItemProjetoMock.getInstance();
    ItemProjeto ip = new ItemProjeto();
    ip.setId(id);
    pdao.homologar(ip);
    out.print("Suquicés");
    out.flush();
%>
