package watt.w170803;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 04/08/2017.
 */

public class Clientes implements Serializable {
    private long codigoCliente;
    private String razaoSocial;
    private String fantasia;
    private Integer cep;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String contato;
    private String aniver;
    private Integer telefone;
    private Integer telefone2;
    private String email;
    private String obs;

    public Clientes() {
    }

    public Clientes(long codigoCliente, String razaoSocial, String fantasia, Integer cep, String endereco, Integer numero, String complemento, String bairro, String cidade, String estado, String contato, String aniver, Integer telefone, Integer telefone2, String email, String obs) {
        this.codigoCliente = codigoCliente;
        this.razaoSocial = razaoSocial;
        this.fantasia = fantasia;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
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

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Integer getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(Integer telefone2) {
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

