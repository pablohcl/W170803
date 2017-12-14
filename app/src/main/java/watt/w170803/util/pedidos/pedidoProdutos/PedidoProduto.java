package watt.w170803.util.pedidos.pedidoProdutos;

/**
 * Created by Pablo Henrique Correa on 14/12/2017.
 */

public class PedidoProduto {

    private long id;
    private long idPedido;
    private long idProduto;
    private double quantidade;
    private double valor;
    private double total;

    public PedidoProduto() {
    }

    public PedidoProduto(long id, long idPedido, long idProduto, double quantidade, double valor, double total) {
        this.id = id;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
