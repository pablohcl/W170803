package watt.w170803;

/**
 * Created by Usuario on 09/08/2017.
 */

public class ClientesJuridica extends Clientes {

    private Long cnpj;
    private Long inscricaoEstadual;

    public ClientesJuridica(){};

    public ClientesJuridica(long codigoCliente, String tipoCliente, String razaoSocial, String fantasia, Integer cep, String endereco, Integer numero, String complemento, String bairro, String cidade, String contato, String aniver, Integer telefone, Integer telefone2, String email, String obs, Long cnpj, Long inscricaoEstadual) {
        super(codigoCliente, tipoCliente, razaoSocial, fantasia, cep, endereco, numero, complemento, bairro, cidade, contato, aniver, telefone, telefone2, email, obs);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public Long getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(Long inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
