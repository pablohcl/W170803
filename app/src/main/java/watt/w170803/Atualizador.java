package watt.w170803;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;
import watt.w170803.util.BaseDB;
import watt.w170803.util.produtos.Produto;
import watt.w170803.util.produtos.ProdutoDB;

public class Atualizador extends AppCompatActivity {

    private String fileName = "produtos";
    ProgressBar progressBar;
    Context context = getBaseContext();
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizador);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importarWeb();
            }
        });


    } // END OnCreate

    // MÉTODOS DA CLASSE #####
    public void importarWeb(){

        // PROGRESS BAR #####
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable();
        progressBar.setVisibility(View.VISIBLE);
        // PROGRESS BAR #####

        // IMPORTANDO O ARQUIVO DA WEB #####
        Log.d("log", "INICIANDO ASYNC HTTP");
        File fileProd = new File(context.getFilesDir(), fileName);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.wattdistribuidora.com.br/mobile/produtos.txt", new FileAsyncHttpResponseHandler(fileProd) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Log.d("log", "ERRO NA TRANSMISSÃO, STATUS CODE: "+statusCode);
                Log.d("log", "ASYNC HTTP FINALIZADO");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Log.d("log", "TRANSMISSÃO EFETUADA COM SUCESSO, STATUS CODE: "+statusCode);

                // SALVANDO NO BANCO #####
                try {
                    salvarNoBanco();
                }catch(IOException e){
                    Log.d("log", "ERRO AO SALVAR NO BANCO, MSG: "+e);
                }
                Log.d("log", "ASYNC HTTP FINALIZADO");
            }
        });
        // IMPORTANDO O ARQUIVO DA WEB #####
    }

    public void salvarNoBanco() throws IOException{

        Log.d("log", "INICIANDO SALVAMENTO DOS DADOS");
        ProdutoDB proDB = new ProdutoDB(this);
        proDB.abrirBanco();
        proDB.recriarTblProdutos();

        InputStreamReader is=new InputStreamReader(openFileInput(fileName));
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

            proDB.inserir(prod);

        }

        is.close();
        proDB.fecharBanco();
        Log.d("log", "DADOS SALVOS NO BANCO COM SUCESSO");

        // PROGRESS BAR #####
        progressBar.setVisibility(View.GONE);
        // PROGRESS BAR #####
    }
}
