package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/patinhas";
    private static final String USER = "postgres";
    private static final String PASS = "root";
    
    static Connection conexao = null;
    
    public static Connection getConexao() {
        try{
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USER, PASS);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return conexao;
    }
}
