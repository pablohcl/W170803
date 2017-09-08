package watt.w170803;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import watt.w170803.util.produtos.Produto;
import watt.w170803.util.produtos.ProdutoAdapter;
import watt.w170803.util.produtos.ProdutoDB;

public class ActivityProdutos extends AppCompatActivity {

    private RecyclerView rvProdutos;

    private Produto p;
    private ProdutoDB proDB;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        rvProdutos = (RecyclerView) findViewById(R.id.rv_produtos);
        context = ActivityProdutos.this;

        proDB = new ProdutoDB(context);
        proDB.abrirBanco();

        p = new Produto();
        p.setIdProduto(2);
        p.setDescricao("produto1");
        p.setUndMedida("cx");
        p.setPreco(19.90);
        p.setPrecoMin(14.90);
        p.setPrecoSugerido(15.90);

        proDB.inserir(p);

        ArrayList<Produto> produtos = new ArrayList<>();
        produtos = proDB.consultar();
        ProdutoAdapter adapter = new ProdutoAdapter(context, produtos);
        rvProdutos.setAdapter(adapter);
        rvProdutos.setHasFixedSize(true);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
    }
}
