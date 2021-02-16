/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class ServicoDAO {

    private final Connection connection;

    public ServicoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    
    
    /**
     * Insere um servico dentro do banco de dados
     * @param servico exige que seja passado um objeto do tipo servico
     */
    public void insert(Servico servico) throws SQLException{

        if(servico.getId() == 0){
            servico.setId(proximoId());
        String sql = "insert into servico(id,descricao,valor) values "
                + "('"+ servico.getId() + "',"
                + "'" + servico.getDescricao() + "'," 
                + "'" + servico.getValor() + "');";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        

    }
        }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param servico
     * @return 
     */
    public boolean update(Servico servico){
        
        for (int i = 0; i < Banco.servico.size(); i++) {
            if(idSaoIguais(Banco.servico.get(i),servico)){
                Banco.servico.set(i, servico);
                return true;
            }
        }
        return false;      

    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do servico passado
     * @param servico
     * @return 
     */
    public boolean delete(Servico servico){
        for (Servico servicoLista : Banco.servico) {
            if(idSaoIguais(servicoLista,servico)){
                Banco.servico.remove(servicoLista);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retorna um arraylist com todos os servicos do banco de dados
     * @return uma lista com todos os registros do banco
     */
    public ArrayList<Servico> selectAll() throws SQLException{
        
        String sql = "select * from servico";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        //Criar uma Agenda no Banco de dados
        //COLOCAR O ID AUTOMÁTICO
        
        ArrayList<Servico> servicos = new ArrayList<Servico>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
            
            int id = resultSet.getInt("id");
            String descricao = resultSet.getString("descricao");
            float valor = resultSet.getFloat("valor");
            
            Servico servicoComDadosDoBanco = new Servico(id, descricao, valor);
            servicos.add(servicoComDadosDoBanco);
            
        }
        return servicos;
    }
    
    /**
     * Compara se dois objetos tem a propriedade id igual
     * @param servico
     * @param servicoAComparar
     * @return verdadeiro caso os id forem iguais e falso se nao forem
     */
    private boolean idSaoIguais(Servico servico, Servico servicoAComparar) {
        return servico.getId() ==  servicoAComparar.getId();
    }
    private int proximoId(){
        
        int maiorId = 0;
        
        for (Servico servico : Banco.servico) {           
           int id = servico.getId();
            
            if(maiorId < id){
                maiorId = id;
            }
            
        }
        
        return maiorId + 1;
    }
    
    
}
