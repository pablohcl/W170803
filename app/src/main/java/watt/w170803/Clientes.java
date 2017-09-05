package watt.w170803;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 04/08/2017.
 */

public class Clientes implements Serializable {
    private long codigoCliente;
    private String tipoCliente;
    private String razaoSocial;
    private String fantasia;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String contato;
    private String aniver;
    private String telefone;
    private String telefone2;
    private String email;
    private String obs;

    public Clientes() {
    }

    public Clientes(long codigoCliente, String tipoCliente, String razaoSocial, String fantasia, String cep, String endereco, String numero, String complemento, String bairro, String cidade, String contato, String aniver, String telefone, String telefone2, String email, String obs) {
        this.codigoCliente = codigoCliente;
        this.tipoCliente = tipoCliente;
        this.razaoSocial = razaoSocial;
        this.fantasia = fantasia;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.contato = contato;
        this.aniver = aniver;
        this.telefone = telefone;
        this.telefone2 = telefone2;
        this.email = email;
        this.obs = obs;
    }

    public long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getAniver() {
        return aniver;
    }

    public void setAniver(String aniver) {
        this.aniver = aniver;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}

