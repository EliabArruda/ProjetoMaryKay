package Controller.helper;

import Controller.Cadastro.helper.CadastroUsuarioHelper;
import Model.DAO.Conexao;
import Model.DAO.UsuarioDAO;
import View.Login;
import Model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tayen
 */
public class LoginHelper implements iHelper {
    
    private final Login view;
   

    
    public LoginHelper(Login view) {
        this.view = view;
       
    }
    
    
    @Override
    public Usuario obterModelo() {
        
        String nome = view.getUsuario_Text().getText();
        String senha = view.getSenha_text().getText();
        
        Usuario modelo = new Usuario(0, nome, senha);
         try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDAO = new  UsuarioDAO(conexao);
           

        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuarioHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return modelo;
        
    }
    
    public void setarModelo(Usuario modelo){
    
        String nome = modelo.getNome();
        String senha = modelo.getSenha();
        
        view.getUsuario_Text().setText(nome);
        view.getSenha_text().setText(senha);
    }
    
    @Override
    public void limparTela() {
        
        view.getUsuario_Text().setText("");
        view.getSenha_text().setText("");
    }

    
    
    
}
