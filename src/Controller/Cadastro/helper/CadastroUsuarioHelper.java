/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Cadastro.helper;

import Controller.helper.iHelper;
import Model.DAO.Conexao;
import Model.DAO.UsuarioDAO;
import Model.Usuario;
import View.Cadastro.CadastroUsuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author tayen
 */
public class CadastroUsuarioHelper implements iHelper {

    private final CadastroUsuario view;

    public CadastroUsuarioHelper(CadastroUsuario view) {

        this.view = view;
    }

    private String obterSexo() {

        return (String) view.getSexoUsuarioComboBox().getSelectedItem();

    }

    /**
     *
     * @param usuarios
     */
    public void preencherNivelAcesso(ArrayList<Usuario> usuarios) {

        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getNivelAcessoUsuarioComboBox().getModel();

        for (Usuario usuario : usuarios) {

            comboBoxModel.addElement(usuario); //Pulo do gato
    }
        }
    public String obterNivelAcesso() {
        
        return (String) view.getNivelAcessoUsuarioComboBox().getSelectedItem();
    }

    @Override
    public Usuario obterModelo() {

        String idString = view.getIdUsuarioText().getText();
        int id = Integer.parseInt(idString);
        String nome = view.getNomeUsuarioText().getText();
        String sexo = obterSexo();
        String data = view.getDataUsuarioText().getText();
        String numero = view.getNumeroUsuarioText().getText();
        String email = view.getEmailUsuarioText().getText();
        String rg = view.getRgUsuarioText().getText();
        String senha = view.getSenhaUsuarioText().getText();
        String nivel = obterNivelAcesso();

        Usuario modelo = new Usuario(id, nome, sexo, data, numero, email, rg, senha, nivel);
       
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            
            usuarioDAO.insert(modelo);

            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuarioHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }

    @Override
    public void limparTela() {

        view.getIdUsuarioText().setText("");
        view.getNomeUsuarioText().setText("");
        view.getDataUsuarioText().setText("");
        view.getNumeroUsuarioText().setText("");
        view.getEmailUsuarioText().setText("");
        view.getRgUsuarioText().setText("");
        view.getSenhaUsuarioText().setText("");
    }

}
