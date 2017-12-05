package watt.w170803.util.produtos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import watt.w170803.util.db.BaseDB;
import watt.w170803.util.produtos.Produto;

/**
 * Created by Usuario on 08/09/2017.
 */

public class ProdutoDB {

    private SQLiteDatabase database;
    private BaseDB dbHelper;

    public ProdutoDB(Context context){
        dbHelper = new BaseDB(context);
    }

    public void abrirBanco(){
        database = dbHelper.getWritableDatabase();
    }

    public void recriarTblProdutos(){
        database.execSQL(BaseDB.DROP_PRODUTOS);
        database.execSQL(BaseDB.CREATE_PRODUTOS);
    }

    public void fecharBanco(){
        dbHelper.close();
    }

    public long inserir(Produto p){
        ContentValues cv = new ContentValues();

        cv.put(BaseDB.PRODUTOS_ID, p.getIdProduto());
        cv.put(BaseDB.PRODUTOS_DESCRICAO, p.getDescricao());
        cv.put(BaseDB.PRODUTOS_UND_MEDIDA, p.getUndMedida());
        cv.put(BaseDB.PRODUTOS_PRECO, p.getPreco());
        cv.put(BaseDB.PRODUTOS_PRECO_MIN, p.getPrecoMin());
        cv.put(BaseDB.PRODUTOS_PRECO_SUGERIDO, p.getPrecoSugerido());

        return database.insert(BaseDB.TBL_PRODUTOS, null, cv);
    }

    public ArrayList<Produto> consultar(){

        ArrayList<Produto> proAux = new ArrayList<>();

        /* Consulta para trazer todos os dados de todas as
        *  colunas da tabela produto ordenados pelo nome */
        Cursor cursor = database.query(
                BaseDB.TBL_PRODUTOS,
                BaseDB.TBL_PRODUTOS_COLUNAS,
                null,
                null,
                null,
                null,
                BaseDB.PRODUTOS_DESCRICAO); //order by

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Produto p = new Produto();
            p.setIdProduto(cursor.getLong(0));
            p.setDescricao(cursor.getString(1));
            p.setUndMedida(cursor.getString(2));
            p.setPreco(cursor.getDouble(3));
            p.setPrecoMin(cursor.getDouble(4));
            p.setPrecoSugerido(cursor.getDouble(5));
            cursor.moveToNext();
            proAux.add(p);
        }

        cursor.close();
        return proAux;
    }

    public Produto consultarTotal(long codigo){

        Produto p = new Produto();
        abrirBanco();

        /* Consulta para trazer todos os dados de todas as
        *  colunas da tabela produto ordenados pelo nome */
        Cursor cursor = database.query(
                BaseDB.TBL_PRODUTOS,
                BaseDB.TBL_PRODUTOS_COLUNAS,
                BaseDB.PRODUTOS_ID+" = '"+codigo+"'",
                null,
                null,
                null,
                null); //order by

        cursor.moveToFirst();
        if(cursor.getCount() == 1){
            p.setIdProduto(cursor.getInt(0));
            p.setDescricao(cursor.getString(1));
            p.setUndMedida(cursor.getString(2));
            p.setPreco(cursor.getDouble(3));
            p.setPrecoMin(cursor.getDouble(4));
            p.setPrecoSugerido(cursor.getDouble(5));

        }

        cursor.close();
        fecharBanco();
        return p;
    }
}
