<%@page import="Model.Servico"%>
<%@page import="Model.Categoria"%>
<%@page import="Model.Especie"%>
<%@page import="Model.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Especie especie = (Especie) request.getAttribute("especie");
    Categoria categoria = (Categoria) request.getAttribute("categoria");
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    List<Servico> servicos = (List<Servico>) request.getAttribute("servicos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patinhas & Companhia</title>
    </head>
    <body>
        <%@ include file="/includes/menu.jsp" %>
        <br>
        <br>
        <section style="width: 1186px; margin: 0 auto;">
            <div style="display: flex; justify-content: space-between">
                <div style="display: flex; align-items: center;">
                    <label>Patinhas & <br> Companhia &nbsp;&nbsp;&nbsp;</label>
                    <% if(especie != null){ %>
                    <label>&nbsp;&nbsp;&nbsp; > </label>
                    <label>&nbsp;&nbsp;&nbsp; <%= especie.getDescricao() %></label>
                    <% } %>
                    <label>&nbsp;&nbsp;&nbsp; > </label>
                    <label>&nbsp;&nbsp;&nbsp; <%= categoria.getDescricao() %></label>
                </div>
                
                <div>
                    <label>Ordenar por: &nbsp;</label>
                    <select>
                        <option>Mais relevantes</option>
                    </select>
                </div>
            </div>
        </section>
        <br>
        <section style="width: 1186px; margin: 0 auto;">
            <% if(especie != null) { %>
            <h1><%= especie.getDescricao() %></h1>
            <% } else { %> 
            <h1><%= categoria.getDescricao() %></h1>
            <%  } %>
            <p>
                Tudo o que é essencial para a vida do seu cachorro tem na Patinhas&Companhia! Se você é tutor de primeira viagem ou já tem muitos peludos em casa, deve estar sempre com atenção redobrada às necessidades e cuidados básicos. Afinal, o importante é nossos amigos estarem felizes e saudáveis, não é mesmo?
            </p>
        </section>
        <br>
        <section style="width: 1186px; margin: 0 auto;">
            <% if(especie != null) { %>
            <div>
                <ul style="list-style: none; display: flex; gap: 30px; padding: 0;">
                    <li>
                        <a href="produto?especie=<%= especie.getId() %>&categoria=3">
                        Ração
                        </a>
                    </li>
                    <li>
                        <a href="produto?especie=<%= especie.getId() %>&categoria=4">
                        Brinquedos
                        </a>
                    </li>
                    <li>
                        <a href="produto?especie=<%= especie.getId() %>&categoria=9">
                        Medicamentos
                        </a>
                    </li>
                </ul>
            </div>
            <br>
            <% } %>
            <div style="display: flex; gap: 30px; flex-wrap: wrap;">
                <% if(produtos != null && produtos.size() > 0) { %>
                    <% for (Produto produto : produtos) { %>
                    <form action="${pageContext.request.contextPath}/carrinho/add">
                    <table border="1" style="width: 262px; height: 380px;">
                        <tr>
                            <td style="text-align: right;">Add Favorito</td>
                        </tr>
                        <tr>
                            <input type="hidden" name="produtoID" value="<%= produto.getId() %>" />
                            <td><img src="${pageContext.request.contextPath}/assets/produtos/<%= produto.getFoto() %>" alt="Descrição da imagem" width="100%"></td>
                        </tr>
                        <tr>
                            <td><%= produto.getNome()%></td>
                        </tr>
                        <tr>
                            <td><b>R$ <%= produto.getValor()%></b></td>
                        </tr>
                        <tr>
                            <td style="text-align: right;">
                                <input type="submit" value="Adicionar ao Carrinho" />
                            </td>
                        </tr>
                    </table>
                    </form>
                    <% } %>
                <% } else if(servicos != null && servicos.size() > 0){ %>
                    <% for (Servico servico : servicos) { %>
                    <table border="1" style="width: 300px; height: 380px;">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/assets/servicos/<%= servico.getFoto() %>" width="100%">
                        </td>
                    </tr>
                    <tr>
                        <td><b><%= servico.getNome()%></b></td>
                    </tr>
                    <tr>
                        <td><%= servico.getDescricao()%></td>
                    </tr>
                    <tr>
                        <td><b>A partir de R$ <%= servico.getValor()%></b></td>
                    </tr>
                    <tr>
                        <td>
                            <a href="list/servico/">Agende Online</a>
                        </td>
                    </tr>
                    </table>
                    <% } %>
                <% } else { %>
                <h1>Nenhum produto encontrado.</h1>
                <% } %>
            </div>
        </section>
        <br>
        <br>
        <br>
        <br>
    </body>
</html>
