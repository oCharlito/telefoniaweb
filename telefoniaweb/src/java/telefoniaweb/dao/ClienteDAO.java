package telefoniaweb.dao;

import telefoniaweb.model.Cliente;
import telefoniaweb.utils.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlito
 */
public class ClienteDAO {    
    private Connection conexao;
    
    public List<Cliente> listarClientes(){
    List<Cliente> clientes = new ArrayList<>();
    conexao = DBConnection.getConnection("");
    
    String sql = "SELECT * FROM clientes";
    PreparedStatement stmt;
    
        try {
            stmt = this.conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("sexo").charAt(0), rs.getString("telefone"), rs.getString("email"));
            cliente.setId(rs.getInt("id"));
            
            clientes.add(cliente);
            }
            
            rs.close();
            stmt.close();
            conexao.close();
        
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        return clientes;
    }
   
 public int inserirCliente(Cliente cliente) throws SQLIntegrityConstraintViolationException, SQLException {
    int status = 0;
    String sql = "INSERT INTO clientes (nome, sexo, telefone, email) VALUES (?,?,?,?)";
    conexao = DBConnection.getConnection("");
    PreparedStatement stmt = null;
    
    
     try {
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, String.valueOf(cliente.getSexo()));
        stmt.setString(3, cliente.getTelefone());
        stmt.setString(4, cliente.getEmail());
    
        status = stmt.executeUpdate();        
        stmt.close();
        conexao.close();  
     } catch (SQLException ex) {
        Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
      return status ;
 }
    
    
   public int editarCliente(Cliente cliente){
    int status = 0;
    String sql = "UPDATE clientes SET nome = ?, sexo = ?, telefone = ?, email = ? WHERE id = ?";
    conexao = DBConnection.getConnection("");
    PreparedStatement stmt = null; 
    try{     
    stmt = conexao.prepareStatement(sql);
    stmt.setString(1, cliente.getNome());
    stmt.setString(2, String.valueOf(cliente.getSexo()));
    stmt.setString(3, cliente.getTelefone());
    stmt.setString(4, cliente.getEmail());
    stmt.setInt(5, cliente.getId());
       
    status = stmt.executeUpdate();
    
    stmt.close();
    conexao.close();    
    } catch (SQLException ex){
        ex.printStackTrace();
    }
    return status;
   }
      
   public int excluirCliente(int id){
    int status = 0;   
    String sql = "DELETE FROM clientes WHERE id = ?";
    conexao = DBConnection.getConnection("");
    PreparedStatement stmt = null; 
    try{     
    stmt = conexao.prepareStatement(sql);
    stmt.setInt(1, id);
       
    status = stmt.executeUpdate();
    
    stmt.close();
    conexao.close();    
    } catch (SQLException ex){
        ex.printStackTrace();
    }
    return status;
   }

   public Cliente buscarClientes(int id){
    Cliente cliente = null;
    conexao = DBConnection.getConnection("");
    
    String sql = "SELECT * FROM clientes WHERE id = ?";
    PreparedStatement stmt;
    
        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, id);            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                cliente = new Cliente(rs.getString("nome"), rs.getString("sexo").charAt(0), rs.getString("telefone"), rs.getString("email"));
                cliente.setId(rs.getInt("id"));            
            }
            
            rs.close();
            stmt.close();
            conexao.close();
        
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        return cliente;
    }   
}