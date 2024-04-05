package DAO;

// IMPORTAÇÕES
import java.sql.Connection; //classe Connection do pacote java.sql para manipulação de conexões com o banco de dados.
import java.sql.DriverManager; // classe DriverManager do pacote java.sql para gerenciar os drivers de banco de dados.
import java.sql.SQLException; // Importa a classe SQLException do pacote java.sql para lidar com exceções relacionadas ao SQL.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // VARIAVEIS PARA AUTENTICACAO COM O BANCO DE DADOS
    private String driver = "org.postgresql.Driver"; // VALOR PADRAO DEFINIDO PELO BANCO USADO
    private String url = "jdbc:postgresql://localhost:5432/patinhas"; // URL DO BANCO + O NOME DO BANCO 
    private String usuario = "postgres";
    private String senha = "root";

    private static Conexao instancia = null;

    Connection con = null;
    
    // CONSTRUTOR DA CLASSE CONEXAO
    public Conexao() {
        try{
            Class.forName(driver); // CARREGANDO O DRIVER JDBC ESPECIFICO DO PostgreSQL
            con = DriverManager.getConnection(url, usuario, senha); // ESTABELECENDO UMA CONEXA
            System.out.println("Conexão estabelecida com sucesso."); // RESPOSTA DE SUCESSO
            con.close();
        }
        catch(ClassNotFoundException | SQLException  e){ // POSSIVEIS TIPOS DE ERROS
            System.err.println("Erro ao se reconectar ao banco."); // MENSAGEM DE ERRO
            e.printStackTrace();
        }
    }
    
    // PADRAO 'Singleton' QUE PERMITE APENAS UMA INSTANCIA DA CLASSE
    public static Conexao getInstancia(){
        // VERIRIFICA SE EXISTE UMA INSTANCIA
        if (instancia == null){
            instancia = new Conexao(); // CASO NAO TENHA ELE CRIA UMA NOVA
        }
        return instancia; // RETORNA A INSTANCIA
    }
    
    // METODO GET 
    public Connection getConexao(){
        return this.con;
    }
}

