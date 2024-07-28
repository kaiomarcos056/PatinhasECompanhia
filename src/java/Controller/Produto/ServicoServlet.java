package Controller.Produto;

import DAO.EspecieDAO;
import DAO.ServicoDAO;
import Model.Especie;
import Model.Produto;
import Model.Servico;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class ServicoServlet extends HttpServlet {
    private EspecieDAO especieDAO;
    private ServicoDAO servicoDAO;
    
    @Override
    public void init() {
        especieDAO = new EspecieDAO();
        servicoDAO = new ServicoDAO();
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
        if (acao == null) { acao = "/";}
        
        System.out.println("SERVIÇO SERVLET");
        
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        switch (acao) {
            
            case "/new":
            case "/edit":
                form(request, response, acao);
                break;
            case "/insert":
                String insert = insert(request, response);
                session.setAttribute("msg", insert);
                response.sendRedirect("list");
                break;
            case "/delete":
                String delete = delete(request, response);
                session.setAttribute("msg", delete);
                response.sendRedirect("list");
                break;
            case "/update":
                String update = update(request, response);
                session.setAttribute("msg", update);
                response.sendRedirect("list");
                break;
            default:
                List<Servico> servicos = list(request, response);
                request.setAttribute("servicos", servicos);
                dispatcher = request.getRequestDispatcher("/pages/servico/servico.jsp");
                dispatcher.forward(request, response);           
        }
    }
    
    private void form(HttpServletRequest request, HttpServletResponse response, String acao){
        try{
            if("/edit".equals(acao)){
                Integer id = Integer.valueOf(request.getParameter("id"));
                Servico servico = servicoDAO.selectById(id);
                request.setAttribute("servico", servico);
            }
           
            List<Especie> especies = especieDAO.select();
            request.setAttribute("especies", especies);
            
            request.setAttribute("rota", acao);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/servico/form-servico.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private List<Servico> list(HttpServletRequest request, HttpServletResponse response){
        String servico = request.getParameter("servico");
        List<Servico> servicos = null; 
        
        try {
            servicos = servicoDAO.select();
            
            if (servico != null) {
                servicos = servicoDAO.selectByServico(servico);
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return servicos;
    }
    
    private String insert(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {
            
            String dataAtual = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
            
            String caminhoUpload =  getServletContext().getRealPath("") + "assets" + File.separator + "servicos";
            caminhoUpload = caminhoUpload.replace("build"+File.separator, "");
            
            Part filePart = request.getPart("foto");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            if (fileName == null || fileName.equals("")) {
                fileName = "default.png";
            }
            else{
                fileName = dataAtual+"_"+fileName;
            }
            String filePath = caminhoUpload + File.separator + fileName;
            
            if (fileName != null && !fileName.equals("default.png")) {
                filePart.write(filePath);
            }
            
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String tamanho = request.getParameter("tamanho");
            Double valor = Double.valueOf(request.getParameter("valor"));
            Integer especie = Integer.valueOf(request.getParameter("especie"));
            
            Servico servico = new Servico();
            servico.setNome(nome);
            servico.setDescricao(descricao);
            servico.setTamanho(tamanho);
            servico.setFoto(fileName);
            servico.setValor(valor);
            servico.setIdEspecie(especie);
            servico.setServico(true);
            
            servicoDAO.insert(servico);
            
            retorno = "Serviço cadastrado com sucesso.";
        } 
        catch (Exception e) {
            System.out.println("ERRO AO INSERIR SERVIÇO = "+e.getMessage());
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
    
    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            servicoDAO.delete(id);
            retorno = "Serviço excluido com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
    
    private String update(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {
            String dataAtual = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
            
            String caminhoUpload =  getServletContext().getRealPath("") + "assets" + File.separator + "servicos";
            caminhoUpload = caminhoUpload.replace("build"+File.separator, "");
            
            Part filePart = request.getPart("foto");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            if (fileName == null || fileName.equals("")) {
                fileName = "default.png";
            }
            else{
                fileName = dataAtual+"_"+fileName;
            }
            String filePath = caminhoUpload + File.separator + fileName;
            
            Integer id = Integer.valueOf(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String tamanho = request.getParameter("tamanho");
            Double valor = Double.valueOf(request.getParameter("valor"));
            Integer especie = Integer.valueOf(request.getParameter("especie"));
            
            Servico servico = new Servico();
            servico.setId(id);
            servico.setNome(nome);
            servico.setDescricao(descricao);
            servico.setTamanho(tamanho);
            servico.setFoto(fileName);
            servico.setValor(valor);
            servico.setIdEspecie(especie);
            servico.setServico(true);
            
            servicoDAO.update(servico);
            
            if (fileName != null && !fileName.equals("default.png")) {
                filePart.write(filePath);
            }
            retorno = "Serviço alterado com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
}
