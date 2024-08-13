<div id="pagamento" class="aba" style="display:none">
    <br>
    <br>
    
    <h1>Pagamento</h1>
    
    <p>Solicitamos que você confirme os dados inseridos durante o seu cadastro</p>
    
    <br>
    <br>    
    
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
                        <td>
                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= item.getProduto().getFoto() %>" alt="Descrição da imagem" width="170px">
                        </td>
                        <td>
                            <h3><%= item.getProduto().getNome()%></h3>
                        </td>
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
                        <td>
                            <b>Frete:</b>
                        </td>
                        <input type="hidden" name="valor-frete" value="<%= frete %>">
                        <td style="text-align: right;"> <p>R$ <%= df.format(frete) %></p> </td>
                    </tr>
                </table>
                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                <table border="0">
                    <tr>
                        <td>
                            <b>Total:</b>
                        </td>
                        <input type="hidden" name="valor-total" value="<%= total %>">
                        <td style="text-align: right;"> <p>R$ <%= df.format(total) %></p> </td>
                    </tr>
                </table>
                
            </div>
            <% } %>
            
            <br>
            <br>
            
            <h1>Cartão de crédito</h1>
                <table style="width: 100%">
                    <tr>
                        <td colspan="2"> <label>Número</label></td>
                    </tr>
                    <tr>
                        <td colspan="2"> <input type="text" name="numero-cartao" style="width: 95%;" placeholder="1234 5678 9101 778"> </td>
                    </tr>
                    
                    <tr><td colspan="2">&nbsp;</td></tr>
                    
                    <tr>
                        <td colspan="2"> <label>Nome impresso no cartão</label></td>
                    </tr>
                    <tr>
                        <td colspan="2"> <input type="text" name="nome-cartao" style="width: 95%;" placeholder="CARTAO"><br></td>
                    </tr>
                    
                    <tr><td colspan="2">&nbsp;</td></tr>
                    
                    <tr>
                        <td> <label>CPF do Titular</label></td>
                        <td> <label>Validade</label></td>
                    </tr>
                    <tr>
                        <td> <input type="text" name="cpf-titular" style="width: 90%;" placeholder="123.456.789-00"></td>
                        <td> <input type="text" name="validade" style="width: 90%;" placeholder="08/23"></td>
                    </tr>
                    
                    <tr><td colspan="2">&nbsp;</td></tr>
                    
                    <tr>
                        <td> <label>CVV</label></td>
                    </tr>
                    <tr>
                        <td> <input type="text" name="cvv" style="width: 90%;"><br></td>
                    </tr>
                </table>
            
            <br>
            <br>
            <br>
            
            <!--<button class="tab-button" onclick="openCity('final-compra');changeBorder(this)">Finalizar compra</button>-->
            
            <input type="submit" name="name" value="Finalizar Compra">
           
           <!-- FIM FORM CRIAR COMPRA -->
           </form>
        </div>
        
    </section>
   
    <br><br><br><br><br><br>
    
</div>