package DAO;

import DB.Conexao;
import Model.Agendamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {
    
    private static final String INSERIR_AGENDAMENTO = ""
            + "INSERT INTO "
            + "AGENDAMENTO(NOME_PET, ID_USUARIO, ID_ESPECIE, ID_SERVICO, DATA_INICIAL, DATA_FINAL) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    
    private static final String SELECT_AGENDAMENTO = ""
            + "SELECT "
            + "AGD.ID_AGENDAMENTO, "
            + "AGD.ID_USUARIO, "
            + "USU.NOME AS NOME_USUARIO, "
            + "AGD.NOME_PET, "
            + "AGD.ID_ESPECIE, "
            + "ESP.DESCRICAO AS NOME_ESPECIE, "
            + "AGD.ID_SERVICO, "
            + "PRO.NOME AS NOME_SERVICO, "
            + "AGD.DATA_INICIAL, "
            + "AGD.DATA_FINAL "
            + "FROM AGENDAMENTO AGD "
            + "INNER JOIN ESPECIE ESP "
            + "ON ESP.ID_ESPECIE = AGD.ID_ESPECIE "
            + "INNER JOIN PRODUTO PRO "
            + "ON PRO.ID_PRODUTO = AGD.ID_SERVICO "
            + "INNER JOIN USUARIO USU "
            + "ON USU.ID_USUARIO = AGD.ID_USUARIO";
    
    private static final String SELECT_AGENDAMENTO_BY_ID = "SELECT * FROM AGENDAMENTO WHERE ID_AGENDAMENTO = ?";
    
    private static final String UPDATE_AGENDAMENTO = ""
            + "UPDATE AGENDAMENTO "
            + "SET NOME_PET = ?, "
            //+ "ID_USUARIO = ?, "
            + "ID_SERVICO = ?, "
            + "ID_ESPECIE = ?, "
            + "DATA_INICIAL = ?, "
            + "DATA_FINAL = ? "
            + "WHERE ID_AGENDAMENTO = ?";
    
    private static final String DELETE_AGENDAMENTO = "DELETE FROM AGENDAMENTO WHERE ID_AGENDAMENTO = ?";
    
    
    public List<Agendamento> select() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Agendamento> agendamentos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_AGENDAMENTO);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Agendamento agendamento = new Agendamento();
                
                agendamento.setIdAgendamento(rs.getInt("ID_AGENDAMENTO"));
                agendamento.setIdUsuario(rs.getInt("ID_USUARIO"));
                agendamento.setNomeUsuario(rs.getString("NOME_USUARIO"));
                agendamento.setNomePet(rs.getString("NOME_PET"));
                agendamento.setIdEspecie(rs.getInt("ID_ESPECIE"));
                agendamento.setNomeEspecie(rs.getString("NOME_ESPECIE"));
                agendamento.setIdServico(rs.getInt("ID_SERVICO"));
                agendamento.setNomeServico(rs.getString("NOME_SERVICO"));
                agendamento.setDataInicial(rs.getTimestamp("DATA_INICIAL").toLocalDateTime());
                agendamento.setDataFinal(rs.getTimestamp("DATA_FINAL").toLocalDateTime());
                
                agendamentos.add(agendamento);
            }
            
            return agendamentos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public Agendamento selectById(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        Agendamento agendamento = new Agendamento();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_AGENDAMENTO_BY_ID);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){                
                agendamento.setIdAgendamento(rs.getInt("ID_AGENDAMENTO"));
                agendamento.setNomePet(rs.getString("NOME_PET"));
                agendamento.setDataInicial(rs.getTimestamp("DATA_INICIAL").toLocalDateTime());
                agendamento.setDataFinal(rs.getTimestamp("DATA_FINAL").toLocalDateTime());
                agendamento.setIdUsuario(rs.getInt("ID_USUARIO"));
                agendamento.setIdServico(rs.getInt("ID_SERVICO"));
                agendamento.setIdEspecie(rs.getInt("ID_ESPECIE"));
            }
            
            return agendamento;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void insert(Agendamento agendamento) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = Conexao.getConexao();
        
	try{
            Instant dtIniInstant = agendamento.getDataInicial().toInstant(ZoneOffset.UTC);
            Instant dtFinInstant = agendamento.getDataFinal().toInstant(ZoneOffset.UTC);
            
            Timestamp dtIni = Timestamp.from(dtIniInstant);
            Timestamp dtFin = Timestamp.from(dtFinInstant);
            
            ps = conexao.prepareStatement(INSERIR_AGENDAMENTO);
            
            ps.setString(1, agendamento.getNomePet());
            ps.setInt(2, agendamento.getIdUsuario());
            ps.setInt(3, agendamento.getIdEspecie());
            ps.setInt(4, agendamento.getIdServico());
            ps.setTimestamp(5, dtIni);
            ps.setTimestamp(6, dtFin);
            
            ps.executeUpdate();
	} 
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
	}
        finally{
            ps.close();
            conexao.close();
        }
    }
    
    public void delete(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(DELETE_AGENDAMENTO);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally{
            ps.close();
            conexao.close();
        }
    }
    
    public void update(Agendamento agendamento) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
           conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(UPDATE_AGENDAMENTO);
            
            ps.setString(1, agendamento.getNomePet());
            ps.setInt(2, agendamento.getIdServico());
            ps.setInt(3, agendamento.getIdEspecie());
            ps.setTimestamp(4, converteParaTimestamp(agendamento.getDataInicial()));
            ps.setTimestamp(5, converteParaTimestamp(agendamento.getDataFinal()));
            ps.setInt(6, agendamento.getIdAgendamento());
            
            ps.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally{
            conexao.close();
            ps.close();
        }
    }
    
    
    private Timestamp converteParaTimestamp(LocalDateTime dt){
        ZoneId zoneId = ZoneId.systemDefault(); // Usa o fuso horário do sistema
        Instant dtInstant = dt.atZone(zoneId).toInstant(); // Converte usando o fuso horário do sistema
        return Timestamp.from(dtInstant);
    }
}
