<%@page import="Model.Servico"%>
<%@page import="DAO.ServicoDAO"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Produto"%>
<%@page import="java.util.List"%>
<%@page import="DAO.ProdutoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ServicoDAO servicoDAO = new ServicoDAO();
    ProdutoDAO prodDAO = new ProdutoDAO();
    List<Produto> racoes = prodDAO.selectByCategoria(3); // RAÇÃO = ID 3
    List<Produto> brinquedos = prodDAO.selectByCategoria(4); // BRINQUEDOS = ID 4
    List<Produto> medicamentos = prodDAO.selectByCategoria(9); // MEDICAMENTO = ID 9
    List<Servico> servicos = servicoDAO.select();
    Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <!-- FONTES -->
        <%@ include file="/includes/fontes.jsp" %>
        <title>Inicio</title>
    </head>
    <body>
        <%@ include file="/includes/menu.jsp" %>
        <div class="content">
            <section>
                <img src="${pageContext.request.contextPath}/assets/banners/banner-um.png">
            </section>
            
            <section>
                <br>
                <br>
                <div style="width: 100%; display: flex; gap: 20px; overflow-x: auto;">
                <% for(Servico servico : servicos) { %>
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
                </div>
                <br>
                <br>
            </section>
            
            <section>
                <div style="display: flex; gap: 40px; overflow-x: auto; ">
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-um.png">
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-dois.png">
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-tres.png">
                    <br>
                </div>
                <br>
            </section>
                    
            <section>
                <br>
                <br>
                <div style="width: 100%; display: flex; gap: 20px; overflow-x: auto;">
                <% for(Produto produto: racoes) { %>
                <form action="${pageContext.request.contextPath}/carrinho/add">
                    <table border="1" style="min-width: 262px; height: 380px;">
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
                </div>
                <br>
            </section>
                
            <section>
                <br>
                <br>
                <div style="width: 100%; display: flex; gap: 20px; overflow-x: auto;">
                <% for(Produto produto: brinquedos) { %>
                <form action="${pageContext.request.contextPath}/carrinho/add">
                    <table border="1" style="width: 262px; height: 380px;">
                        <tr>
                            <td style="text-align: right;">Favorito</td>
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
                </div>
                <br><br>
            </section>
            
            <section>
                <br>
                <br>
                <div style="width: 100%; display: flex; gap: 20px; overflow-x: auto;">
                <% for(Produto produto: medicamentos) { %>
                <form action="${pageContext.request.contextPath}/carrinho/add">
                    <table border="1" style="width: 262px; height: 380px;">
                        <tr>
                            <td style="text-align: right;">Favorito</td>
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
                </div>
                <br><br><br><br>
            </section>
        </div>
                
        <%@ include file="/includes/rodape.jsp" %>
        
    </body>
</html>
