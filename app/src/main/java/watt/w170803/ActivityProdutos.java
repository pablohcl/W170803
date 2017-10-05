package watt.w170803;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import watt.w170803.util.produtos.Produto;
import watt.w170803.util.produtos.ProdutoAdapter;
import watt.w170803.util.produtos.ProdutoDB;

public class ActivityProdutos extends AppCompatActivity {

    private RecyclerView rvProdutos;

    private Produto p;
    private ProdutoDB proDB;
    private Context context;
    private byte[] respostaWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        rvProdutos = (RecyclerView) findViewById(R.id.rv_produtos);
        context = ActivityProdutos.this;

        // Realiza uma carga inicial do banco de dados, à partir de um arquivo texto CSV.
        //fazerCargaInicial(this);

        // Obtem lista de todos os funcionários da base.
        ArrayList<Produto> listaProd = new ArrayList<Produto>();
        ProdutoDB prodDB = new ProdutoDB(this);
        prodDB.abrirBanco();
        listaProd=prodDB.consultar();
        prodDB.fecharBanco();

        /*proDB = new ProdutoDB(context);
        proDB.abrirBanco();

        p = new Produto();
        p.setIdProduto(2);
        p.setDescricao("produto1");
        p.setUndMedida("cx");
        p.setPreco(19.90);
        p.setPrecoMin(14.90);
        p.setPrecoSugerido(                                                                                                                                 15.90);

        proDB.inserir(p);

        ArrayList<Produto> produtos = new ArrayList<>();
        produtos = proDB.consultar();*/
        ProdutoAdapter adapter = new ProdutoAdapter(context, listaProd);
        rvProdutos.setAdapter(adapter);
        rvProdutos.setHasFixedSize(true);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));

    }

    public void fazerCargaInicial(Context ctx)
    {
        // Obtém DAO do funcionário.
        ProdutoDB db = new ProdutoDB(ctx);
        db.abrirBanco();

        String filename = "produtos";


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.wattdistribuidora.com.br/mobile/produtos.txt", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                respostaWeb = response;
                Log.d("transmissão", "TRANSMISSÃO EFETUADA COM SUCESSO.");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });



        try
        {

            escreverTemp(respostaWeb, filename);


            /*AssetManager assetManager= ctx.getAssets();
            //InputStreamReader is=new InputStreamReader(assetManager.open("produtos"));
            InputStreamReader is=new InputStreamReader(openFileInput(filename));
            BufferedReader reader=new BufferedReader(is);
            String linha;
            String idProduto, descricao, undMedida, preco, precoMin, precoSugerido;

            while ((linha=reader.readLine()) != null)
            {
                String[] dadosDaLinha=linha.split(";");
                Produto prod = new Produto();

                prod.setIdProduto(Integer.parseInt(dadosDaLinha[0]));
                prod.setDescricao(dadosDaLinha[1]);
                prod.setUndMedida(dadosDaLinha[2]);
                prod.setPreco(Double.parseDouble(dadosDaLinha[3]));
                prod.setPrecoMin(Double.parseDouble(dadosDaLinha[4]));
                prod.setPrecoSugerido(Double.parseDouble(dadosDaLinha[5]));

                db.inserir(prod);

            }
            is.close();*/
        }
        catch (IOException ex)
        {
            Log.i("debug", "erro" + ex.getMessage() );
        }

        db.fecharBanco();

    }

    public void escreverTemp(byte[] respostaWeb, String filename)throws IOException{
        File file = new File(context.getFilesDir(), filename);
        file.setWritable(true);
        //FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
        FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
        fos.write(respostaWeb);
        fos.close();
    }
}