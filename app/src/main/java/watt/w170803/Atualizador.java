package watt.w170803;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;
import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.db.ClientesDB;
import watt.w170803.util.produtos.Produto;
import watt.w170803.util.db.ProdutoDB;

public class Atualizador extends AppCompatActivity {

    // ATUALIZAÇÃO PRODUTOS
    private String fileNameProdutos;
    ProgressBar progressBar;
    Context context;
    private Button btn;

    // ATUALIZAÇÃO CLIENTES
    private String fileNameClientes;
    private Button btnAtualizaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizador);

        btn = (Button) findViewById(R.id.btn);
        btnAtualizaClientes = (Button) findViewById(R.id.btn_atualiza_clientes);
        fileNameProdutos = "produtos";
        fileNameClientes = "clientes";
        context = getBaseContext();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importarWeb(fileNameProdutos);
            }
        });

        btnAtualizaClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importarWeb(fileNameClientes);
            }
        });


    } // END OnCreate

    // MÉTODOS DA CLASSE #####
    public void importarWeb(final String fileName){

        // PROGRESS BAR #####
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable();
        progressBar.setVisibility(View.VISIBLE);
        // PROGRESS BAR #####

        // IMPORTANDO O ARQUIVO DA WEB #####
        Log.d("log", "INICIANDO ASYNC HTTP");
        File fileProd = new File(context.getFilesDir(), fileName);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.wattdistribuidora.com.br/mobile/"+fileName+".txt", new FileAsyncHttpResponseHandler(fileProd) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progressBar.setVisibility(View.GONE);
                Log.d("log", "ERRO NA TRANSMISSÃO, STATUS CODE: "+statusCode);
                Log.d("log", "ASYNC HTTP FINALIZADO");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Log.d("log", "TRANSMISSÃO EFETUADA COM SUCESSO, STATUS CODE: "+statusCode);

                // SALVANDO NO BANCO #####
                try {
                    salvarNoBanco(fileName, file);
                } catch(Exception e){
                    Log.d("log", "Exception:"+e);
                }
            }
        });
        // IMPORTANDO O ARQUIVO DA WEB #####
    }

    public void salvarNoBanco(String fileName, File file) throws IOException{

        switch(fileName){
            case "produtos":

                Log.d("log", "INICIANDO SALVAMENTO DOS DADOS");
                ProdutoDB proDB = new ProdutoDB(this);
                proDB.abrirBanco();
                proDB.recriarTblProdutos();

                //InputStreamReader is=new InputStreamReader(openFileInput(fileName));
                FileInputStream is = new FileInputStream(file);
                InputStreamReader isReader = new InputStreamReader(is, "windows-1250");
                BufferedReader reader=new BufferedReader(isReader);
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

                // ALERT DIALOG AVISANDO QUE ESTA OK #####
                AlertDialog.Builder alert = new AlertDialog.Builder(Atualizador.this);
                alert.setMessage("Transmissão efetuada com sucesso.");
                alert.setTitle("Alerta");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btn.setBackgroundResource(R.drawable.screen_green_border);
                    }
                });
                alert.show();

                break;

            case "clientes":

                Log.d("log", "INICIANDO SALVAMENTO DOS DADOS");
                ClientesDB cliDB = new ClientesDB(this);
                cliDB.abrirBanco();
                cliDB.recriarTblClientes();

                FileInputStream isCli = new FileInputStream(file);
                InputStreamReader isReaderCli = new InputStreamReader(isCli, "windows-1250");
                BufferedReader readerCli=new BufferedReader(isReaderCli);
                String linhaCli;
                String idCliente, razaoSocial, fantasia, cnpjCpf, inscricao, cep, endereco, numero, complemento, bairro, cidade, aniver, ddd1, telefone1, ddd2, telefone2, email, obs, eJuridica;

                while ((linha=readerCli.readLine()) != null)
                {
                    String[] dadosDaLinha=linha.split(";");
                    Clientes cli = new Clientes();

                    cli.setCodigoCliente(Integer.parseInt(dadosDaLinha[0]));
                    cli.setRazaoSocial(dadosDaLinha[1]);
                    cli.setFantasia(dadosDaLinha[2]);
                    cli.setCnpjOuCpf(dadosDaLinha[3]);
                    cli.setInscricaoOuRg(dadosDaLinha[4]);
                    cli.setCep(dadosDaLinha[5]);
                    cli.setEndereco(dadosDaLinha[6]);
                    cli.setNumero(dadosDaLinha[7]);
                    cli.setComplemento(dadosDaLinha[8]);
                    cli.setBairro(dadosDaLinha[9]);
                    cli.setCidade(dadosDaLinha[10]);
                    cli.setAniver(dadosDaLinha[11]);
                    cli.setDdd1(dadosDaLinha[12]);
                    cli.setTelefone(dadosDaLinha[13]);
                    cli.setDdd2(dadosDaLinha[14]);
                    cli.setTelefone2(dadosDaLinha[15]);
                    cli.setEmail(dadosDaLinha[16]);
                    cli.setObs(dadosDaLinha[17]);
                    cli.seteJuridica(dadosDaLinha[18]);

                    cliDB.inserir(cli);

                }

                isCli.close();
                cliDB.fecharBanco();
                Log.d("log", "DADOS SALVOS NO BANCO COM SUCESSO");

                // PROGRESS BAR #####
                progressBar.setVisibility(View.GONE);
                // PROGRESS BAR #####

                // ALERT DIALOG AVISANDO QUE ESTA OK #####
                AlertDialog.Builder alertAtualizarClientes = new AlertDialog.Builder(Atualizador.this);
                alertAtualizarClientes.setMessage("Transmissão efetuada com sucesso.");
                alertAtualizarClientes.setTitle("Alerta");
                alertAtualizarClientes.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btnAtualizaClientes.setBackgroundResource(R.drawable.screen_green_border);
                    }
                });
                alertAtualizarClientes.show();

                break;

            default:

        }


    }
}
