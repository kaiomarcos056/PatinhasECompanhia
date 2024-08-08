<%@page import="java.util.List"%>
<%@page import="Model.Especie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Especie> especies = (List<Especie>) request.getAttribute("especies");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Especie</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <!-- BEM-VINDO -->
        <%@ include file="/includes/bem-vindo.jsp" %>
        
        <section class="home-section"> 
            <!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <!-- CONTEUDO -->
            <div class="home-content">
                <!-- TITULO DA PAGINA -->
                <div class="home-titulo">
                    <h1>Especies</h1>
                    <a href="new" class="btn-link-home" >Nova Especie</a>
                </div>

                <br>

                <!-- PESQUISA -->
                <form action="list" method="GET" class="form-pesquisa">
                    <input type="text" name="especie" placeholder="Especie">
                    <button type="submit" class="btn-home" >Pesquisar</button>
                </form>

                <br>

                <!-- MENSGEM DE CADASTRO -->
                <% if (msg != null) {%>
                <p><%= msg%></p>
                <%
                        session.removeAttribute("msg");
                    }
                %>

                <!-- TABELA -->
                <table class="tabela-home">
                    <tr>
                        <th>ID</th>
                        <th>Especie</th>
                        <th colspan="2">Ação</th>
                    </tr>
                    <% if (especies == null) { %>
                    <tr>
                        <td colspan="3">Especies não encontradas.</td>
                    </tr>
                    <% } else { %>
                    <% for (Especie especie : especies) {%>
                    <tr>
                        <td><%= especie.getId()%></td>
                        <td><%= especie.getDescricao()%></td>
                        <td>
                            <a href="edit?id=<%= especie.getId()%>" class="edit">
                                <i class="fa-solid fa-pen"></i> Editar
                            </a>
                        </td>
                        <td>
                            <a href="delete?id=<%= especie.getId()%>" class="remove">
                                <i class="fa-regular fa-trash-can"></i> Excluir
                            </a>
                        </td>
                    </tr>
                    <% } %>
                    <% }%>
                </table>
            </div>
        </section>
    </body>
</html>