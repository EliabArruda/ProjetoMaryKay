/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Cliente;
import View.Cadastro.CadastroCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class ClienteDAO {

    private final Connection connection;

    public ClienteDAO(Connection connection) {
        
        this.connection = connection;
    }

   
    

    /**
     * Insere um cliente dentro do banco de dados
     *
     * @param cliente exige que seja passado um objeto do tipo cliente
     * @throws java.sql.SQLException
     */
    public void insert(Cliente cliente) throws SQLException {

        String sql = "insert into cliente(id,nome,sexo,data,numero, email,rg,endereco,cep)"
                + " values ('" + cliente.getId() + "',"
                + "'" + cliente.getNome() + "',"
                + "'" + cliente.getSexo() + "',"
                + "'" + cliente.getDataNascimento()+ "',"
                + "'" + cliente.getNumero() + "',"
                + "'" + cliente.getEmail() + "',"
                + "'" + cliente.getRg() + "',"
                + "'" + cliente.getEndereco()+ "',"
                + "'" + cliente.getCep() + "');";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    /**
     * Atualiza um Objeto no banco de dados
     *
     * @param cliente
     * @return
     */
    public boolean update(Cliente cliente) {

        for (int i = 0; i < Banco.cliente.size(); i++) {
            if (idSaoIguais(Banco.cliente.get(i), cliente)) {
                Banco.cliente.set(i, cliente);
                return true;
            }
        }
        return false;

    }

    /**
     * Deleta um objeto do banco de dados pelo id do cliente passado
     *
     * @param cliente
     * @return
     */
    public boolean delete(Cliente cliente) {
        for (Cliente clienteLista : Banco.cliente) {
            if (idSaoIguais(clienteLista, cliente)) {
                Banco.cliente.remove(clienteLista);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna um arraylist com todos os clientes do banco de dados
     *
     * @return uma lista com todos os registros do banco
     */
    public ArrayList<Cliente> selectAll() throws SQLException {
       
        String sql = "select * from cliente";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()) {
            
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String sexo = resultSet.getString("sexo");
            String data = resultSet.getString("data");
            String numero = resultSet.getString("numero");
            String email = resultSet.getString("email");
            String rg = resultSet.getString("rg");
            String endereco = resultSet.getString("endereco");
            String cep = resultSet.getString("cep");
            
            Cliente clienteComDadosDoBanco = new Cliente(id, nome, sexo, data, numero, email, rg, endereco, cep);
            clientes.add(clienteComDadosDoBanco);
            
        }
        
        return clientes;
    }

    /**
     * Compara se dois objetos tem a propriedade id igual
     *
     * @param cliente
     * @param clienteAComparar
     * @return verdadeiro caso os id forem iguais e falso se nao forem
     */
    private boolean idSaoIguais(Cliente cliente, Cliente clienteAComparar) {
        return cliente.getId() == clienteAComparar.getId();
    }

}
