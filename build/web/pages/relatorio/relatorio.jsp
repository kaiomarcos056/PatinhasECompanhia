<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <style>
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                padding-top: 100px;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.4);
            }

            .modal-content {
                background-color: #fefefe;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #888;
                width: 30%;
                display: flex;
                flex-direction: column;
            }

            .close {
                color: #aaaaaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }

            input[type="date"] {
                width: 100%;
                font-size: 16px;
                padding: 5px;
                border: 1px solid orange;
                border-radius: 5px;
            }

            .btn-modal{
                align-self: end;
                background: orange;
                border: 1px solid orange;
                color: white;
                font-size: 16px;
                font-weight: 500;
                padding: 10px;
                border-radius: 8px;
                cursor: pointer;
            }

            .btn-modal:hover{
                background: white;
                color: orange;
            }
            
            .btn-relatorio{
                border: none;
                background: none;
                color: var(--azul);
                font-size: 16px;
                cursor: pointer;
                text-decoration: none;
                display: flex;
                align-items: center;
                gap: 10px;
            }
            .btn-relatorio:hover{
                color: var(--laranja);
            }
        </style>
        <title>Relatórios</title>
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
                <div class="home-titulo" >
                    <h1>Relatórios</h1>
                </div>

                <br>

                <!-- TABELA -->
                <table class="tabela-home">
                    <tr>
                        <th>Relatório</th>
                        <th colspan="2">Ação</th>
                    </tr>
                    <tr>
                        <td>Produto Sem Estoque</td>
                        <td>
                            <a href="produto-sem-estoque" class="btn-relatorio" target="_blank">
                                <i class="fa-solid fa-eye"></i> Ver Relatório 
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>Compras Por Usuário</td>
                        <td>
                            <button id="btn-vendas" class="btn-relatorio"><i class="fa-solid fa-eye"></i>Ver Relatório</button>
                            <div id="modal-vendas" class="modal">
                                <div class="modal-content">
                                    <span class="close close-vendas" style="align-self: end;">&times;</span>
                                    <form action="vendas-por-usuario" method="GET" target="_blank">
                                        <label for="dtini">Data Inicial:</label><br>
                                        <input type="date" id="startDate" name="dtini" required><br><br>
                                        <label for="dtfin">Data Final:</label><br>
                                        <input type="date" id="endDate" name="dtfin" required><br><br>
                                        <button type="submit" class="btn-modal">Gerar Relatório</button>
                                    </form>
                                </div>

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Faturamento Diário</td>
                        <td>
                            <button id="btn-faturamento" class="btn-relatorio"><i class="fa-solid fa-eye"></i>Ver Relatório</button>
                            <div id="modal-faturamento" class="modal">
                                <div class="modal-content">
                                    <span class="close close-faturamento" style="align-self: end;">&times;</span>
                                    <form action="faturamento-diario" method="GET" target="_blank">
                                        <label for="dtini">Data Inicial:</label><br>
                                        <input type="date" id="startDate" name="dtini" required><br><br>
                                        <label for="dtfin">Data Final:</label><br>
                                        <input type="date" id="endDate" name="dtfin" required><br><br>
                                        <button type="submit" class="btn-modal">Gerar Relatório</button>
                                    </form>
                                </div>

                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </section>

        <br><br><br><br>

        <script>
            var modalVendas = document.getElementById("modal-vendas");
            var modalFaturamento = document.getElementById("modal-faturamento");

            var btnVendas = document.getElementById("btn-vendas");
            var btnFaturamento = document.getElementById("btn-faturamento");

            var spanVendas = document.getElementsByClassName("close-vendas")[0];
            var spanFaturamento = document.getElementsByClassName("close-faturamento")[0];

            btnVendas.onclick = function () {
                modalVendas.style.display = "block";
            };
            
            btnFaturamento.onclick = function () {
                modalFaturamento.style.display = "block";
            };

            spanVendas.onclick = function () {
                modalVendas.style.display = "none";
            };
            
            spanFaturamento.onclick = function () {
                modalFaturamento.style.display = "none";
            };

            window.onclick = function (event) {
                if (event.target == modalVendas) { modalVendas.style.display = "none"; };
                if (event.target == modalFaturamento) { modalFaturamento.style.display = "none"; };
            };
        </script>


    </body>
</html>