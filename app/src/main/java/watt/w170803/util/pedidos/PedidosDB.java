package watt.w170803.util.pedidos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.db.BaseDB;

/**
 * Created by Pablo Henrique Correa on 10/11/2017.
 */

public class PedidosDB {

    private SQLiteDatabase database;
    private BaseDB dbHelper;

    public PedidosDB(Context context) {
        dbHelper = new BaseDB(context);
    }

    public void abrirBanco(){
        database = dbHelper.getWritableDatabase();
    }

    public void fecharBanco(){
        dbHelper.close();
    }

    public long getCodigoNovoPedido(){
        long result;
        abrirBanco();
        Cursor cursor = database.query(
                BaseDB.TBL_PEDIDO,
                BaseDB.TBL_PEDIDO_COLUNAS_SOMENTE_ID,
                null,
                null,
                null,
                null,
                BaseDB.PEDIDO_ID+" DESC"
        );
        cursor.moveToFirst();
        if(cursor.getCount()==0) {
            result = 1;
            cursor.close();
        }else{
            result = cursor.getInt(0)+1;
            cursor.close();
        }
        fecharBanco();
        return result;
    }

    public Pedido getPedidoAberto(){
        abrirBanco();
        Cursor cursor = database.query(
                BaseDB.TBL_PEDIDO,
                BaseDB.TBL_PEDIDO_COLUNAS,
                BaseDB.PEDIDO_FINALIZADO+" LIKE '%"+0+"%'",
                null,
                null,
                null,
                null
        );
        if(cursor.getCount() !=0) {
            cursor.moveToFirst();
            Pedido pedido = new Pedido(Long.parseLong(cursor.getString(2)), Long.parseLong(cursor.getString(1)));
            return pedido;
        }else{
            return null;
        }
    }

    public void salvarNoBanco(Pedido p){
        ContentValues cv = new ContentValues();

        cv.put(BaseDB.PEDIDO_FINALIZADO, p.getFinalizado());
        cv.put(BaseDB.PEDIDO_ID, p.getIdPedido());
        cv.put(BaseDB.PEDIDO_CLIENTE, p.getIdCliente());

        abrirBanco();
        database.insert(BaseDB.TBL_PEDIDO, null, cv);
        fecharBanco();
    }

    public String descobrirClientePeloPedido(String pedido){

        abrirBanco();

        /* Consulta para trazer todos os dados de todas as
        *  colunas da tabela produto ordenados pelo nome */
        Cursor cursor = database.query(
                BaseDB.TBL_PEDIDO,
                BaseDB.TBL_PEDIDO_COLUNAS,
                BaseDB.PEDIDO_ID+" = '"+pedido+"'",
                null,
                null,
                null,
                null); //order by

        cursor.moveToFirst();
        String cliente;
        if(cursor.getCount() == 1){
            cliente = cursor.getString(2);
        }else{
            cliente = "0";
        }
        cursor.close();
        fecharBanco();
        return cliente;
    }
}
