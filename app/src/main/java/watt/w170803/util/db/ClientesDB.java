package watt.w170803.util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import watt.w170803.util.clientes.Clientes;

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

    public long inserir(Clientes c){
        ContentValues cv = new ContentValues();

        cv.put(BaseDB.CLIENTE_ID, c.getCodigoCliente());
        cv.put(BaseDB.CLIENTE_RAZAO_SOCIAL, c.getRazaoSocial());
        cv.put(BaseDB.CLIENTE_FANTASIA, c.getFantasia());
        cv.put(BaseDB.CLIENTE_CNPJ_OU_CPF, c.getCnpjOuCpf());
        cv.put(BaseDB.CLIENTE_INSCRICAO_OU_RG, c.getInscricaoOuRg());
        cv.put(BaseDB.CLIENTE_CEP, c.getCep());
        cv.put(BaseDB.CLIENTE_ENDERECO, c.getEndereco());
        cv.put(BaseDB.CLIENTE_NUMERO, c.getNumero());
        cv.put(BaseDB.CLIENTE_COMPLEMENTO, c.getComplemento());
        cv.put(BaseDB.CLIENTE_BAIRRO, c.getBairro());
        cv.put(BaseDB.CLIENTE_CIDADE, c.getCidade());
        cv.put(BaseDB.CLIENTE_ANIVER, c.getAniver());
        cv.put(BaseDB.CLIENTE_TELEFONE, c.getTelefone());
        cv.put(BaseDB.CLIENTE_TELEFONE2, c.getTelefone2());
        cv.put(BaseDB.CLIENTE_EMAIL, c.getEmail());
        cv.put(BaseDB.CLIENTE_OBS, c.getObs());
        cv.put(BaseDB.CLIENTE_E_JURIDICA, c.geteJuridica());



        return database.insert(BaseDB.TBL_CLIENTE, null, cv);
    }

    public ArrayList<Clientes> consultar(){

        ArrayList<Clientes> cliAux = new ArrayList<>();

        /* Consulta para trazer todos os dados de todas as
        *  colunas da tabela produto ordenados pelo nome */
        Cursor cursor = database.query(
                BaseDB.TBL_CLIENTE,
                BaseDB.TBL_CLIENTE_COLUNAS_CONSULTA,
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
            c.setBairro(cursor.getString(3));
            c.setCidade(cursor.getString(4));
            cursor.moveToNext();
            cliAux.add(c);
        }

        cursor.close();
        return cliAux;
    }

    public ArrayList<Clientes> consultar(String[] campo, String busca){
        ArrayList<Clientes> resultado = new ArrayList<>();

        Cursor cursor = database.query(
                BaseDB.TBL_CLIENTE,
                BaseDB.TBL_CLIENTE_COLUNAS_CONSULTA,
                campo[0]+" LIKE '%"+busca+"%' OR "+campo[1]+" LIKE '%"+busca+"%'",
                null,
                null,
                null,
                campo[0]
        );
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Clientes c = new Clientes();
            c.setCodigoCliente(cursor.getInt(0));
            c.setRazaoSocial(cursor.getString(1));
            c.setFantasia(cursor.getString(2));
            c.setBairro(cursor.getString(3));
            c.setCidade(cursor.getString(4));
            cursor.moveToNext();
            resultado.add(c);
        }
        cursor.close();
        return resultado;
    }

    public Clientes consultarTotal(long codigo){

        Clientes c = new Clientes();

        /* Consulta para trazer todos os dados de todas as
        *  colunas da tabela produto ordenados pelo nome */
        Cursor cursor = database.query(
                BaseDB.TBL_CLIENTE,
                BaseDB.TBL_CLIENTE_COLUNAS,
                BaseDB.CLIENTE_ID+" = '"+codigo+"'",
                null,
                null,
                null,
                null); //order by

        cursor.moveToFirst();
        if(cursor.getCount() == 1){
            c.setCodigoCliente(cursor.getInt(0));
            c.setRazaoSocial(cursor.getString(1));
            c.setFantasia(cursor.getString(2));
            c.setCnpjOuCpf(cursor.getString(3));
            c.setInscricaoOuRg(cursor.getString(4));
            c.setCep(cursor.getString(5));
            c.setEndereco(cursor.getString(6));
            c.setNumero(cursor.getString(7));
            c.setComplemento(cursor.getString(8));
            c.setBairro(cursor.getString(9));
            c.setCidade(cursor.getString(10));
            c.setAniver(cursor.getString(11));
            c.setTelefone(cursor.getString(12));
            c.setTelefone2(cursor.getString(13));
            c.setEmail(cursor.getString(14));
            c.setObs(cursor.getString(15));
            c.seteJuridica(cursor.getString(16));

        }

        cursor.close();
        return c;
    }

    public int getCodigoNovo(){
        int result;
        Cursor cursor = database.query(
                BaseDB.TBL_CLIENTE,
                BaseDB.TBL_CLIENTE_COLUNAS_SOMENTE_O_ID,
                null,
                null,
                null,
                null,
                BaseDB.CLIENTE_ID+" DESC"
        );
        cursor.moveToFirst();
        if(cursor.getCount()==0) {
            result = 0;
        }else{
            result = cursor.getInt(0)+1;
        }
        return result;
    }

    public String consultaTipoCliente(long codigo){
        String result;
        Cursor cursor = database.query(
                BaseDB.TBL_CLIENTE,
                BaseDB.TBL_CLIENTE_COLUNAS_CONSULTA_TIPO,
                BaseDB.CLIENTE_ID+" = '"+codigo+"'",
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        if(cursor.getCount()==1){
            result = cursor.getString(1);
        }else{
            result = "ERRO NA CONSULTA SQL consultaTipoCliente()";
        }
        return result;
    }
}

