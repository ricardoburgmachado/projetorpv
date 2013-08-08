<%-- 
    Document   : clpph
    Created on : 07/08/2013, 16:40:31
    Author     : Wolmir
--%>

<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="br.com.us17dao.ItemProjetoDAO"%>
<%@page import="br.com.us17dao.ItemProjetoMock"%>
<%@page import="br.com.us17model.ItemProjeto"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ItemProjetoDAO pdao = ItemProjetoMock.getInstance();
    List<ItemProjeto> prjs = pdao.getNHomologados();
    JSONArray js_projs = new JSONArray();
    for (ItemProjeto item_projeto: prjs) {
        JSONObject js_proj = new JSONObject();
        js_proj.put("id", item_projeto.getId());
        js_proj.put("titulo", item_projeto.getTitulo());
        js_proj.put("resumo", item_projeto.getResumo());
        js_proj.put("professor", item_projeto.getProfessor());
        js_projs.add(js_proj);
    }
    out.print(js_projs);
    out.flush();
%>
