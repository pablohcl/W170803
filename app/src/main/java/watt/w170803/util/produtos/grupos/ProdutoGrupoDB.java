package watt.w170803.util.produtos.grupos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import watt.w170803.util.db.BaseDB;

/**
 * Created by Pablo Henrique Correa on 14/12/2017.
 */

public class ProdutoGrupoDB {

    private SQLiteDatabase database;
    private BaseDB dbHelper;

    public ProdutoGrupoDB(Context context) {
        dbHelper = new BaseDB(context);
    }

    public void abrirBanco(){
        database = dbHelper.getWritableDatabase();
    }

    public void fecharBanco(){ database.close();}

    public void recriarTblGrupos(){
        database.execSQL(BaseDB.DROP_GRUPOS);
        database.execSQL(BaseDB.CREATE_GRUPOS);
    }

    public void inserir(ProdutoGrupo g){
        ContentValues cv = new ContentValues();

        cv.put(BaseDB.GRUPOS_ID, g.getIdGrupo());
        cv.put(BaseDB.GRUPOS_DESC, g.getDescGrupo());

        database.insert(BaseDB.TBL_GRUPOS, null, cv);
    }
}
