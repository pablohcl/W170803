package watt.w170803;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.db.ClientesDB;

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
    private EditText etContato;
    private static EditText etAniver;
    private Button btnSelecionaData;
    private EditText etTelefone;
    private EditText etTelefone2;
    private EditText etEmail;
    private EditText etObs;
    private Button btnClienteSalvar;
    private Button btnCancelar;

    private Clientes c;

    // Banco
    private ClientesDB cliDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes_cadastro_fisica);

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
        etContato = (EditText) findViewById(R.id.et_contato);
        etAniver = (EditText) findViewById(R.id.et_aniver);
        btnSelecionaData = (Button) findViewById(R.id.btn_seleciona_data);
        etTelefone = (EditText) findViewById(R.id.et_telefone);
        etTelefone2 = (EditText) findViewById(R.id.et_telefone2);
        etEmail = (EditText) findViewById(R.id.et_email);
        etObs = (EditText) findViewById(R.id.et_obs);
        btnClienteSalvar = (Button) findViewById(R.id.btn_clientes_fisica_salvar);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar);

        // Instanciando o banco
        cliDB = new ClientesDB(this);

        // DatePicker
        btnSelecionaData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });//END DatePicker

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
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //Toda vez que a Activity receber o foco, ativamos a conexão com o BD
    }

    @Override
    protected void onPause() {
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
        etContato.setText(null);
        etAniver.setText(null);
        etTelefone.setText(null);
        etTelefone2.setText(null);
        etEmail.setText(null);
        etObs.setText(null);
    }

    // ORGANIZA E INSERE OS DADOS NO BANCO #####
    public void salvarNoBanco(){

        if(etRazaoSocial.getText().toString().isEmpty() ||  etFantasia.getText().toString().isEmpty() || etCpf.getText().toString().isEmpty() || etEndereco.getText().toString().isEmpty() || etBairro.getText().toString().isEmpty() || etCidade.getText().toString().isEmpty() || etContato.getText().toString().isEmpty() || etTelefone.getText().toString().isEmpty()) {

            Toast.makeText(TelaClientesCadastroFisica.this, "Preencha todos os campos em vermelho.", Toast.LENGTH_LONG).show();

        }else{

            cliDB.abrirBanco();

            int codigoNovo = cliDB.getCodigoNovo();

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
            c.setTelefone(etTelefone.getText().toString());
            c.setTelefone2(etTelefone2.getText().toString());
            c.setEmail(etEmail.getText().toString());
            c.setObs(etObs.getText().toString());
            c.seteJuridica("FALSO");


            // Enviando para metodo cadastrar
            cliDB.inserir(c);

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

    // DatePicker
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            etAniver.setText(day+"/"+(month+1)+"/"+year);
        }
    }// END DatePicker
}
