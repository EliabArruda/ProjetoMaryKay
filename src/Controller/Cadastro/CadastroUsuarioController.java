/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Cadastro;

import Controller.Cadastro.helper.CadastroUsuarioHelper;
import Model.DAO.Conexao;
import Model.DAO.UsuarioDAO;
import Model.Usuario;
import View.Cadastro.CadastroUsuario;
import View.Menu;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tayen
 */
public class CadastroUsuarioController implements iVoltar{

    private final CadastroUsuario view;
    private final CadastroUsuarioHelper helper;

    public CadastroUsuarioController(CadastroUsuario view) {
        this.view = view;
        this.helper = new CadastroUsuarioHelper(view);
    }

    public void cadastrarUsuario() {
        Usuario usuario = helper.obterModelo();
        helper.limparTela();

    }

    public void atualizaNivelAcesso() {
        Connection conexao;
        try {
            conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            ArrayList<Usuario> usuarios = usuarioDAO.selectAll();
            helper.preencherNivelAcesso(usuarios);
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //buscar lista com agendamento no banco de dados

        //exibir a lista na view
    }
    @Override
    public void voltarParaMenu() {

        Menu menu = new Menu();
        menu.setVisible(true);
        view.dispose();
    }
}
