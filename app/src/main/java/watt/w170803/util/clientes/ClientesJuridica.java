package watt.w170803.util.clientes;

import watt.w170803.util.clientes.Clientes;

/**
 * Created by Usuario on 09/08/2017.
 */

public class ClientesJuridica extends Clientes {

    private String cnpj;
    private String inscricaoEstadual;

    public ClientesJuridica(){};

    public ClientesJuridica(long codigoCliente, String tipoCliente, String razaoSocial, String fantasia, String cep, String endereco, String numero, String complemento, String bairro, String cidade, String contato, String aniver, String telefone, String telefone2, String email, String obs, String cnpj, String inscricaoEstadual) {
        super(codigoCliente, tipoCliente, razaoSocial, fantasia, cep, endereco, numero, complemento, bairro, cidade, contato, aniver, telefone, telefone2, email, obs);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
