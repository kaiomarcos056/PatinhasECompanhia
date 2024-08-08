package Controller.Usuario;

import DAO.PedidoDAO;
import Model.ItemPedido;
import Model.Pedido;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PedidosUsuarioServlet extends HttpServlet {
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
            default:
                list(request, response);
        }
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response){
        String pedido = request.getParameter("pedido");

        try {
            HttpSession session = request.getSession(false);
            
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            Integer idUsuario = usuarioLogado.getId();
            
            List<Pedido> pedidos = pedidoDAO.selectByUsuario(idUsuario);
            List<ItemPedido> itemPedidos = pedidoDAO.selectItensByUsuario(idUsuario);
            
            if (pedido != null && pedido != "") { 
                Integer idPedido = Integer.parseInt(pedido);
                pedidos = pedidoDAO.selectByPedidoUsuario(idUsuario, idPedido);
            }
            
            request.setAttribute("pedidos", pedidos);
            request.setAttribute("itens", itemPedidos);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/usuario/meus-pedidos.jsp");
            dispatcher.forward(request, response);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
