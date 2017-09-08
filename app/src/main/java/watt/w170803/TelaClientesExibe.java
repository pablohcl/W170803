package watt.w170803;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import watt.w170803.util.clientes.ClientesDB;
import watt.w170803.util.clientes.ClientesFisica;
import watt.w170803.util.clientes.ClientesJuridica;

public class TelaClientesExibe extends AppCompatActivity {

    private ClientesDB cDB;
    private long clicado;
    private String tipoCliente;

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

        Bundle args = getIntent().getExtras();
        clicado = args.getLong("clicado");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        cDB = new ClientesDB(this);
        cDB.abrirBanco();
        tipoCliente = cDB.consultaTipoCliente(clicado);

        if(tipoCliente.equals("juridica")) {
            ClientesJuridica cliJur;
            cliJur = cDB.consultarTotalJuridica(clicado);

            tvIdCliente.setText(String.valueOf(cliJur.getCodigoCliente()));
            tvRazaoSocial.setText(cliJur.getRazaoSocial());
            tvFantasia.setText(cliJur.getFantasia());
            tvCnpjCpf.setText(String.valueOf(cliJur.getCnpj()));
            tvInscrRg.setText(String.valueOf(cliJur.getInscricaoEstadual()));
            tvCep.setText(String.valueOf(cliJur.getCep()));
            tvEndereco.setText(cliJur.getEndereco());
            tvNumero.setText(String.valueOf(cliJur.getNumero()));
            tvComplemento.setText(cliJur.getComplemento());
            tvBairro.setText(cliJur.getBairro());
            tvCidade.setText(cliJur.getCidade());
            tvContato.setText(cliJur.getContato());
            tvAniver.setText(cliJur.getAniver());
            tvTelefone.setText(String.valueOf(cliJur.getTelefone()));
            tvTelefone2.setText(String.valueOf(cliJur.getTelefone2()));
            tvEmail.setText(cliJur.getEmail());
            tvObs.setText(cliJur.getObs());
        }else if(tipoCliente.equals("fisica")) {
            ClientesFisica cliFis;
            cliFis = cDB.consultarTotalFisica(clicado);

            tvIdCliente.setText(String.valueOf(cliFis.getCodigoCliente()));
            tvRazaoSocial.setText(cliFis.getRazaoSocial());
            tvFantasia.setText(cliFis.getFantasia());
            tvCnpjCpf.setText(String.valueOf(cliFis.getCpf()));
            tvInscrRg.setText(String.valueOf(cliFis.getRg()));
            tvCep.setText(String.valueOf(cliFis.getCep()));
            tvEndereco.setText(cliFis.getEndereco());
            tvNumero.setText(String.valueOf(cliFis.getNumero()));
            tvComplemento.setText(cliFis.getComplemento());
            tvBairro.setText(cliFis.getBairro());
            tvCidade.setText(cliFis.getCidade());
            tvContato.setText(cliFis.getContato());
            tvAniver.setText(cliFis.getAniver());
            tvTelefone.setText(String.valueOf(cliFis.getTelefone()));
            tvTelefone2.setText(String.valueOf(cliFis.getTelefone2()));
            tvEmail.setText(cliFis.getEmail());
            tvObs.setText(cliFis.getObs());
        }else{
            tvRazaoSocial.setText("Erro, cliente com o campo TIPO nulo.");
        }
    }
}
