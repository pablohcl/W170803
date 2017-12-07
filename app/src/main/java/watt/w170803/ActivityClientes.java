package watt.w170803;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import watt.w170803.util.db.BaseDB;
import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.clientes.ClientesAdapter;
import watt.w170803.util.clientes.ClientesDB;

public class ActivityClientes extends AppCompatActivity {

    //Banco
    private ClientesDB cDAO; //instância responsável pela persistência dos dados

    //Widgets
    private RecyclerView recyclerViewClientes;
    private ImageButton imgBtnBuscaCliente;
    private Button btnNovoCliente;
    private EditText etBuscaClientes;
    private ImageButton imgBtnTipoBuscaClientes;
    private String[] campoBuscaSelecionado;
    private int opcaoSelecionada;

    private ClientesAdapter adapter;
    private ArrayList<Clientes> clientes;

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
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        //up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Referencias
        recyclerViewClientes = (RecyclerView) findViewById(R.id.rv_clientes);
        imgBtnBuscaCliente = (ImageButton) findViewById(R.id.img_btn_busca_clientes);
        btnNovoCliente = (Button) findViewById(R.id.btn_novo_cliente);
        etBuscaClientes = (EditText) findViewById(R.id.et_busca_clientes);
        imgBtnTipoBuscaClientes = (ImageButton) findViewById(R.id.img_btn_campo_cliente_busca);
        cDAO = new ClientesDB(this);
        clientes = new ArrayList<>();

        // MOSTRA TODOS OS CLIENTES CADASTRADOS @@@@@
        mostrarTodos();
        // MOSTRA TODOS OS CLIENTES CADASTRADOS #####

        // ALERT DIALOG PARA SELECIONAR CAMPOS PARA A BUSCA @@@@@
        campoBuscaSelecionado = new String[2];
        campoBuscaSelecionado[0] = BaseDB.CLIENTE_RAZAO_SOCIAL;
        campoBuscaSelecionado[1] = BaseDB.CLIENTE_FANTASIA;
        imgBtnTipoBuscaClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcaoSelecionada=0;
                etBuscaClientes.setText(null);

                AlertDialog.Builder msg = new AlertDialog.Builder(ActivityClientes.this);

                msg.setSingleChoiceItems(R.array.array_alert_campo_busca_clientes, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        opcaoSelecionada=i;
                    }
                });
                msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(opcaoSelecionada){
                            case 0:
                                etBuscaClientes.setHint(R.string.alert_campo_razao);
                                campoBuscaSelecionado[0] = BaseDB.CLIENTE_RAZAO_SOCIAL;
                                campoBuscaSelecionado[1] = BaseDB.CLIENTE_FANTASIA;
                                break;
                            case 1:
                                etBuscaClientes.setHint(R.string.alert_campo_cnpj);
                                campoBuscaSelecionado[0] = BaseDB.CLIENTE_CNPJ_OU_CPF;
                                campoBuscaSelecionado[1] = BaseDB.CLIENTE_CNPJ_OU_CPF;
                                break;
                            case 2:
                                etBuscaClientes.setHint(R.string.alert_campo_endereco);
                                campoBuscaSelecionado[0] = BaseDB.CLIENTE_ENDERECO;
                                campoBuscaSelecionado[1] = BaseDB.CLIENTE_CIDADE;
                                break;
                            default:
                                etBuscaClientes.setHint("Erro no botão de selecionar tipo de campo da busca");
                        }
                    }
                });
                msg.setTitle("Selecione o campo para buscar");
                msg.show();
            }
        });
        // ALERT DIALOG PARA SELECIONAR CAMPOS PARA A BUSCA #####

        // CADASTRAR NOVO CLIENTE @@@@@
        btnNovoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcaoSelecionada=0;

                AlertDialog.Builder msg = new AlertDialog.Builder(ActivityClientes.this);

                msg.setSingleChoiceItems(R.array.array_alert_clientes_cadastro_tipo, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        opcaoSelecionada=i;
                    }
                });
                msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(opcaoSelecionada==0){
                            Bundle args = new Bundle();
                            args.putString("tipo cliente", "FALSO");
                            Intent intent = new Intent(getContext(), TelaClientesCadastroFisica.class);
                            intent.putExtras(args);
                            startActivity(intent);
                        }else{
                            Bundle args = new Bundle();
                            args.putString("tipo cliente", "VERDADEIRO");
                            Intent intent = new Intent(getContext(), TelaClientesCadastroFisica.class);
                            intent.putExtras(args);
                            startActivity(intent);
                        }
                    }
                });
                msg.setTitle("Selecione o tipo");
                msg.show();
            }
        });
        // CADASTRAR NOVO CLIENTE #####

        // BUSCAR CLIENTES @@@@@
        imgBtnBuscaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etBuscaClientes.getText().toString().isEmpty()){
                    mostrarTodos();
                }else {
                    buscarClientes();
                }
            }
        });
        // BUSCAR CLIENTES #####

    }// END OnCreate

    // MÉTODOS DA CLASSE #####
    private Context getContext(){
        return this;
    }

    // ATUALIZA A LISTA EXIBIDA NA TELA
    private void refreshList(){
        adapter = new ClientesAdapter(ActivityClientes.this, clientes);
        recyclerViewClientes.setAdapter(adapter);
        recyclerViewClientes.setHasFixedSize(true);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));
    }

    // BUSCA TODOS OS CLIENTES CADASTRADOS
    private void mostrarTodos(){

        clientes = cDAO.consultar();
        refreshList();
    }

    // BUSCA OS CLIENTES DE ACORDO COM O DIGITADO
    private void buscarClientes(){

        clientes = cDAO.consultar(campoBuscaSelecionado, etBuscaClientes.getText().toString());
        refreshList();
    }
}
