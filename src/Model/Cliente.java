package Model;

public class Cliente extends Pessoa {

    public String cep;
    public String endereco;

    public Cliente(int id, String nome, String sexo,
            String dataNascimento, String numero, String email, String rg, String endereco, String cep) {
        super(id, nome, sexo, dataNascimento, numero, email, rg);
        this.endereco = endereco;
        this.cep = cep;
    }

   

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    

    @Override
    public String toString() {
        return getNome();
    }
    
}
