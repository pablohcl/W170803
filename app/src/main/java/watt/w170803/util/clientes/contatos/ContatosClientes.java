package watt.w170803.util.clientes.contatos;

/**
 * Created by Usuario on 11/10/2017.
 */

public class ContatosClientes {

    private long idContato;
    private long cadastro;
    private String contatoContatos;
    private String ddd1Contato;
    private String fone1Contato;
    private String ddd2Contato;
    private String fone2Contato;
    private String emailContato;

    public ContatosClientes() {
    }

    public ContatosClientes(long idContato, long cadastro, String contatoContatos, String ddd1Contato, String fone1Contato, String ddd2Contato, String fone2Contato, String emailContato) {
        this.idContato = idContato;
        this.cadastro = cadastro;
        this.contatoContatos = contatoContatos;
        this.ddd1Contato = ddd1Contato;
        this.fone1Contato = fone1Contato;
        this.ddd2Contato = ddd2Contato;
        this.fone2Contato = fone2Contato;
        this.emailContato = emailContato;
    }

    public long getIdContato() {
        return idContato;
    }

    public void setIdContato(long idContato) {
        this.idContato = idContato;
    }

    public long getCadastro() {
        return cadastro;
    }

    public void setCadastro(long cadastro) {
        this.cadastro = cadastro;
    }

    public String getContatoContatos() {
        return contatoContatos;
    }

    public void setContatoContatos(String contatoContatos) {
        this.contatoContatos = contatoContatos;
    }

    public String getDdd1Contato() {
        return ddd1Contato;
    }

    public void setDdd1Contato(String ddd1Contato) {
        this.ddd1Contato = ddd1Contato;
    }

    public String getFone1Contato() {
        return fone1Contato;
    }

    public void setFone1Contato(String fone1Contato) {
        this.fone1Contato = fone1Contato;
    }

    public String getDdd2Contato() {
        return ddd2Contato;
    }

    public void setDdd2Contato(String ddd2Contato) {
        this.ddd2Contato = ddd2Contato;
    }

    public String getFone2Contato() {
        return fone2Contato;
    }

    public void setFone2Contato(String fone2Contato) {
        this.fone2Contato = fone2Contato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }
}
