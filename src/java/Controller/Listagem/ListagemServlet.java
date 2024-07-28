package Controller.Listagem;

import DAO.CategoriaDAO;
import DAO.EspecieDAO;
import DAO.ProdutoDAO;
import DAO.ServicoDAO;
import Model.Categoria;
import Model.Especie;
import Model.Produto;
import Model.Servico;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListagemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        System.out.println("\nLISTAGEM SERVLET");
        
        String rota = request.getServletPath();
        String acao = request.getPathInfo();
        if (acao == null) { acao = "/";}
        
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        try{
            switch (acao) {
                case "/produto":
                    listProduto(request, response);
                    break;
                case "/servico":
                    listServico(request, response);
                    break;
                case "/favorito":
                    break;
                default:
            }
        
            System.out.println("\n");
        }
        catch(Exception e){
            System.out.println("ERRO EFETUAR LISTAGEM = "+e.getMessage());
            response.sendRedirect(request.getContextPath());
        }
    }
    
    private void listProduto(HttpServletRequest request, HttpServletResponse response){
        try {
            EspecieDAO especieDAO = new EspecieDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            
            Integer especieID = Integer.valueOf(request.getParameter("especie"));
            Especie especie = especieDAO.selectById(especieID);
            request.setAttribute("especie", especie);
            
            Integer categoriaID = Integer.valueOf(request.getParameter("categoria"));
            Categoria categoria = categoriaDAO.selectById(categoriaID);
            request.setAttribute("categoria", categoria);
            
            List<Produto> produtos = produtoDAO.selectByCategoriaEEspecie(categoriaID, especieID);
            
            request.setAttribute("produtos", produtos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listagem/listagem.jsp");
            dispatcher.forward(request, response);
        } 
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private void listServico(HttpServletRequest request, HttpServletResponse response){
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            ServicoDAO servicoDAO = new ServicoDAO();
            
            Categoria categoria = categoriaDAO.selectById(10);
            request.setAttribute("categoria", categoria);
            
            List<Servico> servicos = servicoDAO.select();
            
            request.setAttribute("servicos", servicos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listagem/listagem.jsp");
            dispatcher.forward(request, response);
        } 
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
