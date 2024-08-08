package Controller.Relatorio;

import DB.Conexao;
import Model.Usuario;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class RelatorioServlet extends HttpServlet {

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
        
        System.out.println("RELATORIO SERVLET");
        
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        switch (acao) {
            case "/teste":
                form(request, response, acao);
                break;
            case "/vendas-por-usuario":
                vendasPorUsuario(request, response, acao);
                break;
            case "/faturamento-diario":
                faturamentoDiario(request, response, acao);
                break;
            default:
                dispatcher = request.getRequestDispatcher("/pages/relatorio/relatorio.jsp");
                dispatcher.forward(request, response);           
        }
    }

    private void form(HttpServletRequest request, HttpServletResponse response, String acao) {
        Connection conexao = null;
        try {
            conexao = Conexao.getConexao();
            
            String nomeUsuario = "";
            HttpSession session = request.getSession(false);
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            
            if (usuarioLogado != null) { nomeUsuario = usuarioLogado.getNome(); }
            
            // Caminho do arquivo JRXML
            String jrxmlPath = getServletContext().getRealPath("/WEB-INF/produto-sem-estoque.jrxml");
            System.out.println(jrxmlPath);
            
            File jrxmlFile = new File(jrxmlPath);
            if (!jrxmlFile.exists()) {
                throw new RuntimeException("Arquivo JRXML não encontrado: " + jrxmlPath);
            }

            
            // Compilar o arquivo JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);
            
            // Parâmetros do relatório
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("USUARIO_LOGADO", nomeUsuario);
                        
            // Preencher o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);
            
            // Exportar o relatório para PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=relatorio.pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
             if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void vendasPorUsuario(HttpServletRequest request, HttpServletResponse response, String acao) {
        Connection conexao = null;
        try {
            System.out.println("VENDAS POR USUARIO");
            conexao = Conexao.getConexao();
            
            String dtIni = request.getParameter("dtini");
            String dtFin = request.getParameter("dtfin");
            
            System.out.println(dtIni);
            System.out.println(dtFin);
            
            String nomeUsuario = "";
            HttpSession session = request.getSession(false);
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            
            if (usuarioLogado != null) { nomeUsuario = usuarioLogado.getNome(); }
            
            // Caminho do arquivo JRXML
            String jrxmlPath = getServletContext().getRealPath("/WEB-INF/vendas-por-usuario.jrxml");
            System.out.println(jrxmlPath);
            
            File jrxmlFile = new File(jrxmlPath);
            if (!jrxmlFile.exists()) {
                throw new RuntimeException("Arquivo JRXML não encontrado: " + jrxmlPath);
            }
            
            // Compilar o arquivo JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);
            
            // Parâmetros do relatório
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("USUARIO_LOGADO", nomeUsuario);
            parametros.put("DT_INI", dtIni);
            parametros.put("DT_FIN", dtFin);
            
            // Preencher o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);
            
            // Exportar o relatório para PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=relatorio.pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
             if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void faturamentoDiario(HttpServletRequest request, HttpServletResponse response, String acao) {
        Connection conexao = null;
        try {
            System.out.println("VENDAS POR USUARIO");
            conexao = Conexao.getConexao();
            
            String dtIni = request.getParameter("dtini");
            String dtFin = request.getParameter("dtfin");
            
            System.out.println(dtIni);
            System.out.println(dtFin);
            
            String nomeUsuario = "";
            HttpSession session = request.getSession(false);
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            
            if (usuarioLogado != null) { nomeUsuario = usuarioLogado.getNome(); }
            
            // Caminho do arquivo JRXML
            String jrxmlPath = getServletContext().getRealPath("/WEB-INF/faturamento-diario.jrxml");
            System.out.println(jrxmlPath);
            
            File jrxmlFile = new File(jrxmlPath);
            if (!jrxmlFile.exists()) {
                throw new RuntimeException("Arquivo JRXML não encontrado: " + jrxmlPath);
            }
            
            // Compilar o arquivo JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);
            
            // Parâmetros do relatório
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("USUARIO_LOGADO", nomeUsuario);
            parametros.put("DT_INI", dtIni);
            parametros.put("DT_FIN", dtFin);
            
            // Preencher o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);
            
            // Exportar o relatório para PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=relatorio.pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
             if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
