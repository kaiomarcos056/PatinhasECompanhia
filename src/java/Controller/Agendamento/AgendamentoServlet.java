package Controller.Agendamento;

import DAO.AgendamentoDAO;
import DAO.EspecieDAO;
import DAO.ServicoDAO;
import Model.Agendamento;
import Model.Especie;
import Model.Marca;
import Model.Mensagem;
import Model.Servico;
import Model.Usuario;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AgendamentoServlet extends HttpServlet {
    private EspecieDAO especieDAO;
    private ServicoDAO servicoDAO;
    private AgendamentoDAO agendamentoDAO;
    
    @Override
    public void init() {
        especieDAO = new EspecieDAO();
        servicoDAO = new ServicoDAO();
        agendamentoDAO = new AgendamentoDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String rota = request.getServletPath();
        String acao = request.getPathInfo();
        if (acao == null) {
            acao = "/";
        }

        System.out.println("MARCA SERVLET");

        System.out.println("ROTA = " + rota);
        System.out.println("ACAO = " + acao);

        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        switch (acao) {
            case "/new":
            case "/edit":
                form(request, response, acao);
                break;
            case "/insert":
                Mensagem insert = insert(request, response);
                session.setAttribute("msg", insert);
                response.sendRedirect("list");
                break;
            case "/delete":
                Mensagem delete = delete(request, response);
                session.setAttribute("msg", delete);
                response.sendRedirect("list");
                break;
            case "/update":
                Mensagem update = update(request, response);
                session.setAttribute("msg", update);
                response.sendRedirect("list");
                break;
            default:
                List<Agendamento> agendamentos = list(request, response);
                request.setAttribute("agendamentos", agendamentos);
                dispatcher = request.getRequestDispatcher("/pages/agendamento/agendamento.jsp");
                dispatcher.forward(request, response);
        }
    }

    private List<Agendamento> list(HttpServletRequest request, HttpServletResponse response){
        List<Agendamento> agendamentos = null; 
        try {
            agendamentos = agendamentoDAO.select();
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return agendamentos;
    }

    private void form(HttpServletRequest request, HttpServletResponse response, String acao) {
        try{
            if("/edit".equals(acao)){
                Integer id = Integer.valueOf(request.getParameter("id"));
                Agendamento agendamento = agendamentoDAO.selectById(id);
                request.setAttribute("agendamento", agendamento);
            }
            String dtIni = request.getParameter("dtIni");
            String hIni = request.getParameter("hIni");
            
            String dataInicial = "";
            if (dtIni != null && hIni != null) {
                dataInicial= dtIni+"T"+hIni;
            }
            request.setAttribute("dataInicial", dataInicial);
            
            List<Especie> especies = especieDAO.select();
            request.setAttribute("especies", especies);
            
            List<Servico> servicos =  servicos = servicoDAO.select();
            request.setAttribute("servicos", servicos);
            
            request.setAttribute("rota", acao);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/agendamento/form-agendamento.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Mensagem insert(HttpServletRequest request, HttpServletResponse response) {
        Mensagem msg = new Mensagem();
        try {
            HttpSession session = request.getSession(false);
            
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            
            if (usuarioLogado == null) { response.sendRedirect(request.getContextPath()+"/auth/login"); }
            
            Integer idUsuario = usuarioLogado.getId();
            
            String nomePet = request.getParameter("pet");
            Integer idServico = Integer.valueOf(request.getParameter("servico"));
            Integer idEspecie = Integer.valueOf(request.getParameter("especie"));
            String dataHoraStr = request.getParameter("data");
            
            LocalDateTime dataInicial = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime dataFinal = dataInicial.plusHours(1);
            
            Agendamento agendamento = new Agendamento();
            agendamento.setNomePet(nomePet);
            agendamento.setIdEspecie(idEspecie);
            agendamento.setIdServico(idServico);
            agendamento.setIdUsuario(idUsuario);
            agendamento.setDataInicial(dataInicial);
            agendamento.setDataFinal(dataFinal);
            
            agendamentoDAO.insert(agendamento);
            
            msg.setMensagem("Agendamento cadastrado com sucesso.");
            msg.setStatus("sucesso");
        } 
        catch (Exception e) {
            msg.setMensagem(e.getMessage());
            msg.setStatus("erro");
        }
        return msg;
    }
    
    private Mensagem delete(HttpServletRequest request, HttpServletResponse response) {
        Mensagem msg = new Mensagem();
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            agendamentoDAO.delete(id);
            
            msg.setMensagem("Agendamento excluido com sucesso.");
            msg.setStatus("sucesso");
        } 
        catch (Exception e) {
            msg.setMensagem(e.getMessage());
            msg.setStatus("erro");
        }
        return msg;
    }
    
    private Mensagem update(HttpServletRequest request, HttpServletResponse response) {
        Mensagem msg = new Mensagem();
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            
            String nomePet = request.getParameter("pet");
            Integer idServico = Integer.valueOf(request.getParameter("servico"));
            Integer idEspecie = Integer.valueOf(request.getParameter("especie"));
            String dataHoraStr = request.getParameter("data");
            
            LocalDateTime dataInicial = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime dataFinal = dataInicial.plusHours(1);
            
            Agendamento agendamento = new Agendamento();
            
            agendamento.setIdAgendamento(id);
            agendamento.setNomePet(nomePet);
            agendamento.setIdEspecie(idEspecie);
            agendamento.setIdServico(idServico);
            agendamento.setDataInicial(dataInicial);
            agendamento.setDataFinal(dataFinal);
            
            agendamentoDAO.update(agendamento);
            
            msg.setMensagem("Agendamento alterado com sucesso.");
            msg.setStatus("sucesso");
        } 
        catch (Exception e) {
            //msg.setMensagem(e.getMessage());
            //msg.setStatus("erro");
            System.out.println("ERRO UPDATE = "+e);
        }
        return msg;
    }
}
