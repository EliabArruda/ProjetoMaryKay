package Controller;

import Controller.helper.AgendaHelper;
import Model.Agendamento;
import Model.Cliente;
import Model.DAO.AgendamentoDAO;
import Model.DAO.ClienteDAO;
import Model.DAO.Conexao;
import Model.DAO.ServicoDAO;
import Model.Servico;
import View.Agenda;
import View.Menu;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eliab
 */
public class AgendaController {

    private final Agenda view;
    private final AgendaHelper helper;

    public AgendaController(Agenda view) {
        this.view = view;
        this.helper = new AgendaHelper(view);
    }

    public void atualizaTabela() {
       
            AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
            ArrayList<Agendamento> agendamentos = agendamentoDAO.selectAll();
            helper.preencherTabela(agendamentos);
        //buscar lista com agendamento no banco de dados
        //exibir a lista na view

    }

    public void atualizaCliente() {

        //buscar lista com agendamento no banco de dados
        Connection connection;
        try {
            connection = new Conexao().getConnection();
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            ArrayList<Cliente> clientes = clienteDAO.selectAll();
            helper.preencherClienteComboBox(clientes);

        } catch (SQLException ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);

            //exibir a lista de clientes no JComboBox
        }
    }

    public void atualizaServico() {
        try {
        Connection connection = new Conexao().getConnection();
        ServicoDAO servicoDAO = new ServicoDAO(connection);
        ArrayList<Servico> servicos = servicoDAO.selectAll();

        helper.preencherServicoComboBox(servicos);
        } catch (SQLException ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizaValor() {

        Servico servico = helper.obterServico();
        helper.setarValor(servico.getValor());

    }

    public void agendar() {
        //Buscar objeto agendamento na tela.
        Agendamento agendamento = helper.obterModelo();
        
        //Salva objeto no Banco
        new AgendamentoDAO().insert(agendamento);
        
        //Insere Elemento na Tabela
        atualizaTabela();
        
        helper.limparTela();
    }

    public void voltarParaMenu() {

        Menu menu = new Menu();
        menu.setVisible(true);
        this.view.dispose();

    }

}
