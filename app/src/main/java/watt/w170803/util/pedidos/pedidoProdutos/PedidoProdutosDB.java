package watt.w170803.util.pedidos.pedidoProdutos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import watt.w170803.util.db.BaseDB;

/**
 * Created by Pablo Henrique Correa on 13/12/2017.
 */

public class PedidoProdutosDB {

    private SQLiteDatabase database;
    private BaseDB dbHelper;

    public PedidoProdutosDB(Context context){dbHelper = new BaseDB(context);}

    public void abrirBanco(){database = dbHelper.getWritableDatabase();}

    public void fecharBanco(){dbHelper.close();}


}
