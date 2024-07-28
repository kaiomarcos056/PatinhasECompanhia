<%@page import="Model.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String msg = (String) session.getAttribute("msg");
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <!-- FONTES -->
        <%@ include file="/includes/fontes.jsp" %>
        <title>Produto</title>
    </head>
    <body>
        <div class="container" style="display: flex;">
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <div class="home-content" style="width: 100%; height: 100vh; display: flex; flex-direction: column;">

                <div style="width: 100%; padding: 10px; box-shadow: rgba(0, 0, 0, 0.24) 0px 4px 2px -2px; z-index: 8;">
                    <h3>Bem-Vindo</h3>
                </div>

                <div style="width: 100%; padding: 10px; background-color: #04C4D9; color: white;">
                    <h3><i class="fa-solid fa-layer-group"></i>&nbsp;&nbsp; Produtos</h3>
                </div>

                <div style="width: 100%; padding: 50px; background-color: #F8F8F8; flex-grow: 1; margin: 0 auto; overflow-y: auto;">

                    <h1>Produtos</h1>

                    <a href="new">Novo Produto</a>

                    <br>
                    <br>

                    <form action="list" method="GET">
                        <input type="text" name="produto" placeholder="produto">
                        <button type="submit" class="">Pesquisar</button>
                    </form>

                    <br>

                    <% if (msg != null) {%>
                    <p><%= msg%></p>
                    <%
                            session.removeAttribute("msg");
                        }
                    %>

                    <section style="width: 100%; display: flex; gap: 20px; flex-wrap: wrap;">
                        <%
                            if (produtos == null) {
                        %>
                        <p>Nenhum produto cadastrado.</p>
                        <% } else {
                            for (Produto produto : produtos) {
                        %>
                        <table border="1" style="width: 262px; max-height: 380px;">
                            <tr><td colspan="3"><img src="${pageContext.request.contextPath}/assets/produtos/<%= produto.getFoto()%>" alt="Descrição da imagem" width="100%"></td></tr>
                            <tr>
                                <td><b>Nome</b></td>
                                <td><%= produto.getId()%></td>
                                <td><%= produto.getNome()%></td>
                            </tr>
                            <tr>
                                <td><b>Categoria:</b></td>
                                <td><%= produto.getIdCategoria()%></td>
                                <td><%= produto.getCategoria()%></td>
                            </tr>
                            <tr>
                                <td><b>Marca:</b></td>
                                <td><%= produto.getIdMarca()%></td>
                                <td><%= produto.getMarca()%></td>
                            </tr>
                            <tr>
                                <td><b>Especie:</b></td>
                                <td><%= produto.getIdEspecie()%></td>
                                <td><%= produto.getEspecie()%></td>
                            </tr>
                            <tr>
                                <td><b>Quantidade</b></td>
                                <td colspan="2"><%= produto.getQuantidade()%></td>
                            </tr>
                            <tr>
                                <td><b>Valor</b></td>
                                <td colspan="2"><%= produto.getValor()%></td>
                            </tr>
                            <tr>
                                <td> <a href="edit?id=<%= produto.getId()%>" class="edit"> Editar </a> </td>
                                <td colspan="2"> <a href="delete?id=<%= produto.getId()%>" class="remove"> Excluir </a> </td>
                            </tr>
                        </table>
                        <% } }%>
                    </section>
                </div>

            </div>
        </div>
    </body>
</html>
