package Controller.Pedido;

import DAO.PedidoDAO;
import Model.ItemPedido;
import Model.Pedido;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PedidoServlet extends HttpServlet {
    private PedidoDAO pedidoDAO;
    
    @Override
    public void init() {
        pedidoDAO = new PedidoDAO();
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
        
        System.out.println("PEDIDO SERVLET");
        
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        switch (acao) {
            case "/delete":
                String delete = delete(request, response);
                session.setAttribute("msg", delete);
                response.sendRedirect("list");
                break;
            default:
                list(request, response);   
        }
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response){
        String pedido = request.getParameter("pedido");
        
        try {
            List<Pedido> pedidos = pedidoDAO.selectAll();
            List<ItemPedido> itemPedidos = pedidoDAO.selectAllItens();
            
            if (pedido != null && pedido != "") { 
                Integer idPedido = Integer.parseInt(pedido);
                pedidos = pedidoDAO.selectAllByPedido(idPedido);
            }
            
            request.setAttribute("pedidos", pedidos);
            request.setAttribute("itens", itemPedidos);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/pedido/pedido.jsp");
            dispatcher.forward(request, response);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            pedidoDAO.deleteItem(id);
            pedidoDAO.deletePedido(id);
            retorno = "Pedido excluido com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
    
}
