package watt.w170803;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import watt.w170803.util.BaseDB;
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

        campoBuscaSelecionado = new String[2];

        cDAO = new ClientesDB(this);
        cDAO.abrirBanco();

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
                                campoBuscaSelecionado[0] = BaseDB.CLIENTE_CNPJ;
                                campoBuscaSelecionado[1] = BaseDB.CLIENTE_CPF;
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

        clientes = new ArrayList<>();
        clientes = cDAO.consultar();
        refreshList();

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
                            Intent intent = new Intent(getContext(), TelaClientesCadastroFisica.class);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(getContext(), TelaClientesCadastroJuridica.class);
                            startActivity(intent);
                        }
                    }
                });
                msg.setTitle("Selecione o tipo");
                msg.show();
            }
        });

        imgBtnBuscaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etBuscaClientes.getText().toString().isEmpty()){
                    clientes = new ArrayList<>();
                    clientes = cDAO.consultar();
                    refreshList();
                }else {
                    clientes = new ArrayList<Clientes>();
                    clientes = cDAO.consultar(campoBuscaSelecionado, etBuscaClientes.getText().toString());
                    adapter.notifyDataSetChanged();
                    refreshList();
                }
            }
        });
    }

    private Context getContext(){
        return this;
    }

    private void refreshList(){
        adapter = new ClientesAdapter(ActivityClientes.this, clientes);
        recyclerViewClientes.setAdapter(adapter);
        recyclerViewClientes.setHasFixedSize(true);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));
    }
}
