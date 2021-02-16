/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class UsuarioDAO {

    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Insere um usuario dentro do banco de dados
     *
     * @param usuario exige que seja passado um objeto do tipo usuario
     * @throws java.sql.SQLException
     */
    public void insert(Usuario usuario) throws SQLException {

        String sql = "insert into usuario(id, usuario, sexo, data, numero, email, rg, senha, nivel)"
                + " values('" + usuario.getId() + "',"
                + "'" + usuario.getNome() + "',"
                + "'" + usuario.getSexo() + "',"
                + "'" + usuario.getDataNascimento() + "',"
                + "'" + usuario.getNumero() + "',"
                + "'" + usuario.getEmail() + "',"
                + "'" + usuario.getRg() + "',"
                + "'" + usuario.getSenha() + "',"
                + "'" + usuario.getNivelAcesso() + "');";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        
    }

    /**
     * Atualiza um Objeto no banco de dados
     *
     * @param usuario
     * @return
     */
    public boolean update(Usuario usuario) {

        for (int i = 0; i < Banco.usuario.size(); i++) {
            if (idSaoIguais(Banco.usuario.get(i), usuario)) {
                Banco.usuario.set(i, usuario);
                return true;
            }
        }
        return false;

    }

    /**
     * Deleta um objeto do banco de dados pelo id do usuario passado
     *
     * @param usuario
     * @return
     */
    public boolean delete(Usuario usuario) {
        for (Usuario usuarioLista : Banco.usuario) {
            if (idSaoIguais(usuarioLista, usuario)) {
                Banco.usuario.remove(usuarioLista);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna um arraylist com todos os usuarios do banco de dados
     *
     * @return uma lista com todos os registros do banco
     */
    public ArrayList<Usuario> selectAll() throws SQLException {
       
        String sql = "select * from usuario";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
            
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("usuario");
            String sexo = resultSet.getString("sexo");
            String data = resultSet.getString("data");
            String numero = resultSet.getString("numero");
            String email = resultSet.getString("email");
            String rg = resultSet.getString("rg");
            String senha = resultSet.getString("senha");
            String nivel = resultSet.getString("nivel");
            
           
            Usuario usuarioComDadosDoBanco = new Usuario(id, nome, sexo, data, numero,email,rg,senha,nivel);
            usuarios.add(usuarioComDadosDoBanco);
        }
        
        return usuarios;
    }

    /**
     * Retorna um Objeto do tipo usuario se a funcao encontrar o usuario passado
     * como parâmetro no banco, para considerar sao usado nome e senha
     *
     * @param usuario
     * @return Usuario encontrado no banco de dados
     * @throws java.sql.SQLException
     */
    public boolean selectPorNomeESenha(Usuario usuario) throws SQLException {
        String sql = "select * from usuario where usuario = '"+usuario.getNome()+"' "
                + "and senha = '"+usuario.getSenha()+"'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
       
        return resultSet.next();
    }
    public boolean selectAdministrador(Usuario usuario) throws SQLException {
       usuario.setNivelAcesso("Adminstrador");
        String sql = "select * from usuario where nivel = '"+usuario.getNivelAcesso()+ "'";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
        
    }
    public boolean selectFuncionario(Usuario usuario) throws SQLException {
       
        String sql = "select * from usuario where nivel = 'Funcionário'";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
        
    }

    /**
     * Recebe dois objetos e verifica se são iguais verificando o nome e senha
     *
     * @param usuario
     * @param usuarioAPesquisar
     * @return verdadeiro caso sejam iguais e falso caso nao forem iguais
     */
    private boolean nomeESenhaSaoIguais(Usuario usuario, Usuario usuarioAPesquisar) {
        return usuario.getNome().equals(usuarioAPesquisar.getNome()) && usuario.getSenha().equals(usuarioAPesquisar.getSenha());
    }

    /**
     * Compara se dois objetos tem a propriedade id igual
     *
     * @param usuario
     * @param usuarioAComparar
     * @return verdadeiro caso os id forem iguais e falso se nao forem
     */
    private boolean idSaoIguais(Usuario usuario, Usuario usuarioAComparar) {
        return usuario.getId() == usuarioAComparar.getId();
    }

}
