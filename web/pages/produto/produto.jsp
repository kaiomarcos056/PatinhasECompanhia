<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String msg = (String) session.getAttribute("msg");
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    
    DecimalFormat df = new DecimalFormat("###,##0.00");
%>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Produto</title>
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
                    <h1>Produtos</h1>
                    <a href="new" class="btn-link-home">Novo Produto</a>
                </div>

                <br>

                <!-- PESQUISA -->
                <form action="list" method="GET" class="form-pesquisa">
                    <input type="text" name="produto" placeholder="produto">
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
                
                <br>
                
                <!-- TABELA -->
                <div style="display: flex; gap: 20px; flex-wrap: wrap; width: 100%">
                <% if (produtos == null) { %>
                <p>Nenhum produto cadastrado.</p>
                <% } else { %>
                <%for (Produto produto : produtos) {%>

                <div class="box-produto" style="width: 400px; height: auto;">
                    <div class="box-produto-item" style="align-self: center;">
                        <img src="${pageContext.request.contextPath}/assets/produtos/<%= produto.getFoto()%>">
                    </div>
                    <div class="box-produto-item">
                        <table border="0" style="width: 100%;">
                            <tr>
                                <td><b>Nome</b></td>
                                <td style="text-align: right;"><%= produto.getId()%> - <%= produto.getNome()%></td>
                            </tr>
                            <tr>
                                <td><b>Categoria</b></td>
                                <td style="text-align: right;"><%= produto.getIdCategoria()%> - <%= produto.getCategoria()%></td>
                            </tr>
                            <tr>
                                <td><b>Marca</b></td>
                                <td style="text-align: right;"><%= produto.getIdMarca()%> - <%= produto.getMarca()%></td>
                            </tr>
                            <tr>
                                <td><b>Especie</b></td>
                                <td style="text-align: right;"><%= produto.getIdEspecie()%> - <%= produto.getEspecie()%></td>
                            </tr>
                            <tr>
                                <td><b>Quantidade</b></td>
                                <td style="text-align: right;"><%= produto.getQuantidade()%></td>
                            </tr>
                            <tr>
                                <td><b>Valor</b></td>
                                <td style="text-align: right;">R$ <%= df.format(produto.getValor())%></td>
                            </tr>

                            <tr>
                                <td colspan="2" style="text-align: center;"> 
                                    <a href="edit?id=<%= produto.getId()%>" class="edit"> 
                                        <i class="fa-solid fa-pen"></i>Editar 
                                    </a> 
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="delete?id=<%= produto.getId()%>" class="remove"> 
                                        <i class="fa-regular fa-trash-can"></i> Excluir 
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                            
                <% } %>
                <% }%>
                </div>
            </div>
        </section>
            
                <br><br><br><br>
                
    </body>
</html>
