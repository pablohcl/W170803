package watt.w170803;

/**
 * Created by Usuario on 09/08/2017.
 */

public class ClientesFisica extends Clientes {

    private String cpf;
    private String rg;

    public ClientesFisica(){}

    public ClientesFisica(long codigoCliente, String tipoCliente, String razaoSocial, String fantasia, String cep, String endereco, String numero, String complemento, String bairro, String cidade, String contato, String aniver, String telefone, String telefone2, String email, String obs, String cpf, String rg) {
        super(codigoCliente, tipoCliente, razaoSocial, fantasia, cep, endereco, numero, complemento, bairro, cidade, contato, aniver, telefone, telefone2, email, obs);
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
