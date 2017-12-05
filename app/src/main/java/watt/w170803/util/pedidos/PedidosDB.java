package watt.w170803.util.pedidos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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
}
