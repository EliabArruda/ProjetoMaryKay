package Controller;

import Controller.Cadastro.CadastroUsuarioController;
import Controller.Cadastro.helper.CadastroUsuarioHelper;
import Model.Usuario;
import View.Agenda;
import View.Cadastro.CadastroCliente;
import View.Cadastro.CadastroServico;
import View.Cadastro.CadastroUsuario;
import View.Login;
import View.Menu;
import javax.swing.JOptionPane;

public class MenuController {

    private final Menu view;
    public MenuController(Menu view) {

        this.view = view;
        
    }

    public void entrarAgenda() {

        Agenda agenda = new Agenda();
        agenda.setVisible(true);

    }

    public void entrarCadastroCliente() {
        CadastroCliente cadastroCliente = new CadastroCliente();
        cadastroCliente.setVisible(true);
        view.dispose();
    }

    public void entrarCadastroUsuario() {
        CadastroUsuario cadastroUsuario = new CadastroUsuario();
        cadastroUsuario.setVisible(true);
        
        view.dispose();
        
    }
    
    public void entrarCadastroServico() {
        
        CadastroServico cadastroServico = new CadastroServico();
        cadastroServico.setVisible(true);
        view.dispose();
    }

    public void voltarParaTelaDeLogin() {
     Login login = new Login();
     login.setVisible(true);
     view.dispose();
    }
    
    

}
