package Controller;

import Controller.helper.LoginHelper;
import View.Login;
import View.Menu;
import Model.Cliente;
import Model.DAO.Conexao;
import Model.DAO.UsuarioDAO;
import Model.Usuario;
import View.Cadastro.CadastroUsuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private final Login view;
    private final LoginHelper helper;
    
    public LoginController(Login view) {
        
        this.view = view;
        this.helper = new LoginHelper(view);
       
    }
    
    public void entrarSistema() throws SQLException {
        
        //Pegar Usuario da view
       Usuario usuario = helper.obterModelo();
        CadastroUsuario cadastro = new CadastroUsuario();
       
       //Pesquisa um usuário do Banco
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        
        boolean existe = usuarioDAO.selectPorNomeESenha(usuario);
        boolean admin = usuarioDAO.selectAdministrador(usuario);
        boolean func = usuarioDAO.selectFuncionario(usuario);
          /*Se usuario da view tiver o mesmo usuário e senha
        que o usuario vindo do banco, direcionar para o menu*/
        if(existe && admin) {
            //navegar para o menu
            Menu menu = new Menu();
            menu.setVisible(true);
            menu.getjMenuItemCliente().setEnabled(true);
            menu.getjMenuItemServico().setEnabled(true);
            menu.getjMenuItemUsuario().setEnabled(true);
      
            System.out.println(admin);
          
            this.view.dispose();
        } else if(existe && func){
            
            Menu menu = new Menu();
            menu.setVisible(true);
            menu.getjMenuItemCliente().setEnabled(true);
            menu.getjMenuItemServico().setEnabled(true);
            
            System.out.println(func);
            
        } 
      
        //senão, mostrar uma mensagem ao usuário "usuário ou senha inválidos
        
    }
   
}
