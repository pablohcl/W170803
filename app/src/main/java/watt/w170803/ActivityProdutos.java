package watt.w170803;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import watt.w170803.util.produtos.Produto;
import watt.w170803.util.produtos.ProdutoAdapter;
import watt.w170803.util.db.ProdutoDB;

public class ActivityProdutos extends AppCompatActivity {

    private RecyclerView rvProdutos;
    private Context context;
    private ArrayList<Produto> produtos;
    private ProdutoDB produtoDB;
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        // REFERÊNCIAS @@@@@
        rvProdutos = (RecyclerView) findViewById(R.id.rv_produtos);
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