package watt.w170803.util.pedidos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private String argPedido;
    private String argCliente;

    // Variáveis da classe
    private Pedido pedido;
    private PedidosDB pedDB;

    //ViewPager
    private ViewPager viewPager;

    // Botões
    private Button btnSalvar;
    private Button btnCancelar;

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
        if(args.getString("cliente selecionado") != null) {
            clienteSelecionado = Long.parseLong(args.getString("cliente selecionado"));
            argCliente = args.getString("cliente selecionado");
            salvarNoBanco(clienteSelecionado, getContext());
            Log.d("log", "PARAMETRO CLIENTE RECEBIDO PELA ACTIVITY NOVO PEDIDO");
        }
        if(args.getString("pedido") != null){
            argPedido = args.getString("pedido");
            Log.d("log", "PARAMETRO PEDIDO RECEBIDO PELA ACTIVITY NOVO PEDIDO");
            vincularPedidoAberto(argPedido);
            exibirAlertDePedidoAberto();
        }

        btnCancelar = (Button) findViewById(R.id.btn_tela_novo_pedido_cancelar_pedido);
        btnSalvar = (Button) findViewById(R.id.btn_tela_novo_pedido_salvar_pedido);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertCancelar = new AlertDialog.Builder(getContext());
                alertCancelar.setTitle("Atenção!");
                alertCancelar.setMessage("Deseja realmente cancelar o pedido?");
                alertCancelar.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PedidosDB pedDB = new PedidosDB(getContext());
                        pedDB.deletePedido(String.valueOf(pedido.getIdPedido()));

                        AlertDialog.Builder alertCancEfetuado = new AlertDialog.Builder(getContext());
                        alertCancEfetuado.setTitle("Alerta!");
                        alertCancEfetuado.setMessage("Pedido cancelado!");
                        alertCancEfetuado.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        alertCancEfetuado.show();
                    }
                });
                alertCancelar.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertCancelar.show();
            }
        });

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.vp_novo_pedido);
        viewPager.setAdapter(new watt.w170803.util.pedidos.PagerAdapter(getSupportFragmentManager(), getContext(), argPedido, argCliente));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_novo_pedido);
        tabLayout.setupWithViewPager(viewPager);
    }

    // Métodos da classe
    private Context getContext(){
        return this;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void salvarNoBanco(long cliente, Context context){
        PedidosDB pedDB = new PedidosDB(context);
        pedido = new Pedido(cliente, pedDB.getCodigoNovoPedido());
        pedDB.salvarNoBanco(pedido);
    }

    private void exibirAlertDePedidoAberto(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Atenção!");
        alert.setMessage("Opa, parece que ficou um pedido em aberto. Continue ele ou exclua-o");
        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

    private void vincularPedidoAberto(String idPedido){
        PedidosDB pedidosDB = new PedidosDB(getContext());
        pedido = pedidosDB.getPedido(idPedido);
    }
}
