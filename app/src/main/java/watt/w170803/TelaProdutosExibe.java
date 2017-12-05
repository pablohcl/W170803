package watt.w170803;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

import watt.w170803.util.produtos.ProdutoDB;
import watt.w170803.util.produtos.Produto;
import watt.w170803.util.produtos.ProdutoAdapter;

public class TelaProdutosExibe extends AppCompatActivity {

    private ProdutoDB pDB;
    private long clicado;

    private TextView tvIdProduto;
    private TextView tvDescProduto;
    private TextView tvUndMedida;
    private TextView tvPreco;
    private TextView tvPrecoMinimo;
    private TextView tvPrecoSugerido;

    private ProdutoAdapter adapter;

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
        setContentView(R.layout.activity_tela_produtos_exibe);

        // RECEBENDO OS PARAMETROS DA ACTIVITY #####
        Bundle args = getIntent().getExtras();
        clicado = args.getLong("clicado");

        // MOSTRANDO O BOTAO VOLTAR #####
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Referências
        tvIdProduto = (TextView) findViewById(R.id.tv_id_tela_produtos_exibe);
        tvDescProduto = (TextView) findViewById(R.id.tv_desc_tela_produtos_exibe);
        tvUndMedida = (TextView) findViewById(R.id.tv_und_medida_tela_produtos_exibe);
        tvPreco = (TextView) findViewById(R.id.tv_preco_tela_produtos_exibe);
        tvPrecoMinimo = (TextView) findViewById(R.id.tv_preco_min_tela_produtos_exibe);
        tvPrecoSugerido = (TextView) findViewById(R.id.tv_preco_sugerido_tela_produtos_exibe);

        // ABRINDO O BANCO E CONSULTANDO OS DADOS DO CLIENTE SELECIONADO #####
        pDB = new ProdutoDB(this);

        Produto pro;
        pro = pDB.consultarTotal(clicado);

        tvIdProduto.setText(String.valueOf(pro.getIdProduto()));
        tvDescProduto.setText(pro.getDescricao());
        tvUndMedida.setText(pro.getUndMedida());
        tvPreco.setText(String.format(Locale.getDefault(), "%.2f", pro.getPreco()));
        tvPrecoMinimo.setText(String.format(Locale.getDefault(), "%.2f", pro.getPrecoMin()));
        tvPrecoSugerido.setText(String.format(Locale.getDefault(), "%.2f", pro.getPrecoSugerido()));

    }

    private Context getContext(){
        return this;
    }
}
