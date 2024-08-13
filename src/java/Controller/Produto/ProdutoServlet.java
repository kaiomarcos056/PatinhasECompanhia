package Controller.Produto;

import DAO.CategoriaDAO;
import DAO.EspecieDAO;
import DAO.MarcaDAO;
import DAO.ProdutoDAO;
import Model.Categoria;
import Model.Especie;
import Model.Marca;
import Model.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@MultipartConfig
public class ProdutoServlet extends HttpServlet {
    private ProdutoDAO produtoDAO;
    private MarcaDAO marcaDAO;
    private CategoriaDAO categoriaDAO;
    private EspecieDAO especieDAO;
    
    @Override
    public void init() {
        produtoDAO = new ProdutoDAO();
        marcaDAO = new MarcaDAO();
        categoriaDAO = new CategoriaDAO();
        especieDAO = new EspecieDAO();
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
        
        System.out.println("PRODUTO SERVLET");
        
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
                List<Produto> produtos = list(request, response);
                request.setAttribute("produtos", produtos);
                dispatcher = request.getRequestDispatcher("/pages/produto/produto.jsp");
                dispatcher.forward(request, response);           
        }
    }
    
    private void form(HttpServletRequest request, HttpServletResponse response, String acao){
        try{
            if("/edit".equals(acao)){
                Integer id = Integer.valueOf(request.getParameter("id"));
                Produto produto = produtoDAO.selectById(id);
                request.setAttribute("produto", produto);
            }
            List<Marca> marcas = marcaDAO.select();
            request.setAttribute("marcas", marcas);
            
            List<Especie> especies = especieDAO.select();
            request.setAttribute("especies", especies);
            
            List<Categoria> categorias = categoriaDAO.select();
            request.setAttribute("categorias", categorias);
            
            request.setAttribute("rota", acao);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/produto/form-produto.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private List<Produto> list(HttpServletRequest request, HttpServletResponse response){
        String produto = request.getParameter("produto");
        List<Produto> produtos = null; 
        try {
            produtos = produtoDAO.selectAll();
            
            if (produto != null) {
                produtos = produtoDAO.selectByAllProduto(produto);
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return produtos;
    }

    private String insert(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {
            String dataAtual = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
            
            String caminhoUpload =  getServletContext().getRealPath("") + "assets" + File.separator + "produtos";
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
            
            String nome = request.getParameter("nome");
            Double valor = Double.valueOf(request.getParameter("valor"));
            Integer quantidade = Integer.valueOf(request.getParameter("quantidade"));
            Integer categoria = Integer.valueOf(request.getParameter("categoria"));
            Integer marca = Integer.valueOf(request.getParameter("marca"));
            Integer especie = Integer.valueOf(request.getParameter("especie"));
            
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setFoto(fileName);
            produto.setValor(valor);
            produto.setQuantidade(quantidade);
            produto.setIdCategoria(categoria);
            produto.setIdMarca(marca);
            produto.setIdEspecie(especie);
            produto.setServico(false);
            
            produtoDAO.insert(produto);
            if (fileName != null && !fileName.equals("default.png")) {
                filePart.write(filePath);
            }
            
            retorno = "Produto cadastrado com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
    
    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            produtoDAO.delete(id);
            retorno = "Produto excluido com sucesso.";
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
            
            String caminhoUpload =  getServletContext().getRealPath("") + "assets" + File.separator + "produtos";
            caminhoUpload = caminhoUpload.replace("build"+File.separator, "");
            
            Part filePart = request.getPart("foto");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            
            boolean mudaFoto = false;
            if (fileName == null || fileName.equals("")) {
                fileName = "default.png";
            }
            else{
                fileName = dataAtual+"_"+fileName;
                mudaFoto = true;
            }
            String filePath = caminhoUpload + File.separator + fileName;
            
            Integer id = Integer.valueOf(request.getParameter("id"));
            
            Produto oldProd = produtoDAO.selectById(id);
            
            String nome = request.getParameter("nome");
            Double valor = Double.valueOf(request.getParameter("valor"));
            Integer quantidade = Integer.valueOf(request.getParameter("quantidade"));
            Integer categoria = Integer.valueOf(request.getParameter("categoria"));
            Integer marca = Integer.valueOf(request.getParameter("marca"));
            Integer especie = Integer.valueOf(request.getParameter("especie"));
            
            Produto produto = new Produto();
            produto.setId(id);
            produto.setNome(nome);
            if(!mudaFoto) { 
                produto.setFoto(oldProd.getFoto()); 
            }else{
                produto.setFoto(fileName); 
            }
            produto.setValor(valor);
            produto.setQuantidade(quantidade);
            produto.setIdCategoria(categoria);
            produto.setIdMarca(marca);
            produto.setIdEspecie(especie);
            produto.setServico(false);
            
            produtoDAO.update(produto);
            if (fileName != null && !fileName.equals("default.png")) {
                filePart.write(filePath);
            }
            retorno = "Produto alterado com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
    
}
