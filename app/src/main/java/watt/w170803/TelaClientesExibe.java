package watt.w170803;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TelaClientesExibe extends AppCompatActivity {

    private ClientesDB cDB;
    private ClientesJuridica cliJur;
    private String clicado;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes_exibe);

        Bundle args = getIntent().getExtras();
        clicado = args.getString("clicado");

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
        cliJur = new ClientesJuridica();
        cliJur = cDB.consultarTotal(clicado);
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
    }
}
