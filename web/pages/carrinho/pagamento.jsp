<div id="pagamento" class="city" style="display:none">
    <h1>Pagamento</h1>
    
    <p>Solicitamos que você confirme os dados inseridos durante o seu cadastro</p>
    
    <br>
    <br>    
    
    <section style="width: 1186px; margin: 0 auto;">
                
        <div style="width: 904px; margin: 0 auto;">
            
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
            
            <h1>Cartão de crédito</h1>
            
            <form>
                <table style="width: 100%">
                    <tr>
                        <td colspan="2"> <label>Número</label></td>
                    </tr>
                    <tr>
                        <td colspan="2"> <input type="text" name="endereco" style="width: 95%;"> </td>
                    </tr>
                    <tr>
                        <td colspan="2" <label>Nome impresso no cartão</label></td>
                    </tr>
                    <tr>
                        <td colspan="2"> <input type="text" name="endereco" style="width: 95%;"><br></td>
                    </tr>
                    <tr>
                        <td> <label>CPF do Titular</label></td>
                        <td> <label>Validade</label></td>
                    </tr>
                    <tr>
                        <td> <input type="text" name="endereco" style="width: 90%;"></td>
                        <td> <input type="text" name="endereco" style="width: 90%;"></td>
                    </tr>
                    <tr>
                        <td> <label>CVV</label></td>
                    </tr>
                    <tr>
                        <td> <input type="text" name="endereco" style="width: 90%;"><br></td>
                    </tr>
                </table>
            </form>
            
            <br>
            <br>
            <br>
            
            <button class="tab-button" onclick="openCity('final-compra');changeBorder(this)">Finalizar compra</button>
        </div>
        
    </section>
   
    <br>
    <br>
    <br>
    <br>
    
</div>