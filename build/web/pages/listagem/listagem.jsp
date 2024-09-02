<%@page import="DAO.FavoritoDAO"%>
<%@page import="Model.Favorito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Servico"%>
<%@page import="Model.Categoria"%>
<%@page import="Model.Especie"%>
<%@page import="Model.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    FavoritoDAO favDAO = new FavoritoDAO();
    
    String idCategoria = request.getParameter("categoria");
    
    Especie especie = (Especie) request.getAttribute("especie");
    Categoria categoria = (Categoria) request.getAttribute("categoria");
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    List<Servico> servicos = (List<Servico>) request.getAttribute("servicos");
    
    DecimalFormat df = new DecimalFormat("###,##0.00");
    
    Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
    
    List<Favorito> favoritos = new ArrayList<>();
    if (usuario != null) { favoritos = favDAO.selectByUsuario(usuario.getId()); }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Patinhas & Companhia</title>
    </head>
    <body>
        <%@ include file="/includes/menu.jsp" %>
        <br><br>
        <section>
            <div style="display: flex; justify-content: space-between">
                <div style="display: flex; align-items: center;">
                    <label>Patinhas & <br> Companhia &nbsp;&nbsp;&nbsp;</label>
                        <% if (especie != null) {%>
                    <label>&nbsp;&nbsp;&nbsp; > </label>
                    <label>&nbsp;&nbsp;&nbsp; <%= especie.getDescricao()%></label>
                    <% }%>
                    <label>&nbsp;&nbsp;&nbsp; > </label>
                    <label>&nbsp;&nbsp;&nbsp; <%= categoria.getDescricao()%></label>
                </div>

                <div style="display: flex; align-items: center;">
                    <label>Ordenar por: &nbsp;</label>
                    <select style="width: 200px;">
                        <option>Mais relevantes</option>
                    </select>
                </div>
            </div>
        </section>

        <br>

        <section>
            <% if (especie != null) {%>
            <h1><%= especie.getDescricao()%></h1>
            <% } else {%> 
            <h1><%= categoria.getDescricao()%></h1>
            <%  } %>
            <p>
                Tudo o que é essencial para a vida do seu cachorro tem na Patinhas&Companhia! Se você é tutor de primeira viagem ou já tem muitos peludos em casa, deve estar sempre com atenção redobrada às necessidades e cuidados básicos. Afinal, o importante é nossos amigos estarem felizes e saudáveis, não é mesmo?
            </p>
        </section>
            
            <br><br>
            
        <section>
            <% if (especie != null) {%>
            <div>
                <ul style="list-style: none; display: flex; gap: 30px; padding: 0;">
                    <li>
                        <a href="produto?especie=<%= especie.getId()%>&categoria=3" class="<% if(idCategoria.toString().equals("3")) { %> btn-tab-selected <% } else { %> btn-tab <% } %>">
                            Ração
                        </a>
                    </li>
                    <li>
                        <a href="produto?especie=<%= especie.getId()%>&categoria=4" class="<% if(idCategoria.toString().equals("4")) { %> btn-tab-selected <% } else { %> btn-tab <% } %>">
                            Brinquedos
                        </a>
                    </li>
                    <li>
                        <a href="produto?especie=<%= especie.getId()%>&categoria=9" class="<% if(idCategoria.toString().equals("9")) { %> btn-tab-selected <% } else { %> btn-tab <% } %>">
                            Medicamentos
                        </a>
                    </li>
                </ul>
                <br>
            </div>
            <% } %>
            <br>
            <div style="display: flex; gap: 30px; flex-wrap: wrap;">
                <% if (produtos != null && produtos.size() > 0) { %>
                <% for (Produto produto : produtos) {%>
                <form action="${pageContext.request.contextPath}/carrinho/add">
                    <input type="hidden" name="produtoID" value="<%= produto.getId()%>" />
                    <div class="box-produto">
                        <div class="box-produto-item" style="align-self: end;" >
                            <%  boolean achou = false; %>
                                <% if (favoritos == null || favoritos.isEmpty()) {  %>
                                <a href="${pageContext.request.contextPath}/dashboard/favorito/add?id=<%= produto.getId() %>"><i class="fa-regular fa-heart"></i></a>
                                <%  } else { %>
                                    <% for (Favorito f : favoritos) { 
                                            if (f.getIdProduto() == produto.getId()) {
                                                achou = true;
                                                break;
                                            }
                                        }

                                        if (achou) { %>
                                            <a href="${pageContext.request.contextPath}/dashboard/favorito/remove?id=<%= produto.getId() %>"><i class="fa-solid fa-heart"></i></a>
                                    <%  } else { %>
                                        <a href="${pageContext.request.contextPath}/dashboard/favorito/add?id=<%= produto.getId() %>"><i class="fa-regular fa-heart"></i></a>
                                    <%  }  }  %>
                        </div>
                        <div class="box-produto-item" style="align-self: center;">
                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= produto.getFoto()%>">
                        </div>
                        <div class="box-produto-item"><h3><%= produto.getNome()%></h3></div>
                        <div class="box-produto-item"><h2>R$ <%= df.format(produto.getValor())%></h2></div>
                        <div class="box-produto-item" style="align-self: end;">
                            <button type="submit">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <% } %>
                <% } else if (servicos != null && servicos.size() > 0) { %>
                <% for (Servico servico : servicos) {%>
                
                <div class="box-servico">
                    <img src="${pageContext.request.contextPath}/assets/servicos/<%= servico.getFoto()%>" >
                    <div class="servico-content">
                        <div class="servico-titulo">
                            <b><%= servico.getNome()%></b>
                        </div>
                        <div class="servico-descricao">
                            <%= servico.getDescricao()%>
                        </div>
                        <div class="servico-preco">
                            <b>A partir de R$ <%= df.format(servico.getValor()) %></b>
                        </div>
                    </div>
                        <a href="${pageContext.request.contextPath}/dashboard/agendamento/list" class="servico-btn">Agende Online</a>
                </div>
                    
                <% } %>
                <% } else { %>
                <h1>Nenhum produto encontrado.</h1>
                <% }%>
            </div>
        </section>
            
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        
    </body>
</html>
