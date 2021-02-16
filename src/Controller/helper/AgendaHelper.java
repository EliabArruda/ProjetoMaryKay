package Controller.helper;

import Model.Agendamento;
import Model.Cliente;
import Model.DAO.AgendamentoDAO;
import Model.DAO.Conexao;
import Model.Servico;
import View.Agenda;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eliab
 */
public class AgendaHelper implements iHelper {

    private final Agenda view;

    public AgendaHelper(Agenda view) {

        this.view = view;

    }

    public void preencherTabela(ArrayList<Agendamento> agendamentos) {

        DefaultTableModel tableModel = (DefaultTableModel) view.getTabela().getModel();
        tableModel.setNumRows(0);

        //Percorrer a lista Preenchendo o tableModel
        for (Agendamento agendamento : agendamentos) {

            tableModel.addRow(new Object[]{
                agendamento.getId(),
                agendamento.getCliente().getNome(),
                agendamento.getServico().getDescricao(),
                agendamento.getValor(),
                agendamento.getDataFormatada(),
                agendamento.getHoraFormatada(),
                agendamento.getObservacao()

            });
        }

    }

    public void preencherClienteComboBox(ArrayList<Cliente> clientes) {

        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getClienteComboBox().getModel();

        for (Cliente cliente : clientes) {

            comboBoxModel.addElement(cliente); //Pulo do gato

        }

    }

    public void preencherServicoComboBox(ArrayList<Servico> servicos) {

        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getServicoComboBox().getModel();

        for (Servico servico : servicos) {

            comboBoxModel.addElement(servico);

        }

    }

    public Cliente obterCliente() {

        return (Cliente) view.getClienteComboBox().getSelectedItem();

    }

    public Servico obterServico() {

        return (Servico) view.getServicoComboBox().getSelectedItem();

    }

    @Override
    public Agendamento obterModelo() {

        String idString = view.getIdCampo().getText();
        int id = Integer.parseInt(idString);
        Cliente cliente = obterCliente();
        Servico servico = obterServico();
        String valorString = view.getValorCampo().getText();
        float valor = Float.parseFloat(valorString);
        String data = view.getDataCampo().getText();
        String hora = view.getHoraCampo().getText();
        String dataHora = data + " " + hora;
        String observacao = view.getObservacoesCampo().getText();

        Agendamento agendamento = new Agendamento(id, cliente, servico,
                valor, dataHora, observacao);
       
        
      

        return agendamento;
    }

    @Override
    public void limparTela() {

        view.getIdCampo().setText("");
        view.getDataCampo().setText("");
        view.getHoraCampo().setText("");
        view.getObservacoesCampo().setText("");

    }

    public void setarValor(float valor) {

        view.getValorCampo().setText(valor + "");

    }

}
