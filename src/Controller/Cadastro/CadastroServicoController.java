/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Cadastro;

import Controller.Cadastro.helper.CadastroServicoHelper;
import Model.Servico;
import View.Cadastro.CadastroServico;
import View.Menu;

/**
 *
 * @author tayen
 */
public class CadastroServicoController implements iVoltar{

    private final CadastroServico view;
    private final CadastroServicoHelper helper;
    public CadastroServicoController(CadastroServico view) {
        this.view = view;
        helper = new CadastroServicoHelper(view);
    }

    @Override
    public void voltarParaMenu() {

        Menu menu = new Menu();
        menu.setVisible(true);
        view.dispose();
    }

    public void cadastrarServico() {
      Servico servico = helper.obterModelo();
       helper.limparTela();
    }
    
    
}
