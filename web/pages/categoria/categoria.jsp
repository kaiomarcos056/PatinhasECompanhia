<%@page import="java.util.List"%>
<%@page import="Model.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Categoria</title>
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
                    <h1>Categorias</h1>
                    <a href="new" class="btn-link-home" >Nova Categoria</a>
                </div>

                <br>

                <!-- PESQUISA -->
                <form action="list" method="GET" class="form-pesquisa">
                    <input type="text" name="categoria" placeholder="Categoria">
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
                        <th>Categoria</th>
                        <th colspan="2">Ação</th>
                    </tr>
                    <% if (categorias == null) { %>
                    <tr>
                        <td colspan="3">Categorias não encontradas.</td>
                    </tr>
                    <% } else { %>
                    <% for (Categoria categoria : categorias) {%>
                    <tr>
                        <td><%= categoria.getId()%></td>
                        <td><%= categoria.getDescricao()%></td>
                        <td>
                            <a href="edit?id=<%= categoria.getId()%>" class="edit">
                                <i class="fa-solid fa-pen"></i> Editar
                            </a>
                        </td>
                        <td>
                            <a href="delete?id=<%= categoria.getId()%>" class="remove">
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