package watt.w170803.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Usuario on 08/09/2017.
 */

public class Atualizador {

    private SQLiteDatabase database;
    private BaseDB dbHelper;

    public Atualizador(Context context){
        dbHelper = new BaseDB(context);
    }

    public void atualizaProdutos(InputStream inStream){

    }
}
