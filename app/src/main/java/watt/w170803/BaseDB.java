package watt.w170803;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario on 17/08/2017.
 */

public class BaseDB extends SQLiteOpenHelper {

    //Tabela
    public static final String TBL_CLIENTE = "cliente";
    public static final String CLIENTE_ID = "codigoCliente";
    public static final String CLIENTE_TIPO = "tipo";
    public static final String CLIENTE_RAZAO_SOCIAL = "razao_social";
    public static final String CLIENTE_FANTASIA = "fantasia";
    public static final String CLIENTE_CNPJ = "cnpj";
    public static final String CLIENTE_INSCRICAO_ESTADUAL = "inscr";
    public static final String CLIENTE_RG = "rg";
    public static final String CLIENTE_CPF = "cpf";
    public static final String CLIENTE_CEP = "cep";
    public static final String CLIENTE_ENDERECO = "endereco";
    public static final String CLIENTE_NUMERO = "numero";
    public static final String CLIENTE_COMPLEMENTO = "complemento";
    public static final String CLIENTE_BAIRRO = "bairro";
    public static final String CLIENTE_CIDADE = "cidade";
    public static final String CLIENTE_CONTATO = "contato";
    public static final String CLIENTE_ANIVER = "aniver";
    public static final String CLIENTE_TELEFONE = "telefone";
    public static final String CLIENTE_TELEFONE2 = "telefone2";
    public static final String CLIENTE_EMAIL = "email";
    public static final String CLIENTE_OBS = "obs";


    /* Colunas da Tabela PRODUTO. São públicos para qualquer classe. */
    public static final String[] TBL_CLIENTE_COLUNAS = {
            BaseDB.CLIENTE_ID,
            BaseDB.CLIENTE_TIPO,
            BaseDB.CLIENTE_RAZAO_SOCIAL,
            BaseDB.CLIENTE_FANTASIA,
            BaseDB.CLIENTE_CNPJ,
            BaseDB.CLIENTE_INSCRICAO_ESTADUAL,
            BaseDB.CLIENTE_RG,
            BaseDB.CLIENTE_CPF,
            BaseDB.CLIENTE_CEP,
            BaseDB.CLIENTE_ENDERECO,
            BaseDB.CLIENTE_NUMERO,
            BaseDB.CLIENTE_COMPLEMENTO,
            BaseDB.CLIENTE_BAIRRO,
            BaseDB.CLIENTE_CIDADE,
            BaseDB.CLIENTE_CONTATO,
            BaseDB.CLIENTE_ANIVER,
            BaseDB.CLIENTE_TELEFONE,
            BaseDB.CLIENTE_TELEFONE2,
            BaseDB.CLIENTE_EMAIL,
            BaseDB.CLIENTE_OBS};

    public static final String[] TBL_CLIENTE_COLUNAS_JURIDICA = {
            BaseDB.CLIENTE_ID,
            BaseDB.CLIENTE_TIPO,
            BaseDB.CLIENTE_RAZAO_SOCIAL,
            BaseDB.CLIENTE_FANTASIA,
            BaseDB.CLIENTE_CNPJ,
            BaseDB.CLIENTE_INSCRICAO_ESTADUAL,
            BaseDB.CLIENTE_CEP,
            BaseDB.CLIENTE_ENDERECO,
            BaseDB.CLIENTE_NUMERO,
            BaseDB.CLIENTE_COMPLEMENTO,
            BaseDB.CLIENTE_BAIRRO,
            BaseDB.CLIENTE_CIDADE,
            BaseDB.CLIENTE_CONTATO,
            BaseDB.CLIENTE_ANIVER,
            BaseDB.CLIENTE_TELEFONE,
            BaseDB.CLIENTE_TELEFONE2,
            BaseDB.CLIENTE_EMAIL,
            BaseDB.CLIENTE_OBS};

    public static final String[] TBL_CLIENTE_COLUNAS_FISICA = {
            BaseDB.CLIENTE_ID,
            BaseDB.CLIENTE_TIPO,
            BaseDB.CLIENTE_RAZAO_SOCIAL,
            BaseDB.CLIENTE_FANTASIA,
            BaseDB.CLIENTE_RG,
            BaseDB.CLIENTE_CPF,
            BaseDB.CLIENTE_CEP,
            BaseDB.CLIENTE_ENDERECO,
            BaseDB.CLIENTE_NUMERO,
            BaseDB.CLIENTE_COMPLEMENTO,
            BaseDB.CLIENTE_BAIRRO,
            BaseDB.CLIENTE_CIDADE,
            BaseDB.CLIENTE_CONTATO,
            BaseDB.CLIENTE_ANIVER,
            BaseDB.CLIENTE_TELEFONE,
            BaseDB.CLIENTE_TELEFONE2,
            BaseDB.CLIENTE_EMAIL,
            BaseDB.CLIENTE_OBS};

    public static final String[] TBL_CLIENTE_COLUNAS_CONSULTA_FISICA = {
            BaseDB.CLIENTE_ID,
            BaseDB.CLIENTE_RAZAO_SOCIAL,
            BaseDB.CLIENTE_FANTASIA,
            BaseDB.CLIENTE_BAIRRO,
            BaseDB.CLIENTE_CIDADE};

    public static final String[] TBL_CLIENTE_COLUNAS_SOMENTE_O_ID = {
            BaseDB.CLIENTE_ID};

    public static final String[] TBL_CLIENTE_COLUNAS_CONSULTA_TIPO = {
            BaseDB.CLIENTE_ID,
            BaseDB.CLIENTE_TIPO};

    //Banco + name + version
    public static final String BANCO_NOME = "estoque.sqlite";
    public static final int BANCO_VERSAO = 10;

    //DDL - criação da(s) tabela(s)
    public static final String CREATE_CLIENTE =
            "create table "+TBL_CLIENTE+"(" +
                    CLIENTE_ID+" integer AUTO_INCREMENT primary key, "+
                    CLIENTE_TIPO+" text not null, "+
                    CLIENTE_RAZAO_SOCIAL+" text not null, "+
                    CLIENTE_FANTASIA+" text not null, "+
                    CLIENTE_CNPJ+" text, "+
                    CLIENTE_INSCRICAO_ESTADUAL+" text,"+
                    CLIENTE_CPF+" text, "+
                    CLIENTE_RG+" text, "+
                    CLIENTE_CEP+" text, "+
                    CLIENTE_ENDERECO+" text, "+
                    CLIENTE_NUMERO+" text, "+
                    CLIENTE_COMPLEMENTO+" text,"+
                    CLIENTE_BAIRRO+" text,"+
                    CLIENTE_CIDADE+" text,"+
                    CLIENTE_CONTATO+" text,"+
                    CLIENTE_ANIVER+" text, "+
                    CLIENTE_TELEFONE+" text,"+
                    CLIENTE_TELEFONE2+" text,"+
                    CLIENTE_EMAIL+" text,"+
                    CLIENTE_OBS+" text"+
            ");";

    //DDL - exclusão da(s) tabela(s)
    public static final String DROP_CLIENTES =
            "drop table if exists " + TBL_CLIENTE;

    public BaseDB(Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* Criando a tabela cliente */
        db.execSQL(CREATE_CLIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CLIENTES);
        onCreate(db);
    }
}
