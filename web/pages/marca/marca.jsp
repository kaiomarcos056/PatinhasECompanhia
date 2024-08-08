<%@page import="java.util.List"%>
<%@page import="Model.Marca"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Marca> marcas = (List<Marca>) request.getAttribute("marcas");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Marca</title>
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
                    <h1>Marcas</h1>
                    <a href="new" class="btn-link-home" >Nova Marca</a>
                </div>
                
                <br>
                
                <!-- PESQUISA -->
                <form action="list" method="GET" class="form-pesquisa">
                    <input type="text" name="marca" placeholder="Marca">
                    <button type="submit" class="btn-home" >Pesquisar</button>
                </form>
                
                <br>
                
                <!-- MENSGEM DE CADASTRO -->
                <% if(msg != null){ %>
                    <p><%= msg %></p>
                <%      
                    session.removeAttribute("msg"); 
                    }
                %>
                
                <!-- TABELA -->
                <table class="tabela-home">
                <tr>
                    <th>ID</th>
                    <th>Marca</th>
                    <th colspan="2">Ação</th>
                </tr>
                <% if(marcas == null){ %>
                    <tr>
                        <td colspan="3">Marcas não encontradas.</td>
                    </tr>
                <% } else { %>
                    <% for (Marca marca : marcas) { %>
                    <tr>
                        <td><%= marca.getId() %></td>
                        <td><%= marca.getDescricao()%></td>
                        <td>
                            <a href="edit?id=<%= marca.getId() %>" class="edit">
                                <i class="fa-solid fa-pen"></i> Editar
                            </a>
                        </td>
                        <td>
                            <a href="delete?id=<%= marca.getId() %>" class="remove">
                                <i class="fa-regular fa-trash-can"></i> Excluir
                            </a>
                        </td>
                    </tr>
                    <% } %>
                <% } %>
                </table>
            </div>
        </section>
                
                <br><br><br><br>
                
    </body>
</html>