package watt.w170803;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.clientes.contatos.ContatosAdapter;
import watt.w170803.util.clientes.contatos.ContatosClientes;
import watt.w170803.util.clientes.contatos.ContatosClientesDB;
import watt.w170803.util.db.ClientesDB;

public class TelaClientesExibe extends AppCompatActivity {

    private ClientesDB cDB;
    private ContatosClientesDB contatosDB;
    private long clicado;
    private String eJuridica;

    private TextView tvIdCliente;
    private TextView tvRazaoSocial;
    private TextView tvFantasia;
    private TextView tvCnpjCpf;
    private TextView tvInscrRg;
    private TextView tvCep;
    private TextView tvEndereco;
    private TextView tvNumero;
    private TextView tvComplemento;
    private TextView tvBairro;
    private TextView tvCidade;
    private TextView tvContato;
    private TextView tvAniver;
    private TextView tvTelefone;
    private TextView tvTelefone2;
    private TextView tvEmail;
    private TextView tvObs;

    private RecyclerView rvContatosClientes;
    private ContatosAdapter adapter;
    private ArrayList<ContatosClientes> contatos;

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
        setContentView(R.layout.activity_tela_clientes_exibe);

        // RECEBENDO OS PARAMETROS DA ACTIVITY #####
        Bundle args = getIntent().getExtras();
        clicado = args.getLong("clicado");

        // MOSTRANDO O BOTAO VOLTAR #####
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // REFERENCIAS #####
        tvIdCliente = (TextView) findViewById(R.id.tv_id_clientes_exibe);
        tvRazaoSocial = (TextView) findViewById(R.id.tv_razao_social_clientes_exibe);
        tvFantasia = (TextView) findViewById(R.id.tv_fantasia_clientes_exibe);
        tvCnpjCpf = (TextView) findViewById(R.id.tv_cpf_cnpj_clientes_exibe);
        tvInscrRg = (TextView) findViewById(R.id.tv_rg_inscr_clientes_exibe);
        tvCep = (TextView) findViewById(R.id.tv_cep_clientes_exibe);
        tvEndereco = (TextView) findViewById(R.id.tv_endereco_clientes_exibe);
        tvNumero = (TextView) findViewById(R.id.tv_numero_clientes_exibe);
        tvComplemento = (TextView) findViewById(R.id.tv_complemento_clientes_exibe);
        tvBairro = (TextView) findViewById(R.id.tv_bairro_clientes_exibe);
        tvCidade = (TextView) findViewById(R.id.tv_cidade_clientes_exibe);
        tvContato = (TextView) findViewById(R.id.tv_contato_clientes_exibe);
        tvAniver = (TextView) findViewById(R.id.tv_aniver_clientes_exibe);
        tvTelefone = (TextView) findViewById(R.id.tv_telefone_clientes_exibe);
        tvTelefone2 = (TextView) findViewById(R.id.tv_telefone2_clientes_exibe);
        tvEmail = (TextView) findViewById(R.id.tv_email_clientes_exibe);
        tvObs = (TextView) findViewById(R.id.tv_obs_clientes_exibe);

        rvContatosClientes = (RecyclerView) findViewById(R.id.rv_contatos_clientes);
        contatos = new ArrayList<>();
        contatosDB = new ContatosClientesDB(getContext());

        // ABRINDO O BANCO E CONSULTANDO OS DADOS DO CLIENTE SELECIONADO #####
        cDB = new ClientesDB(this);
        eJuridica = cDB.consultaTipoCliente(clicado);

        if(eJuridica.equals("VERDADEIRO")) {
            Clientes cliJur;
            cliJur = cDB.consultarTotal(clicado);

            tvIdCliente.setText(String.valueOf(cliJur.getCodigoCliente()));
            tvRazaoSocial.setText(cliJur.getRazaoSocial());
            tvFantasia.setText(cliJur.getFantasia());
            tvCnpjCpf.setText(String.valueOf(cliJur.getCnpjOuCpf()));
            tvInscrRg.setText(String.valueOf(cliJur.getInscricaoOuRg()));
            tvCep.setText(String.valueOf(cliJur.getCep()));
            tvEndereco.setText(cliJur.getEndereco());
            tvNumero.setText(String.valueOf(cliJur.getNumero()));
            tvComplemento.setText(cliJur.getComplemento());
            tvBairro.setText(cliJur.getBairro());
            tvCidade.setText(cliJur.getCidade());
            tvAniver.setText(cliJur.getAniver());
            tvTelefone.setText(String.valueOf(cliJur.getTelefone()));
            tvTelefone2.setText(String.valueOf(cliJur.getTelefone2()));
            tvEmail.setText(cliJur.getEmail());
            tvObs.setText(cliJur.getObs());

        }else if(eJuridica.equals("FALSO")) {
            Clientes cliFis;
            cliFis = cDB.consultarTotal(clicado);

            tvIdCliente.setText(String.valueOf(cliFis.getCodigoCliente()));
            tvRazaoSocial.setText(cliFis.getRazaoSocial());
            tvFantasia.setText(cliFis.getFantasia());
            tvCnpjCpf.setText(String.valueOf(cliFis.getCnpjOuCpf()));
            tvInscrRg.setText(String.valueOf(cliFis.getInscricaoOuRg()));
            tvCep.setText(String.valueOf(cliFis.getCep()));
            tvEndereco.setText(cliFis.getEndereco());
            tvNumero.setText(String.valueOf(cliFis.getNumero()));
            tvComplemento.setText(cliFis.getComplemento());
            tvBairro.setText(cliFis.getBairro());
            tvCidade.setText(cliFis.getCidade());
            tvAniver.setText(cliFis.getAniver());
            tvTelefone.setText(String.valueOf(cliFis.getTelefone()));
            tvTelefone2.setText(String.valueOf(cliFis.getTelefone2()));
            tvEmail.setText(cliFis.getEmail());
            tvObs.setText(cliFis.getObs());
        }else{
            tvRazaoSocial.setText("Erro, cliente com o campo TIPO nulo.");
        }
        mostrarTodos();
    }

    private Context getContext(){
        return this;
    }

    private void refreshList(){
        adapter = new ContatosAdapter(TelaClientesExibe.this, contatos);
        rvContatosClientes.setAdapter(adapter);
        rvContatosClientes.setHasFixedSize(true);
        rvContatosClientes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void mostrarTodos(){

        contatos = contatosDB.consultar(clicado);
        refreshList();
    }
}
