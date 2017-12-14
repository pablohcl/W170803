package watt.w170803.util.produtos.grupos;

/**
 * Created by Pablo Henrique Correa on 14/12/2017.
 */

public class ProdutoGrupo {

    private long idGrupo;
    private String descGrupo;

    public ProdutoGrupo() {
    }

    public ProdutoGrupo(long idGrupo, String descGrupo) {
        this.idGrupo = idGrupo;
        this.descGrupo = descGrupo;
    }

    public long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(String descGrupo) {
        this.descGrupo = descGrupo;
    }
}
