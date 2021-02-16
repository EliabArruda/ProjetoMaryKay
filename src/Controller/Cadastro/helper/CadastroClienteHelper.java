/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Cadastro.helper;

import Controller.helper.iHelper;
import Model.Cliente;
import Model.DAO.ClienteDAO;
import Model.DAO.Conexao;
import View.Cadastro.CadastroCliente;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tayen
 */
public class CadastroClienteHelper implements iHelper {

    private final CadastroCliente view;

    public CadastroClienteHelper(CadastroCliente view) {

        this.view = view;

    }

    public String obterSexo() {

        return  (String) view.getSexoClienteComboBox().getSelectedItem();

    }

    @Override
    public Cliente obterModelo() {

        String idString = view.getIdClienteText().getText();
        int id = Integer.parseInt(idString);
        String nome = view.getNomeClienteText().getText();
        String sexo = obterSexo();
        String data = view.getDataNascClienteText().getText();
        String numero = view.getNumeroClienteText().getText();
        String email = view.getEmailClienteText().getText();
        String rg = view.getRgClienteText().getText();
        String endereco = view.getEnderecoClienteText().getText();
        String cep = view.getCepClienteText().getText();

        Cliente cliente = new Cliente(id, nome, sexo, data, numero, email, rg, endereco, cep);

        try {
            Connection conexao = new Conexao().getConnection();
            ClienteDAO clienteDAO = new ClienteDAO(conexao);
            clienteDAO.insert(cliente);

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CadastroClienteHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }

    @Override
    public void limparTela() {

        view.getIdClienteText().setText("");
        view.getNomeClienteText().setText("");
        view.getDataNascClienteText().setText("");
        view.getNumeroClienteText().setText("");
        view.getEmailClienteText().setText("");
        view.getRgClienteText().setText("");
        view.getEnderecoClienteText().setText("");
        view.getCepClienteText().setText("");

    }

}
