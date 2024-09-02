<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.CarrinhoItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    List<CarrinhoItem> carrinho = (List<CarrinhoItem>) request.getAttribute("carrinho"); 
    String msg = (String) session.getAttribute("msg");
    
    DecimalFormat df = new DecimalFormat("###,##0.00");
    
    double frete = 4.0;
    double total = frete;
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
                
        <script> function disableButton() { document.getElementById("btn-busca-cep").disabled = true; } </script>
        
        <style>
        
        </style>
        
        <title>Carrinho</title>
    </head>
    <body>
        <%@ include file="/includes/menu.jsp" %>
        
        <br><br><br>
        
        <section>
            <!-- BOTOES -->
            <ul class="tabs">
                <li class="tab-link active" data-tab="lista-pedidos">Carrinho</li>
                <% if(carrinho!=null && carrinho.size() > 0) { %>
                <li class="tab-link" data-tab="identificacao">Identificação</li>
                <li class="tab-link" data-tab="entrega">Entrega</li>
                <li class="tab-link" data-tab="pagamento">Pagamento</li>
                <% } %>
            </ul>
            
            <!-- ABAS -->
            <div class="tab-container">
                
                <!-- ITENS DO CARRINHO E FRETE-->
                <div id="lista-pedidos" class="tab-content active">
                    <br><br>
                    <h1>Carrinho de compras</h1>
                    <br><br>
                    <!-- CONTEUDO  -->
                    <div style="display: flex; justify-content: space-between;">
                        <!-- ITENS DO CARRINHO -->
                        <div style="width: 630px">
                            <!-- MENSAGEM DE RETONO -->
                            <% if (msg != null) {%> <label><%= msg%></label><br><br> <% session.removeAttribute("msg"); } %>
                            <!-- CASO CARRINHO NAO ESTEJA VAZIO -->
                            <% if (carrinho != null && carrinho.size() > 0) { %>
                            <!-- PERCORRENDO ITENS NO CARRINHO -->
                            <% for (CarrinhoItem item : carrinho) {%>
                            <form action="${pageContext.request.contextPath}/carrinho/add-item">
                                <input type="hidden" name="produtoID" value="<%= item.getProduto().getId()%>" />
                                <div class="box-item-carrinho">
                                    <!-- BOTÃO EXCLUIR ITEM -->
                                    <a href="carrinho/delete?produtoID=<%= item.getProduto().getId()%>">
                                        <i class="fa-regular fa-trash-can"></i>
                                    </a>
                                    <!-- FOTO ITEM -->
                                    <img src="${pageContext.request.contextPath}/assets/produtos/<%= item.getProduto().getFoto()%>" >
                                    <!-- NOME ITEM -->
                                    <h2><%= item.getProduto().getNome()%></h2>
                                    <div class="preco">
                                        <p><b>R$ <%= df.format(item.getProduto().getValor() * item.getQuantidade())%></b></p>
                                        <!-- CONTROLES DE ADICIONAR E REMOVER QUANTIDADE ITEM -->
                                        <div class="controle">
                                            <button name="action" value="remove">-</button>
                                            <input type="number" min="0" value="<%= item.getQuantidade()%>" disabled>
                                            <button name="action" value="add">+</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <br>
                            <% } %>
                            <!-- CASO CARRINHO ESTEJA VAZIO -->
                            <% } else { %> 
                            <h1>Carrinho vazio</h1> 
                            <% } %>
                        </div>
                        
                        <!-- CALCULA FRETE -->
                        <div class="box-preco">
                            <h2>Frete e Prazo</h2>
                            <br>
                            <input type="text" name="busca-cep" placeholder="Digite seu CEP">
                            <br><br>
                            <button onclick="disableButton()" id="btn-busca-cep">Buscar</button>
                            <br><br><br><br><br><br>
                            <!-- RETIRE NA LOJA -->
                            <div class="box-flag">
                                <img src="${pageContext.request.contextPath}/assets/icones/loja.png" />
                                <div class="labels">
                                    <label><b>Retire na loja</b></label>
                                    <label>Pedido pronto em 5 dias úteis</label>
                                </div>
                                <input type="radio" name="tipo-frete" value="R" checked>
                            </div>
                            <br>
                            <!-- ECONÔMICO -->
                            <div class="box-flag">
                                <img src="${pageContext.request.contextPath}/assets/icones/car.png" />
                                <div class="labels">
                                    <label><b>Econômico</b></label>
                                    <label>Entrega padrão de 5 a 20 dias</label>
                                </div>
                                <input type="radio" name="tipo-frete" value="E">
                            </div>
                        </div>
                    </div>
                    <br><br><br><br><br><br>
                </div>
                                
                <form id="formularioCarrinho" action="carrinho/new" method="POST"> <!-- INICIO FORMULARIO PEGA E VALIDA DADOS DA COMPRA -->
        
                <!-- IDENTIFICAÇÃO -->
                <div id="identificacao" class="tab-content">
                    <br><br>
                    <h1>Identificação</h1>
                    <p>Solicitamos que você confirme os dados inseridos durante o seu cadastro</p>
                    <br><br>
                    <!-- CONTEUDO  -->
                    <section>
                        <div style="width: 900px; margin: 0 auto;">
                            <label>Nome</label>
                            <input type="text" name="nome" placeholder="Kelvin Erick"><br><br>
            
                            <label>Email</label>
                            <input type="text" name="email" placeholder="erick@email.com"><br><br>
            
                            <label>Telefone</label>
                            <input type="text" name="telefone" id="telefone" placeholder="(85) 94002-8922"><br><br>
                            
                            <br>
                            
                            <% if(carrinho != null) { %>
                            <div class="box-list-carrinho">
                                <h2>Resumo do pedido</h2>
                                <br>
                                <table border="0" style="text-align: left;">
                                    <tr>
                                        <th>Produto</th>
                                        <th style="width: 30%;">Descrição</th>
                                        <th style="text-align: center;">Quantidade</th>
                                        <th style="text-align: right;">Vlr Unit</th>
                                        <th style="text-align: right;">Total</th>
                                    </tr>
                                    <% for(CarrinhoItem item : carrinho) { %>
                                    <tr>
                                        <td>
                                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= item.getProduto().getFoto() %>" alt="Descrição da imagem" width="170px">
                                        </td>
                                        <td> <h3><%= item.getProduto().getNome()%></h3> </td>
                                        <td style="text-align: center;"> <%= item.getQuantidade() %> </td>
                                        <td style="text-align: right;"> <p>R$ <%= df.format(item.getProduto().getValor()) %></p> </td>
                                        <td style="text-align: right;"> <p>R$ <%= df.format(item.getProduto().getValor() * item.getQuantidade()) %></p> </td>
                                        <% total += item.getProduto().getValor() * item.getQuantidade(); %>
                                    </tr>
                                    <% } %>
                                </table>
                                <br>
                                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                                <table>
                                    <tr>
                                        <td> <b>Frete:</b> </td>
                                        <td style="text-align: right;"> <p>R$ <%= df.format(frete) %></p> </td>
                                    </tr>
                                </table>
                                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                                <table border="0">
                                    <tr>
                                        <td> <b>Total:</b> </td>
                                        <td style="text-align: right;"> <p>R$ <%= df.format(total) %></p> </td>
                                    </tr>
                                </table>
                            </div>
                            <% } %>
                        </div>
                        <br><br><br><br><br><br>
                    </section>
                </div>
                
                <!-- ENTREGA -->
                <div id="entrega" class="tab-content">
                    <br><br>
                    <h1>Entrega</h1>
                    <p>Solicitamos que você confirme os dados inseridos durante o seu cadastro</p>
                    <br><br>
                    <!-- CONTEUDO  -->
                    <section>
                        <div style="width: 904px; margin: 0 auto;">
                            <div style="width: 100%; display: flex; flex-direction: column;">
                                <label>Endereço</label>
                                <input type="text" name="endereco" placeholder="R. 5"><br>
                                <label>CEP</label>
                                <input type="text" name="cep" id="cep" placeholder="60355-636"><br>
                                <div style="display: flex; gap: 40px;">
                                    <div style="display: flex; flex: 1; flex-direction: column;">
                                        <label>Número</label>
                                        <input type="number" name="numerocasa" placeholder="100">
                                    </div>
                                    <div style="display: flex; flex: 1; flex-direction: column;">
                                        <label>Complemento</label>
                                        <input type="text" name="complemento" placeholder="UFC">
                                    </div>
                                </div><br>
                                <label>Bairro</label>
                                <input type="text" name="bairro" placeholder="Pres. Kennedy"><br>
                                <div style="display: flex; gap: 40px;">
                                    <div style="display: flex; flex: 1; flex-direction: column;">
                                        <label>Cidade</label>
                                        <input type="text" name="cidade" placeholder="Fortaleza">
                                    </div>
                                    <div style="display: flex; flex: 1; flex-direction: column;">
                                        <label>UF</label>
                                        <input type="text" name="uf" placeholder="CE">
                                    </div>
                                </div><br>
                                <label>Ponto de referencia</label>
                                <input type="text" name="pontoreferencia">
                            </div>
                            <br><br><br><br><br><br>
                        </div>
                    </section>
                </div>
                    
                <!-- PAGAMENTO -->
                <div id="pagamento" class="tab-content">
                    <br><br>
                    <h1>Pagamento</h1>
                    <p>Solicitamos que você confirme os dados inseridos durante o seu cadastro</p>
                    <br><br>
                    <!-- CONTEUDO  -->
                    <section>
                        <div style="width: 904px; margin: 0 auto;">
                            <% if(carrinho != null) { %>
                            <div class="box-list-carrinho">
                                <h2>Resumo do pedido</h2>
                                <table border="0" style="text-align: left;">
                                    <tr>
                                        <th>Produto</th>
                                        <th style="width: 30%;">Descrição</th>
                                        <th style="text-align: center;">Quantidade</th>
                                        <th style="text-align: right;">Vlr Unit</th>
                                        <th style="text-align: right;">Total</th>
                                    </tr>
                                    <% for(CarrinhoItem item : carrinho) { %>
                                    <tr>
                                        <td> <img src="${pageContext.request.contextPath}/assets/produtos/<%= item.getProduto().getFoto() %>" alt="Descrição da imagem" width="170px"> </td>
                                        <td> <h3><%= item.getProduto().getNome()%></h3> </td>
                                        <td style="text-align: center;"> <%= item.getQuantidade() %> </td>
                                        <td style="text-align: right;"> <p>R$ <%= df.format(item.getProduto().getValor()) %></p> </td>
                                        <td style="text-align: right;"> <p>R$ <%= df.format(item.getProduto().getValor() * item.getQuantidade()) %></p> </td
                                    </tr>
                                    <% } %>
                                </table>
                                <br>
                                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                                <table border="0">
                                    <tr>
                                        <td> <b>Frete:</b> </td>
                                        <input type="hidden" name="valor-frete" value="<%= frete %>">
                                        <td style="text-align: right;"> <p>R$ <%= df.format(frete) %></p> </td>
                                    </tr>
                                </table>
                                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                                <table border="0">
                                    <tr>
                                        <td> <b>Total:</b> </td>
                                        <input type="hidden" name="valor-total" value="<%= total %>">
                                        <td style="text-align: right;"> <p>R$ <%= df.format(total) %></p> </td>
                                    </tr>
                                </table>
                            </div>
                            <% } %>
                            <br><br>
                            <h1>Cartão de crédito</h1>
                            <div style="width: 100%; display: flex; flex-direction: column;">
                                <label>Número</label>
                                <input type="text" name="numerocartao" placeholder="1234 5678 9101 778"><br>
                                <label>Nome impresso no cartão</label>
                                <input type="text" name="nomecartao" placeholder="CARTAO"><br>
                                <div style="display: flex; gap: 40px;">
                                    <div style="display: flex; flex: 1; flex-direction: column;">
                                        <label>CPF do Titular</label>
                                        <input type="text" name="cpftitular" id="cpf" placeholder="123.456.789-00">
                                    </div>
                                    <div style="display: flex; flex: 1; flex-direction: column;">
                                        <label>Validade</label>
                                        <input type="text" name="validade" id="validade" placeholder="08/23">
                                    </div>
                                </div><br>
                                <label>CVV</label>
                                <input type="text" name="cvv">
                            </div>
                            <br><br><br>
                            <input type="submit" name="name" value="Finalizar Compra">
                        </div>
                        <br><br><br><br><br><br>
                    </section>
                </div>
                            
                </form> <!-- INICIO FORMULARIO PEGA E VALIDA DADOS DA COMPRA -->
                
            </div>
                            
        </section>
                            
        <%@ include file="/includes/valida-formulario.jsp" %>
    </body>
</html>
