package watt.w170803.util.pedidos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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


public class NovoPedido extends AppCompatActivity implements FragTela1NovoPedido.OnFragmentInteractionListener, FragTela2ProdutosNovoPedido.OnFragmentInteractionListener, FragTela3ResumoNovoPedido.OnFragmentInteractionListener {

    // Args trazidos com a Intent
    private long clienteSelecionado;
    private long idPedido;

    // Variáveis da classe
    private Pedido pedido;
    private PedidosDB pedDB;

    //ViewPager
    private ViewPager viewPager;

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
        clienteSelecionado = Long.parseLong(args.getString("cliente selecionado"));
        idPedido = args.getLong("pedido");

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.vp_novo_pedido);
        viewPager.setAdapter(new watt.w170803.util.pedidos.PagerAdapter(getSupportFragmentManager(), getContext(), String.valueOf(clienteSelecionado)));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_novo_pedido);
        tabLayout.setupWithViewPager(viewPager);

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
