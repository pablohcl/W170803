package watt.w170803.util.pedidos;

/**
 * Created by Pablo Henrique Correa on 14/11/2017.
 */

public class Pedido {

    private Boolean finalizado;
    private long idPedido;
    private long idCliente;
    private String condicaoPgto;
    private String obs;
    private double total;

    public Pedido(long cliente, long pedido){
        this.finalizado = false;
        this.idCliente = cliente;
        this.idPedido = pedido;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getCondicaoPgto() {
        return condicaoPgto;
    }

    public void setCondicaoPgto(String condicaoPgto) {
        this.condicaoPgto = condicaoPgto;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
