package Controller.Cadastro.helper;

import Controller.helper.iHelper;
import Model.DAO.Conexao;
import Model.DAO.ServicoDAO;
import Model.Servico;
import View.Cadastro.CadastroServico;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eliab
 */
public class CadastroServicoHelper implements iHelper {

    private final CadastroServico view;
    public CadastroServicoHelper(CadastroServico view) {
    
        this.view = view;
    }

    @Override
    public Servico obterModelo() {

        String idString = view.getIdServicoText().getText();
        int id = Integer.parseInt(idString);
        String descricao = view.getDescricaoServicoText().getText();
        String valorString = view.getValorServicoText().getText();
        float valor = Float.parseFloat(valorString);
        
        Servico modelo = new Servico(id, descricao, valor);
        
        try {
            
        Connection conexao = new Conexao().getConnection();
        ServicoDAO servicoDAO = new ServicoDAO(conexao);
        servicoDAO.insert(modelo);
        
            JOptionPane.showMessageDialog(view, "Serviço cadastrado com sucesso!");
        
        } catch (SQLException ex) {
            Logger.getLogger(CadastroServicoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return modelo;
    }

    @Override
    public void limparTela() {

        view.getIdServicoText().setText("");
        view.getDescricaoServicoText().setText("");
        view.getValorServicoText().setText("");
    }
    
    
}
