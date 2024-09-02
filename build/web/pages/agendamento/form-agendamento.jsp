<%@page import="Model.Agendamento"%>
<%@page import="Model.Servico"%>
<%@page import="Model.Especie"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Model.Marca"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String label = "Cadastrar";
    String action = "insert";
    String nomePet = "";
    
    String dataInicial = (String) request.getAttribute("dataInicial");
    
    Integer idEspecie = 0;
    List<Especie> especies = (List<Especie>) request.getAttribute("especies");
    
    Integer idServico = 0;
    List<Servico> servicos = (List<Servico>) request.getAttribute("servicos");
    
    String rota = (String) request.getAttribute("rota");
    Agendamento agd = (Agendamento) request.getAttribute("agendamento");
    
    if ("/edit".equals(rota)) {
        label = "Alterar";
        action = "update?id=" + agd.getIdAgendamento();
        idEspecie = agd.getIdEspecie();
        idServico = agd.getIdServico();
        nomePet = agd.getNomePet();
        dataInicial = agd.getDataInicial().toString();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <%@ include file="/includes/valida-formulario.jsp" %>
        <title>Agendamento</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>

        <!-- BEM-VINDO -->
        <%@ include file="/includes/bem-vindo.jsp" %>

        <!-- SEÇÃO -->
        <section class="home-section"> 
            <!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <!-- CONTEUDO -->
            <div class="home-content">
                <!-- TITULO -->
                <div class="home-titulo">
                    <h1><%= label%> Agendamento</h1>
                </div>

                <br>

                <!-- FORMULARIO -->
                <form id="formAgendamento" action="<%= action%>" method="POST">
                    <label>Nome Pet</label><br>
                    <input type="text" name="pet" placeholder="Meia Noite" value="<%= nomePet%>"><br><br>
                    
                    <label>Espécie</label><br>
                    <select name="especie">
                        <% if (especies != null) {
                                for (Especie especie : especies) {
                                    if (idEspecie == especie.getId()) {
                        %>
                        <option value="<%= especie.getId()%>" selected><%= especie.getDescricao()%></option>
                        <% } else {%>
                        <option value="<%= especie.getId()%>"><%= especie.getDescricao()%></option>
                        <% }
                        }
                    } %>
                    </select><br><br>
                    
                    <label>Serviço</label><br>
                    <select name="servico">
                        <% if (servicos != null) {
                                for (Servico servico : servicos) {
                                    if (idServico == servico.getId()) {
                        %>
                        <option value="<%= servico.getId()%>" selected><%= servico.getNome()%></option>
                        <% } else {%>
                        <option value="<%= servico.getId()%>"><%= servico.getNome()%></option>
                        <% }
                        }
                    } %>
                    </select><br><br>

                    <label>Data</label><br>
                    <input type="datetime-local" id="data" name="data" value="<%= dataInicial %>"><br><br>
                    
                    <input type="submit" value="<%= label%>">
                </form>
            </div>
        </section>

        <!-- ESPAÇAMENTO FINAL -->
        <br><br><br><br>
    </body>
</html>
