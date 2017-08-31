package watt.w170803;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Usuario on 17/08/2017.
 */

public class ClientesDB {

    private SQLiteDatabase database;
    private BaseDB dbHelper;

    public ClientesDB(Context context){
        dbHelper = new BaseDB(context);
    }

    public void abrirBanco(){
        database = dbHelper.getWritableDatabase();
    }

    public void fecharBanco(){
        dbHelper.close();
    }

    public long inserir(ClientesFisica c){
        ContentValues cv = new ContentValues();

        cv.put(BaseDB.CLIENTE_RAZAO_SOCIAL, c.getRazaoSocial());
        cv.put(BaseDB.CLIENTE_FANTASIA, c.getFantasia());
        cv.put(BaseDB.CLIENTE_RG, c.getRg());
        cv.put(BaseDB.CLIENTE_CPF, c.getCpf());
        cv.put(BaseDB.CLIENTE_CEP, c.getCep());
        cv.put(BaseDB.CLIENTE_ENDERECO, c.getEndereco());
        cv.put(BaseDB.CLIENTE_NUMERO, c.getNumero());
        cv.put(BaseDB.CLIENTE_COMPLEMENTO, c.getComplemento());
        cv.put(BaseDB.CLIENTE_BAIRRO, c.getBairro());
        cv.put(BaseDB.CLIENTE_CIDADE, c.getCidade());
        cv.put(BaseDB.CLIENTE_ESTADO, c.getEstado());
        cv.put(BaseDB.CLIENTE_CONTATO, c.getContato());
        cv.put(BaseDB.CLIENTE_ANIVER, c.getAniver());
        cv.put(BaseDB.CLIENTE_TELEFONE, c.getTelefone());
        cv.put(BaseDB.CLIENTE_TELEFONE2, c.getTelefone2());
        cv.put(BaseDB.CLIENTE_EMAIL, c.getEmail());
        cv.put(BaseDB.CLIENTE_OBS, c.getObs());


        return database.insert(BaseDB.TBL_CLIENTE, null, cv);
    }

    public long inserir(ClientesJuridica c){
        ContentValues cv = new ContentValues();

        cv.put(BaseDB.CLIENTE_RAZAO_SOCIAL, c.getRazaoSocial());
        cv.put(BaseDB.CLIENTE_FANTASIA, c.getFantasia());
        cv.put(BaseDB.CLIENTE_CNPJ, c.getCnpj());
        cv.put(BaseDB.CLIENTE_INSCRICAO_ESTADUAL, c.getInscricaoEstadual());
        cv.put(BaseDB.CLIENTE_CEP, c.getCep());
        cv.put(BaseDB.CLIENTE_ENDERECO, c.getEndereco());
        cv.put(BaseDB.CLIENTE_NUMERO, c.getNumero());
        cv.put(BaseDB.CLIENTE_COMPLEMENTO, c.getComplemento());
        cv.put(BaseDB.CLIENTE_BAIRRO, c.getBairro());
        cv.put(BaseDB.CLIENTE_CIDADE, c.getCidade());
        cv.put(BaseDB.CLIENTE_ESTADO, c.getEstado());
        cv.put(BaseDB.CLIENTE_CONTATO, c.getContato());
        cv.put(BaseDB.CLIENTE_ANIVER, c.getAniver());
        cv.put(BaseDB.CLIENTE_TELEFONE, c.getTelefone());
        cv.put(BaseDB.CLIENTE_TELEFONE2, c.getTelefone2());
        cv.put(BaseDB.CLIENTE_EMAIL, c.getEmail());
        cv.put(BaseDB.CLIENTE_OBS, c.getObs());

        return database.insert(BaseDB.TBL_CLIENTE, null, cv);
    }

    public ArrayList<Clientes> consultar(){

        ArrayList<Clientes> cliAux = new ArrayList<>();

        /* Consulta para trazer todos os dados de todas as
        *  colunas da tabela produto ordenados pelo nome */
        Cursor cursor = database.query(
                BaseDB.TBL_CLIENTE,
                BaseDB.TBL_CLIENTE_COLUNAS_CONSULTA_FISICA,
                null,
                null,
                null,
                null,
                BaseDB.CLIENTE_FANTASIA); //order by

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Clientes c = new Clientes();
            c.setCodigoCliente(cursor.getInt(0));
            c.setRazaoSocial(cursor.getString(1));
            c.setFantasia(cursor.getString(2));
            c.setCidade(cursor.getString(3));
            c.setBairro(cursor.getString(4));
            cursor.moveToNext();
            cliAux.add(c);
        }

        cursor.close();
        return cliAux;
    }
}

