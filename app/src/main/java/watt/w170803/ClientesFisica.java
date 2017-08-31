package watt.w170803;

/**
 * Created by Usuario on 09/08/2017.
 */

public class ClientesFisica extends Clientes {

    private Long cpf;
    private Long rg;

    public ClientesFisica(){}

    public ClientesFisica(long codigoCliente, String razaoSocial, String fantasia, Integer cep, String endereco, Integer numero, String complemento, String bairro, String cidade, String estado, String contato, String aniver, Integer telefone, Integer telefone2, String email, String obs, Long cpf, Long rg) {
        super(codigoCliente, razaoSocial, fantasia, cep, endereco, numero, complemento, bairro, cidade, estado, contato, aniver, telefone, telefone2, email, obs);
        this.cpf = cpf;
        this.rg = rg;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getRg() {
        return rg;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }
}
