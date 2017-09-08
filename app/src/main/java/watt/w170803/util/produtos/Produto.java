package watt.w170803.util.produtos;

/**
 * Created by Usuario on 08/09/2017.
 */

public class Produto {

    private long idProduto;
    private String descricao;
    private String undMedida;
    private double preco;
    private double precoMin;
    private double precoSugerido;

    public Produto() {
    }

    public Produto(long idProduto, String descricao, String undMedida, double preco, double precoMin, double precoSugerido) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.undMedida = undMedida;
        this.preco = preco;
        this.precoMin = precoMin;
        this.precoSugerido = precoSugerido;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUndMedida() {
        return undMedida;
    }

    public void setUndMedida(String undMedida) {
        this.undMedida = undMedida;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPrecoMin() {
        return precoMin;
    }

    public void setPrecoMin(double precoMin) {
        this.precoMin = precoMin;
    }

    public double getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(double precoSugerido) {
        this.precoSugerido = precoSugerido;
    }
}
