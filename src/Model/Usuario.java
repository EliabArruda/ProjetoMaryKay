package Model;

public class Usuario extends Pessoa {

    protected String senha;
    protected String nivelAcesso;

    public Usuario( int id, String nome, String senha) {
        super(id, nome);
        this.senha = senha;
    }

    public Usuario(int id, String nome, String sexo, String dataNascimento, String numero, String email, String rg, String senha, String nivelAcesso) {
        super(id, nome, sexo, dataNascimento, numero, email, rg);
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivelAcesso() {
            
       
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public String toString() {
        return getNivelAcesso();
    }

}
