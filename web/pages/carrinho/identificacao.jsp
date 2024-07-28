<%
    double frete = 4.0;
    double total = frete;
%>

<div id="identificacao" class="city" style="display:none">
    <h1>Identificação</h1>
    <p>Solicitamos que você confirme os dados inseridos durante o seu cadastro</p>
    <br>
    <section style="width: 1186px; margin: 0 auto;">
        <div style="width: 900px; margin: 0 auto;">
            <label>Nome e sobrenome</label>
            <input type="type" name="name" style="width: 100%;">
            <br>
            <br>
            <label>Email</label>
            <input type="type" name="name" style="width: 100%;">
            <br>
            <br>
            <label>Telefone</label>
            <input type="type" name="name" style="width: 100%;">
            <br>
            <br>
            <% if(carrinho != null) { %>
            <div style="padding: 20px; border: 2px solid orange; border-radius: 40px; display: flex; flex-flow: column;">
                <h1>Resumo do pedido</h1>
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
                        <td>
                            <h3><%= item.getProduto().getNome()%></h3>
                        </td>
                        <td style="text-align: center;"> <%= item.getQuantidade() %> </td>
                        <td style="text-align: right;"> <p>R$ <%= item.getProduto().getValor() %></p> </td>
                        <td style="text-align: right;"> <p>R$ <%= item.getProduto().getValor() * item.getQuantidade() %></p> </td>
                        <% total += item.getProduto().getValor() * item.getQuantidade(); %>
                    </tr>
                <% } %>
                </table>
                
                <br>
                
                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                <table border="0">
                    <tr>
                        <td>
                            <b>Frete:</b>
                        </td>
                        <td style="text-align: right;"> <p>R$ <%= frete %></p> </td>
                    </tr>
                </table>
                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                <table border="0">
                    <tr>
                        <td>
                            <b>Total:</b>
                        </td>
                        <td style="text-align: right;"> <p>R$ <%= total %></p> </td>
                    </tr>
                </table>
                
            </div>
            <% } %>

            <br>
            <br>
            
            <div style="display: flex; justify-content: space-between;">
                <button>Voltar</button>
                <button>Proximo</button>
            </div>
            <br>
            <br>
            <br>
        </div>
    </section>
</div>