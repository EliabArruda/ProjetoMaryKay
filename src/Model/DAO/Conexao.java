package Model.DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eliab
 */
public class Conexao {

    
    public Connection getConnection() throws SQLException{
        
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cursojava", "postgres","postgres");
        
        return conexao;
        
    }
    
    
}
