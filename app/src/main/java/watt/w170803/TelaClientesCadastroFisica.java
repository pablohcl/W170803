package watt.w170803;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import watt.w170803.util.DatePickerFragment;
import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.clientes.contatos.ContatosAdapter;
import watt.w170803.util.clientes.contatos.ContatosClientes;
import watt.w170803.util.clientes.contatos.ContatosClientesDB;
import watt.w170803.util.clientes.ClientesDB;

public class TelaClientesCadastroFisica extends AppCompatActivity {

    private EditText etRazaoSocial;
    private EditText etFantasia;
    private EditText etCpf;
    private EditText etRg;
    private EditText etCep;
    private EditText etEndereco;
    private EditText etNumero;
    private EditText etComplemento;
    private EditText etBairro;
    private EditText etCidade;
    private EditText etAniver;
    private Button btnSelecionaData;
    private EditText etDdd1;
    private EditText etTelefone;
    private EditText etDdd2;
    private EditText etTelefone2;
    private EditText etEmail;
    private EditText etObs;

    private ImageButton addContato;
    private Button btnClienteSalvar;
    private Button btnCancelar;

    private Clientes c;

    // Tipo de cliente
    private String tipoCliente;

    // ARRAY LIST RECEBEDORA DOS CONTATOS
    private ArrayList<ContatosClientes> listContatos;

    // VARIAVEL PARA RECEBER O CODIGO NOVO
    private long codigoNovo;
    private long codigoNovoContato;

    // RecyclerView para vizualizar os contatos adicionados
    private RecyclerView rvContatos;
    private ContatosAdapter adapter;

    // Banco
    private ClientesDB cliDB;
    private ContatosClientesDB contatosDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes_cadastro_fisica);

        // Recebendo os parametros
        Bundle args = getIntent().getExtras();
        tipoCliente = args.getString("tipo cliente");

        //up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Referências
        etRazaoSocial = (EditText) findViewById(R.id.et_razao_social);
        etFantasia = (EditText) findViewById(R.id.et_fantasia);
        etCpf = (EditText) findViewById(R.id.et_cpf);
        etRg = (EditText) findViewById(R.id.et_rg);
        etCep = (EditText) findViewById(R.id.et_cep);
        etEndereco = (EditText) findViewById(R.id.et_endereco);
        etNumero = (EditText) findViewById(R.id.et_numero);
        etComplemento = (EditText) findViewById(R.id.et_complemento);
        etBairro = (EditText) findViewById(R.id.et_bairro);
        etCidade = (EditText) findViewById(R.id.et_cidade);
        etAniver = (EditText) findViewById(R.id.et_aniver);
        btnSelecionaData = (Button) findViewById(R.id.btn_seleciona_data);
        etDdd1 = (EditText) findViewById(R.id.tv_ddd1_tela_exibe);
        etTelefone = (EditText) findViewById(R.id.et_telefone);
        etDdd2 = (EditText) findViewById(R.id.ddd2);
        etTelefone2 = (EditText) findViewById(R.id.et_telefone2);
        etEmail = (EditText) findViewById(R.id.et_email);
        etObs = (EditText) findViewById(R.id.et_obs);
        btnClienteSalvar = (Button) findViewById(R.id.btn_clientes_fisica_salvar);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar);
        addContato = (ImageButton) findViewById(R.id.img_plus);

        // RecyclerView para vizualizar os contatos adicionados
        rvContatos = (RecyclerView) findViewById(R.id.rv_contatos_tela_cadastro_cliente);

        // Instanciando o banco
        cliDB = new ClientesDB(this);
        contatosDB = new ContatosClientesDB(this);

        // PEGANDO UM CODIGO NOVO
        gerarCodigoNovo();

        // GARANTINDO QUE O ARRAYLIST ESTARÁ VAZIO
        listContatos = new ArrayList<>();

        // DatePicker
        btnSelecionaData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        // ADICIONA CONTATOS
        addContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContato();
            }
        });

        // ORGANIZA E SALVA OS DADOS NO BANCO #####
        btnClienteSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarNoBanco();
            }
        });

        // FINALIZA A ACTIVITY #####
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //Toda vez que a Activity receber o foco, ativamos a conexão com o BD
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //Toda vez que a Activity perder o foco, encerramos a conexão com o BD
    }

    private void limpar(){
        etRazaoSocial.setText(null);
        etFantasia.setText(null);
        etCpf.setText(null);
        etRg.setText(null);
        etCep.setText(null);
        etEndereco.setText(null);
        etNumero.setText(null);
        etComplemento.setText(null);
        etBairro.setText(null);
        etCidade.setText(null);
        etAniver.setText(null);
        etDdd1.setText(null);
        etTelefone.setText(null);
        etDdd2.setText(null);
        etTelefone2.setText(null);
        etEmail.setText(null);
        etObs.setText(null);
    }

    // PEGA UM CODIGO NOVO
    public void gerarCodigoNovo(){

        cliDB.abrirBanco();
        codigoNovo = cliDB.getCodigoNovo();
        cliDB.fecharBanco();
    }

    // ORGANIZA E INSERE OS DADOS NO BANCO #####
    public void salvarNoBanco(){

        if(etRazaoSocial.getText().toString().isEmpty() || etDdd1.getText().toString().isEmpty() ||  etFantasia.getText().toString().isEmpty() || etCpf.getText().toString().isEmpty() || etEndereco.getText().toString().isEmpty() || etBairro.getText().toString().isEmpty() || etCidade.getText().toString().isEmpty() || etTelefone.getText().toString().isEmpty()) {

            Toast.makeText(TelaClientesCadastroFisica.this, "Preencha todos os campos em vermelho.", Toast.LENGTH_LONG).show();

        }else{


            cliDB.abrirBanco();

            c = new Clientes();
            c.setCodigoCliente(codigoNovo);
            c.setRazaoSocial(etRazaoSocial.getText().toString());
            c.setFantasia(etFantasia.getText().toString());
            c.setCnpjOuCpf(etCpf.getText().toString());
            c.setInscricaoOuRg(etRg.getText().toString());
            c.setCep(etCep.getText().toString());
            c.setEndereco(etEndereco.getText().toString());
            c.setNumero(etNumero.getText().toString());
            c.setComplemento(etComplemento.getText().toString());
            c.setBairro(etBairro.getText().toString());
            c.setCidade(etCidade.getText().toString());
            c.setAniver(etAniver.getText().toString());
            c.setDdd1(etDdd1.getText().toString());
            c.setTelefone(etTelefone.getText().toString());
            c.setDdd2(etDdd2.getText().toString());
            c.setTelefone2(etTelefone2.getText().toString());
            c.setEmail(etEmail.getText().toString());
            c.setObs(etObs.getText().toString());
            c.seteJuridica(tipoCliente);


            // Enviando para metodo cadastrar
            cliDB.inserir(c);
            salvarContatos(listContatos);

            limpar();

            cliDB.fecharBanco();

            AlertDialog.Builder alert = new AlertDialog.Builder(TelaClientesCadastroFisica.this);
            alert.setMessage("Cliente cadastrado com sucesso!");
            alert.setTitle("Alerta");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
    }

    // EVENTO DO BOTÃO DE ADICIONAR CONTATO
    private void addContato(){

        contatosDB.abrirBanco();

        // PEGAR CODIGO NOVO
        if(listContatos.isEmpty()) {
            codigoNovoContato = contatosDB.getCodigoNovoContatoCliente();
            Log.d("log", "LISTA DE CONTATOS VAZIA");
        }else{
            codigoNovoContato = contatosDB.getCodigoNovoContatoCliente() + listContatos.size();
            //codigoNovoContato = codigoNovoContato + listContatos.size();
            Log.d("log", "LISTA DE CONTATOS CHEIA");
        }

        contatosDB.fecharBanco();

        AlertDialog.Builder alertContato = new AlertDialog.Builder(TelaClientesCadastroFisica.this);
        alertContato.setTitle("Adicione o contato");
        final View v = getLayoutInflater().inflate(R.layout.alert_add_contato, null);
        alertContato.setView(v);
        alertContato.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // CAMPOS DA ALERT DIALOG PARA ADICIONAR CONTATOS
                EditText etContatoContatos = (EditText) v.findViewById(R.id.et_contato_contatos);
                EditText etDdd1Contato = (EditText) v.findViewById(R.id.et_ddd1_contato);
                EditText etFone1Contato = (EditText) v.findViewById(R.id.et_fone1_contato);
                EditText etDdd2Contato = (EditText) v.findViewById(R.id.et_ddd2_contato);
                EditText etFone2Contato = (EditText) v.findViewById(R.id.et_fone2_contato);
                EditText etEmailContato = (EditText) v.findViewById(R.id.et_email_contatos);

                if(etContatoContatos.getText().toString().isEmpty()){
                    AlertDialog.Builder alertContatoVazio = new AlertDialog.Builder(getContext());
                    alertContatoVazio.setTitle("Atenção");
                    alertContatoVazio.setMessage("Contato não será salvo pois não foi informado o nome");
                    alertContatoVazio.setPositiveButton("FECHAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertContatoVazio.show();
                }else {

                    ContatosClientes con = new ContatosClientes();

                    con.setIdContato(codigoNovoContato);
                    con.setCadastro(codigoNovo);
                    con.setContatoContatos(etContatoContatos.getText().toString());
                    con.setDdd1Contato(etDdd1Contato.getText().toString());
                    con.setFone1Contato(etFone1Contato.getText().toString());
                    con.setDdd2Contato(etDdd2Contato.getText().toString());
                    con.setFone2Contato(etFone2Contato.getText().toString());
                    con.setEmailContato(etEmailContato.getText().toString());

                    listContatos.add(con);
                    refreshListContatos();
                }
            }
        });
        alertContato.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertContato.show();
    }

    public void salvarContatos(ArrayList<ContatosClientes> cc){

        Log.d("log", "SALVANDO CONTATOS");
        if(cc.isEmpty()){
            Log.d("log", "ARRAYLIST DE CONTATOS VAZIA");
        }else{
            int i = 0;
            while (i < cc.size()) {
                Log.d("log", "DENTRO DO FOR VEZ " + i);
                ContatosClientes contatosClientes = new ContatosClientes();
                contatosClientes = cc.get(i);
                contatosDB.inserir(contatosClientes);
                i++;
            }
        }
        Log.d("log", "FIM DE SALVANDO CONTATOS");
    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void refreshListContatos(){
        adapter = new ContatosAdapter(getContext(), listContatos);
        rvContatos.setAdapter(adapter);
        rvContatos.setHasFixedSize(true);
        rvContatos.setLayoutManager(new LinearLayoutManager(this));
    }

    public Context getContext(){
        return TelaClientesCadastroFisica.this;
    }
}
