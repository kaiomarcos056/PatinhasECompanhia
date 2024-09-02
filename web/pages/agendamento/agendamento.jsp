<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Model.Agendamento"%>
<%@page import="java.util.List"%>
<%@page import="Model.Mensagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Mensagem msg = (Mensagem) session.getAttribute("msg");
    List<Agendamento> agendamentos = (List<Agendamento>) request.getAttribute("agendamentos");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- FullCalendar -->
        <link
            href="https://cdn.jsdelivr.net/npm/fullcalendar@3.10.2/dist/fullcalendar.min.css"
            rel="stylesheet"
            />
        <script src="https://cdn.jsdelivr.net/npm/moment@2.29.1/moment.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@3.10.2/dist/fullcalendar.min.js"></script>

        <!-- Localização em Português -->
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@3.10.2/dist/locale/pt-br.js"></script>

        <style>
            /* Esconder o formulário inicialmente */
            #bookingForm {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                padding: 20px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                z-index: 1000;
            }

            /* Estilos para o Modal */
#eventModal {
    position: fixed;
    z-index: 1000;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.5); /* Fundo semi-transparente */
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal-content {
    background-color: #fefefe;
    padding: 20px;
    border-radius: 4px;
    width: 40%;
    position: relative;
    text-align: left;
}

.close-button {
    position: absolute;
    top: 10px;
    right: 10px;
    color: #aaa;
    font-size: 20px;
    cursor: pointer;
}

.close-button:hover {
    color: #000;
}
        </style>

        <title>Agendamento</title>
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
                    <h1>Agendamentos</h1>
                    <!-- <a href="new" class="btn-link-home" >Nova Marca</a> -->
                </div>

                <br>

                <!-- PESQUISA -->
                <form action="list" method="GET" class="form-pesquisa">
                    <input type="date" id="datePicker" />
                </form>

                <br>

                <!-- MENSGEM DE CADASTRO -->
                <% if(msg != null){ %>
                <p class="<%= msg.getStatus() %>"><%= msg.getMensagem() %></p>
                <%  session.removeAttribute("msg");  } %>
                <br>
                
                <!-- FORMULARIO OCULTO COM OS DADOS DE CADASTRO -->
                <form id="redirectForm" method="POST" action="new">
                    <input type="hidden" name="dtIni" id="dtIni" />
                    <input type="hidden" name="hIni" id="hIni" />
                    <!-- <input type="hidden" name="dtFin" id="dataFinal" /> -->
                    <!-- <input type="hidden" name="hFin" id="horaFinal" /> -->
                </form>

                <!-- CALENDARIO -->
                <div id="calendar"></div>
                
                <!-- -->
                <div id="eventModal" style="display: none;">
                    <div class="modal-content">
                        <span class="close-button">&times;</span>
                        <h2 style="font-size: 26px; margin-bottom: 10px;">Detalhes do Agendamento</h2>
                        <hr><br>
                        <table border="0" style="width: 100%;">
                            <tr>
                                <td><p><strong>Início</strong></p></td>
                                <td><p><strong>Fim</strong></p></td>
                            </tr>
                            <tr>
                                <td><p style="margin-bottom:10px;"><span id="modalEventStart"></span></p></td>
                                <td><p style="margin-bottom:10px;"><span id="modalEventEnd"></span></p></td>
                            </tr>
                            <tr>
                                <td colspan="2"><p><strong>Usuário</strong></p></td>
                            </tr>
                            <tr>
                                <td colspan="2"><p style="margin-bottom:10px;"><span id="usuario"></span></p></td>
                            </tr>
                            <tr>
                                <td><p><strong>Nome Pet</strong></p></td>
                                <td><p><strong>Espécie</strong></p></td>
                            </tr>
                            <tr>
                                <td><p style="margin-bottom:10px;"><span id="pet"></span></p></td>
                                <td><p style="margin-bottom:10px;"><span id="especie"></span></p></td>
                            </tr>
                            <tr>
                                <td colspan="2"><p><strong>Serviço</strong></p></td>
                            </tr>
                            <tr>
                                <td colspan="2"><p style="margin-bottom:20px;"><span id="servico"></span></p></td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">
                                    <a href="#" class="edit" id="editAgendamento" style="font-size: 20px;">
                                        <i class="fa-solid fa-pen"></i> Editar
                                    </a>
                                </td>
                                <td style="text-align: center;">
                                    <a href="#" class="remove" id="deleteAgendamento" style="font-size: 20px;">
                                        <i class="fa-regular fa-trash-can"></i> Excluir
                                    </a>
                                </td>
                            </tr>
                        </table>
                        <br>
                    </div>
                </div>
            </div>
        </section>

        <script>
            $(document).ready(function () {
                // Inicializa o FullCalendar
                $('#calendar').fullCalendar({
                    locale: 'pt-br', // Define o idioma para português
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    defaultView: 'agendaDay',
                    editable: true,
                    selectable: true,
                    selectHelper: true,
                    minTime: '08:00:00', // Hora mínima para agendamentos
                    maxTime: '21:00:00', // Hora máxima para agendamentos
                    allDaySlot: false, // Desativa o slot de "dia inteiro"
                    events: [
                                <% if(agendamentos != null){ %>
                                    <% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                                       for (Agendamento agd : agendamentos) { %>
                                        {
                                            id: '<%= agd.getIdAgendamento() %>',
                                            title: '<%= agd.getNomePet() %> - <%= agd.getNomeServico() %>',
                                            start: '<%= agd.getDataInicial() %>',
                                            end: '<%= agd.getDataFinal() %>',
                                            editable: false,
                                            extendedProps: {
                                                usuario: '<%= agd.getNomeUsuario()%>',
                                                pet: '<%= agd.getNomePet() %>',
                                                especie: '<%= agd.getNomeEspecie()%>',
                                                servico: '<%= agd.getNomeServico()%>',
                                                dtIni: '<%= agd.getDataInicial().format(formatter) %>',
                                                dtFin: '<%= agd.getDataFinal().format(formatter) %>',
                                            }
                                        },
                                    <% } %>
                                <% } %>
                    ],
                    dayClick: function (date) {
                        $('#dataInicial').val(date.format('YYYY-MM-DD'));
                        $('#dataFinal').val(date.format('YYYY-MM-DD'));

                        $('#redirectForm').submit();
                    },
                    select: function (start, end) {
                        $('#dtIni').val(start.format('YYYY-MM-DD'));
                        $('#hIni').val(start.format('HH:mm'));
                        
                        $('#redirectForm').submit();
                    },
                    eventClick: function (event) {
                        $('#modalEventTitle').text(event.title);
                        $('#modalEventStart').text(event.extendedProps ? event.extendedProps.dtIni : '');
                        $('#modalEventEnd').text(event.extendedProps ? event.extendedProps.dtFin : '');
                        $('#usuario').text(event.extendedProps ? event.extendedProps.usuario : '');
                        $('#pet').text(event.extendedProps ? event.extendedProps.pet : '');
                        $('#especie').text(event.extendedProps ? event.extendedProps.especie : '');
                        $('#servico').text(event.extendedProps ? event.extendedProps.servico : '');
                        
                        //$('#codagendamento').val(event.id|| '');
                        $('#editAgendamento').attr('href', 'edit?id='+event.id);
                        $('#deleteAgendamento').attr('href', 'delete?id='+event.id);
                         
                        $('#eventModal').fadeIn();
                    }
                });

                // Evento para mudar a data quando o input for alterado
                $('#datePicker').on('change', function () {
                    var selectedDate = $(this).val(); // Pega o valor da data selecionada
                    $('#calendar').fullCalendar('gotoDate', selectedDate); // Vai para a data selecionada
                    $('#calendar').fullCalendar('changeView', 'agendaDay'); // Muda para a visualização de dia
                });

                // Evento para fechar o modal ao clicar no botão de fechar
                $('.close-button').on('click', function() {
                    $('#eventModal').fadeOut();
                });

                // Fechar o modal ao clicar fora dele
                $(window).on('click', function(event) {
                    if ($(event.target).is('#eventModal')) {
                        $('#eventModal').fadeOut();
                    }
                });
            });
        </script>
    </body>
</html>
