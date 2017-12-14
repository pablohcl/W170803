package watt.w170803;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

import watt.w170803.util.produtos.Produto;
import watt.w170803.util.produtos.ProdutoAdapter;
import watt.w170803.util.produtos.ProdutoDB;

public class ActivityProdutos extends AppCompatActivity {

    private RecyclerView rvProdutos;
    private Context context;
    private ArrayList<Produto> produtos;
    private ProdutoDB produtoDB;
    private ProdutoAdapter adapter;
    private Spinner spinner;

    // ARGS VINDOS COM A INTENT
    private long idPedido;

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
        setContentView(R.layout.activity_produtos);

        //up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ARGS VINDOS COM A INTENT
        Bundle args;
        if(getIntent().getExtras() != null){
            args = getIntent().getExtras();
            idPedido = args.getLong("id pedido");
        }

        // REFERÊNCIAS @@@@@
        rvProdutos = (RecyclerView) findViewById(R.id.rv_produtos);
        spinner = (Spinner) findViewById(R.id.sn_grupos_produtos);
        context = ActivityProdutos.this;
        produtos = new ArrayList<Produto>();
        produtoDB = new ProdutoDB(this);

        // BUSCA TODOS OS PRODUTOS CADASTRADOS @@@@@
        buscarProdutos();

        // ATUALIZA A LISTA DE PRODUTOS DA TELA @@@@@
        atualizarLista();

    }

    public void buscarProdutos(){

        produtoDB.abrirBanco();
        produtos = produtoDB.consultar();
        produtoDB.fecharBanco();
    }

    public void atualizarLista(){

        adapter = new ProdutoAdapter(context, produtos);
        rvProdutos.setAdapter(adapter);
        rvProdutos.setHasFixedSize(true);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
    }
}