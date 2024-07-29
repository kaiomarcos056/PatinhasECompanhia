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

        <!-- FAVICON -->
        <link rel="icon" href="${pageContext.request.contextPath}/assets/icones/favicon.ico" type="image/x-icon">

        <title>Inicio</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <!-- BANNER -->
        <section>
            <div class="banner">
                <img src="${pageContext.request.contextPath}/assets/banners/banner-um.png">
                <img src="${pageContext.request.contextPath}/assets/banners/banner-dois.png">
            </div>
        </section>
        
        <br><br> 
        
        <!-- SERVIÇOS -->
        <section>
            <div style="width: 100%; display: flex; gap: 20px; overflow-x: auto;">
                <% for (Servico servico : servicos) {%>
                <table border="1" style="width: 300px; height: 380px;">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/assets/servicos/<%= servico.getFoto()%>" width="100%">
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
        </section>
            
        <br><br>  
            
        <!-- PROMOÇÕES -->
        <section style="position: relative;">
            <div style="display: flex;">
                <div class="promocoes" style="/*border: 1px solid blue;*/ z-index: 8; position: relative;">
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-um.png">
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-dois.png">
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-tres.png">
                </div>
                <div class="tns-controls" style="/*border: 1px solid red;*/ width: 50px; z-index: 10; position: absolute; right: 0;
                     background: rgb(255,255,255); background: linear-gradient(90deg, rgba(255, 255, 255, 0) 50.24%, rgba(242, 159, 5, 0.2) 100%);"> 
                    <button class="tns-prev" style="display: none;">Anterior</button>
                    <button class="tns-next" style="width: 100%; height: 216px; background: none; border: none; color: orange;">
                        <i class="fa-solid fa-circle-chevron-right" style="font-size: 32px;"></i>
                    </button>
                </div>
            </div>
        </section>    

        <br><br> 
            
        <!-- RAÇÕES -->
        <section>
            <div style="width: 100%; display: flex; gap: 40px; overflow-x: auto;">
                <% for (Produto produto : racoes) {%>
                <form action="${pageContext.request.contextPath}/carrinho/add">
                    <input type="hidden" name="produtoID" value="<%= produto.getId()%>" />

                    <div class="box-produto">
                        <div class="box-produto-item" style="align-self: end;" >
                            <a href=""><i class="fa-regular fa-heart"></i></a>
                        </div>
                        <div class="box-produto-item" style="align-self: center;">
                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= produto.getFoto()%>">
                        </div>
                        <div class="box-produto-item"><h3><%= produto.getNome()%></h3></div>
                        <div class="box-produto-item"><h2>R$ <%= produto.getValor()%></h2></div>
                        <div class="box-produto-item" style="align-self: end;">
                            <button type="submit">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <% } %>
            </div>
        </section>
            
        <br><br><br><br> 
            
        <!-- BRINQUEDOS -->
        <section>
            <div style="width: 100%; display: flex; gap: 40px; overflow-x: auto;">
                <% for (Produto produto : brinquedos) {%>
                <form action="${pageContext.request.contextPath}/carrinho/add">
                    <input type="hidden" name="produtoID" value="<%= produto.getId()%>" />

                    <div class="box-produto">
                        <div class="box-produto-item" style="align-self: end;" >
                            <a href=""><i class="fa-regular fa-heart"></i></a>
                        </div>
                        <div class="box-produto-item" style="align-self: center;">
                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= produto.getFoto()%>">
                        </div>
                        <div class="box-produto-item"><h3><%= produto.getNome()%></h3></div>
                        <div class="box-produto-item"><h2>R$ <%= produto.getValor()%></h2></div>
                        <div class="box-produto-item" style="align-self: end;">
                            <button type="submit">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <% } %>
            </div>
        </section>
            
        <br><br><br><br> 
            
        <!-- MEDICAMENTO -->
        <section>
            <div style="width: 100%; display: flex; gap: 40px; overflow-x: auto;">
                <% for (Produto produto : medicamentos) {%>
                <form action="${pageContext.request.contextPath}/carrinho/add">
                    <input type="hidden" name="produtoID" value="<%= produto.getId()%>" />

                    <div class="box-produto">
                        <div class="box-produto-item" style="align-self: end;" >
                            <a href=""><i class="fa-regular fa-heart"></i></a>
                        </div>
                        <div class="box-produto-item" style="align-self: center;">
                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= produto.getFoto()%>">
                        </div>
                        <div class="box-produto-item"><h3><%= produto.getNome()%></h3></div>
                        <div class="box-produto-item"><h2>R$ <%= produto.getValor()%></h2></div>
                        <div class="box-produto-item" style="align-self: end;">
                            <button type="submit">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <% }%>
            </div>
        </section>
                
        <br><br><br><br> 
            
        <!-- JS SLIDE -->
        <%@ include file="/includes/slide.jsp" %>
        
        <!-- FOOTER -->
        <%@ include file="/includes/rodape.jsp" %>

    </body>
</html>
