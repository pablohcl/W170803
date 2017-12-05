package watt.w170803.util.pedidos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import watt.w170803.ActivityProdutos;
import watt.w170803.R;
import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.clientes.ClientesDB;


public class NovoPedido extends AppCompatActivity {

    // Args trazidos com a Intent
    private long clienteSelecionado;
    private long idPedido;

    // Variáveis da classe
    private Pedido pedido;
    private PedidosDB pedDB;

    // Botão voltar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // Finaliza a Activity atual
                break;
            default:break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        // MOSTRANDO O BOTAO VOLTAR #####
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Args trazidos com a Intent
        Bundle args = getIntent().getExtras();
        clienteSelecionado = args.getLong("cliente selecionado");
        idPedido = args.getLong("pedido");

        // verifica se existe pedido aberto e recupera ou cria um novo
        getNovoPedido();
    }

    // Métodos da classe
    private Context getContext(){
        return this;
    }

    // verifica se existe pedido aberto e recupera ou cria um novo
    private void getNovoPedido(){
        pedDB = new PedidosDB(getContext());
        if(pedDB.getPedidoAberto() != null) {
            pedido = pedDB.getPedidoAberto();
        }else{
            pedido = new Pedido(pedDB.getCodigoNovoPedido(), clienteSelecionado);
        }
    }
}
