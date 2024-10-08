<%@page import="Model.Favorito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.FavoritoDAO"%>
<%@page import="java.text.DecimalFormat"%>
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
    FavoritoDAO favDAO = new FavoritoDAO();
    
    List<Produto> racoes = prodDAO.selectByCategoria(3); // RAÇÃO = ID 3
    List<Produto> brinquedos = prodDAO.selectByCategoria(4); // BRINQUEDOS = ID 4
    List<Produto> medicamentos = prodDAO.selectByCategoria(9); // MEDICAMENTO = ID 9
    
    List<Servico> servicos = servicoDAO.select();
    
    Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
    
    List<Favorito> favoritos = new ArrayList<>();
    if (usuario != null) {
        favoritos = favDAO.selectByUsuario(usuario.getId());
    }
    
    DecimalFormat df = new DecimalFormat("###,##0.00");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Inicio</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
         
        <br><br>
        
        <!-- BANNER -->
        <section>
            <div class="banner">
                <img src="${pageContext.request.contextPath}/assets/banners/banner-um.png">
                <img src="${pageContext.request.contextPath}/assets/banners/banner-dois.png">
            </div>
        </section>

        <br><br><br>

        <!-- SERVIÇOS -->
        <section>
            <div style="width: 100%; display: flex; gap: 40px; overflow-x: auto;">
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
                            <b>A partir de R$ <%= df.format(servico.getValor())%></b>
                        </div>
                    </div>
                        <a href="dashboard/agendamento/list" class="servico-btn">Agende Online</a>
                </div>
                <% } %>
            </div>
        </section>

        <br><br><br><br>

        <!-- PROMOÇÕES -->
        <section class="box-slide" >
            <div class="slide-item" >
                <div class="promocoes imagens" >
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-um.png">
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-dois.png">
                    <img src="${pageContext.request.contextPath}/assets/promocoes/promocao-tres.png">
                </div>
                <div class="controles ctl-prom" > 
                    <button class="btn-prev" ></button>
                    <button class="btn-next" >
                        <i class="fa-solid fa-circle-chevron-right"></i>
                    </button>
                </div>
            </div>
        </section>    

        <br><br><br><br>

        <!-- RAÇÕES -->
        <section class="box-slide">
            <div class="slide-item">
                <div class="produtos imagens">
                    <% for (Produto produto : racoes) {%>
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
                            <div class="box-produto-item"><h2>R$ <%= df.format(produto.getValor()) %></h2></div>
                            <div class="box-produto-item" style="align-self: end;">
                                <button type="submit">
                                    <i class="fa-solid fa-cart-shopping"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <% } %>
                </div>
                <div class="controles ctl-prod" > 
                    <button class="btn-prev" ></button>
                    <button class="btn-next" >
                        <i class="fa-solid fa-circle-chevron-right"></i>
                    </button>
                </div>
            </div>
        </section>

        <br><br><br><br> 

        <!-- BRINQUEDOS -->
        <section class="box-slide">
            <div class="slide-item">
                <div class="brinquedos imagens">
                    <% for (Produto produto : brinquedos) {%>
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
                </div>
                <div class="controles ctl-brin" > 
                    <button class="btn-prev" ></button>
                    <button class="btn-next" >
                        <i class="fa-solid fa-circle-chevron-right"></i>
                    </button>
                </div>
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
