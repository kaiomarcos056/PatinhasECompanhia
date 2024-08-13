<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Servico"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Servico> servicos = (List<Servico>) request.getAttribute("servicos");
    DecimalFormat df = new DecimalFormat("###,##0.00");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Serviço</title>
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
                    <h1>Serviços</h1>
                    <a href="new" class="btn-link-home" >Novo Serviço</a>
                </div>

                <br>

                <!-- PESQUISA -->
                <form action="list" method="GET" class="form-pesquisa">
                    <input type="text" name="servico" placeholder="serviço">
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
                <% if (servicos == null) { %>
                <p>Nenhum serviço cadastrado.</p>
                <% } else { %>
                <% for (Servico servico : servicos) {%>
                <div class="box-servico" style="height: auto;">
                    <img src="${pageContext.request.contextPath}/assets/servicos/<%= servico.getFoto()%>" >
                    <div class="servico-content">
                        <table style="height: 100%">
                            <tr> 
                                <td><b>Nome:</b></td> 
                                <td><%= servico.getNome()%></td>
                            </tr>

                            <tr> <td colspan="2"><b>Descrição:</b></td> </tr>
                            <tr> <td colspan="2"><%= servico.getDescricao()%></td> </tr>
                            <tr> 
                                <td><b>Especie:</b></td> 
                                <td style="text-align: right;"><%= servico.getIdEspecie()%> - <%= servico.getEspecie()%></td>
                            </tr>
                            <tr> 
                                <td><b>Tamanho</b></td>
                                <td style="text-align: right;"><%= servico.getTamanho()%></td>
                            </tr>
                            <tr> 
                                <td><b>Valor:</b></td> 
                                <td style="text-align: right;">R$ <%= df.format(servico.getValor())%></td>
                            </tr>
                            <tr>
                                <td style="text-align: center;"> 
                                    <a href="edit?id=<%= servico.getId()%>" class="edit"> 
                                        <i class="fa-solid fa-pen"></i> Editar 
                                    </a> 
                                </td>
                                <td style="text-align: center;"> 
                                    <a href="delete?id=<%= servico.getId()%>" class="remove"> 
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
