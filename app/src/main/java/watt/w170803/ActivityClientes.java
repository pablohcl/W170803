package watt.w170803;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static watt.w170803.ActivityClientes.itemClicado;

public class ActivityClientes extends AppCompatActivity {

    //Banco
    private ClientesDB cDAO; //instância responsável pela persistência dos dados

    //Widgets
    private RecyclerView recyclerViewClientes;
    private Button btnBuscarCliente;
    private Button btnNovoCliente;

    private int opcaoSelecionada;
    protected static String itemClicado;

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
        btnBuscarCliente = (Button) findViewById(R.id.btn_busca_cliente);
        btnNovoCliente = (Button) findViewById(R.id.btn_novo_cliente);

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
                            Bundle params = new Bundle();
                            intent.putExtras(params);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(getContext(), TelaClientesCadastroJuridica.class);
                            Bundle params = new Bundle();
                            intent.putExtras(params);
                            startActivity(intent);
                        }
                    }
                });
                msg.setTitle("Selecione o tipo");
                msg.show();
            }
        });

        cDAO = new ClientesDB(this);
        cDAO.abrirBanco();
        clientes = new ArrayList<>();
        clientes = cDAO.consultar();
        adapter = new ClientesAdapter(ActivityClientes.this, clientes);
        recyclerViewClientes.setAdapter(adapter);
        recyclerViewClientes.setHasFixedSize(true);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));

        btnBuscarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
