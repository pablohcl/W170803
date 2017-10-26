package watt.w170803.util.clientes.contatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import watt.w170803.util.db.BaseDB;

/**
 * Created by Usuario on 13/10/2017.
 */

public class ContatosClientesDB {

    private SQLiteDatabase database;
    private BaseDB dbHelper;

    public ContatosClientesDB(Context context){
        dbHelper = new BaseDB(context);
    }

    public void abrirBanco(){
        database = dbHelper.getWritableDatabase();
    }

    public void fecharBanco(){
        database.close();
    }

    public long getCodigoNovoContatoCliente(){
        long result;
        abrirBanco();
        Cursor cursor = database.query(
                BaseDB.TBL_CLIENTE_CONTATOS,
                BaseDB.TBL_CLIENTE_CONTATOS_SOMENTE_ID,
                null,
                null,
                null,
                null,
                BaseDB.CLIENTE_CONTATOS_ID+" DESC"
        );
        cursor.moveToFirst();
        if(cursor.getCount()==0) {
            result = 0;
            cursor.close();
        }else{
            result = cursor.getInt(0)+1;
            cursor.close();
        }
        fecharBanco();
        return result;
    }

    public void inserir(ContatosClientes c){
        ContentValues cv = new ContentValues();

        cv.put(BaseDB.CLIENTE_CONTATOS_ID, c.getIdContato());
        cv.put(BaseDB.CLIENTE_CONTATOS_CADASTRO, c.getCadastro());
        cv.put(BaseDB.CLIENTE_CONTATOS_CONTATO, c.getContatoContatos());
        cv.put(BaseDB.CLIENTE_CONTATOS_DDD1, c.getDdd1Contato());
        cv.put(BaseDB.CLIENTE_CONTATOS_FONE1, c.getFone1Contato());
        cv.put(BaseDB.CLIENTE_CONTATOS_DDD2, c.getDdd2Contato());
        cv.put(BaseDB.CLIENTE_CONTATOS_FONE2, c.getFone2Contato());
        cv.put(BaseDB.CLIENTE_CONTATOS_EMAIL, c.getEmailContato());

        abrirBanco();
        database.insert(BaseDB.TBL_CLIENTE_CONTATOS, null, cv);
        fecharBanco();
    }

    public ArrayList<ContatosClientes> consultar(long codigo){

        ArrayList<ContatosClientes> conAux = new ArrayList<>();
        abrirBanco();

        /* Consulta para trazer todos os dados de todas as
        *  colunas da tabela produto ordenados pelo nome */
        Cursor cursor = database.query(
                BaseDB.TBL_CLIENTE_CONTATOS,
                BaseDB.TBL_CLIENTE_CONTATOS_COLUNAS,
                BaseDB.CLIENTE_CONTATOS_CADASTRO+" = '"+codigo+"'",
                null,
                null,
                null,
                BaseDB.CLIENTE_CONTATOS_CONTATO); //order by

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ContatosClientes c = new ContatosClientes();
            c.setContatoContatos(cursor.getString(2));
            c.setDdd1Contato(cursor.getString(3));
            c.setFone1Contato(cursor.getString(4));
            c.setDdd2Contato(cursor.getString(5));
            c.setFone2Contato(cursor.getString(6));
            c.setEmailContato(cursor.getString(7));
            cursor.moveToNext();
            conAux.add(c);
        }

        cursor.close();
        fecharBanco();
        return conAux;
    }
}
