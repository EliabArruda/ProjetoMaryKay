/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Cadastro;

import Controller.Cadastro.helper.CadastroClienteHelper;
import Model.Cliente;
import Model.DAO.ClienteDAO;
import View.Cadastro.CadastroCliente;
import View.Menu;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tayen
 */
public class CadastroClienteController {

    private final CadastroCliente view;
    private final CadastroClienteHelper helper;

    public CadastroClienteController(CadastroCliente view) {

        this.helper = new CadastroClienteHelper(view);
        this.view = view;
    }

    public void cadastrarCliente() {
        //Buscar objeto agendamento na tela.
        Cliente cliente = helper.obterModelo();

        helper.limparTela();
    }
    public void voltarParaMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
        view.dispose();
        
    }

}
