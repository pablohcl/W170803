package watt.w170803.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pablo Henrique Correa on 17/08/2017.
 */

public class BaseDB extends SQLiteOpenHelper {

    //Tabela cliente #################################################################
    public static final String TBL_CLIENTE = "cliente";
    public static final String CLIENTE_ID = "codigoCliente";
    public static final String CLIENTE_RAZAO_SOCIAL = "razao_social";
    public static final String CLIENTE_FANTASIA = "fantasia";
    public static final String CLIENTE_CNPJ_OU_CPF = "cnpj_ou_cpf";
    public static final String CLIENTE_INSCRICAO_OU_RG = "inscr_ou_rg";
    public static final String CLIENTE_CEP = "cep";
    public static final String CLIENTE_ENDERECO = "endereco";
    public static final String CLIENTE_NUMERO = "numero";
    public static final String CLIENTE_COMPLEMENTO = "complemento";
    public static final String CLIENTE_BAIRRO = "bairro";
    public static final String CLIENTE_CIDADE = "cidade";
    public static final String CLIENTE_ANIVER = "aniver";
    public static final String CLIENTE_DDD1 = "ddd1";
    public static final String CLIENTE_TELEFONE = "telefone";
    public static final String CLIENTE_DDD2 = "ddd2";
    public static final String CLIENTE_TELEFONE2 = "telefone2";
    public static final String CLIENTE_EMAIL = "email";
    public static final String CLIENTE_OBS = "obs";
    public static final String CLIENTE_E_JURIDICA = "e_juridica";

    /* Colunas da Tabela CLIENTE. São públicos para qualquer classe. */
    public static final String[] TBL_CLIENTE_COLUNAS = {
            BaseDB.CLIENTE_ID,
            BaseDB.CLIENTE_RAZAO_SOCIAL,
            BaseDB.CLIENTE_FANTASIA,
            BaseDB.CLIENTE_CNPJ_OU_CPF,
            BaseDB.CLIENTE_INSCRICAO_OU_RG,
            BaseDB.CLIENTE_CEP,
            BaseDB.CLIENTE_ENDERECO,
            BaseDB.CLIENTE_NUMERO,
            BaseDB.CLIENTE_COMPLEMENTO,
            BaseDB.CLIENTE_BAIRRO,
            BaseDB.CLIENTE_CIDADE,
            BaseDB.CLIENTE_ANIVER,
            BaseDB.CLIENTE_DDD1,
            BaseDB.CLIENTE_TELEFONE,
            BaseDB.CLIENTE_DDD2,
            BaseDB.CLIENTE_TELEFONE2,
            BaseDB.CLIENTE_EMAIL,
            BaseDB.CLIENTE_OBS,
            BaseDB.CLIENTE_E_JURIDICA
    };

    public static final String[] TBL_CLIENTE_COLUNAS_CONSULTA = {
            BaseDB.CLIENTE_ID,
            BaseDB.CLIENTE_RAZAO_SOCIAL,
            BaseDB.CLIENTE_FANTASIA,
            BaseDB.CLIENTE_BAIRRO,
            BaseDB.CLIENTE_CIDADE};

    public static final String[] TBL_CLIENTE_COLUNAS_SOMENTE_O_ID = {
            BaseDB.CLIENTE_ID};

    public static final String[] TBL_CLIENTE_COLUNAS_CONSULTA_TIPO = {
            BaseDB.CLIENTE_ID,
            BaseDB.CLIENTE_E_JURIDICA};

    //DDL - criação da(s) tabela(s)
    public static final String CREATE_CLIENTE =
            "create table "+TBL_CLIENTE+"(" +
                    CLIENTE_ID+" integer AUTO_INCREMENT primary key, "+
                    CLIENTE_RAZAO_SOCIAL+" text not null, "+
                    CLIENTE_FANTASIA+" text not null, "+
                    CLIENTE_CNPJ_OU_CPF+" text, "+
                    CLIENTE_INSCRICAO_OU_RG+" text,"+
                    CLIENTE_CEP+" text, "+
                    CLIENTE_ENDERECO+" text, "+
                    CLIENTE_NUMERO+" text, "+
                    CLIENTE_COMPLEMENTO+" text,"+
                    CLIENTE_BAIRRO+" text,"+
                    CLIENTE_CIDADE+" text,"+
                    CLIENTE_ANIVER+" text, "+
                    CLIENTE_DDD1+" text, "+
                    CLIENTE_TELEFONE+" text,"+
                    CLIENTE_DDD2+" text, "+
                    CLIENTE_TELEFONE2+" text,"+
                    CLIENTE_EMAIL+" text,"+
                    CLIENTE_OBS+" text,"+
                    CLIENTE_E_JURIDICA+" text not null "+
                    ");";

    //DDL - exclusão da(s) tabela(s)
    public static final String DROP_CLIENTES =
            "drop table if exists " + TBL_CLIENTE;


    // TABELA CLIENTES_CONTATOS ###################################################

    public static final String TBL_CLIENTE_CONTATOS = "cliente_contatos";
    public static final String CLIENTE_CONTATOS_ID = "id";
    public static final String CLIENTE_CONTATOS_CADASTRO = "cadastro";
    public static final String CLIENTE_CONTATOS_CONTATO = "contato";
    public static final String CLIENTE_CONTATOS_DDD1 = "ddd1";
    public static final String CLIENTE_CONTATOS_FONE1 = "fone1";
    public static final String CLIENTE_CONTATOS_DDD2 = "ddd2";
    public static final String CLIENTE_CONTATOS_FONE2 = "fone2";
    public static final String CLIENTE_CONTATOS_EMAIL = "email";

    // COLUNAS DA TABELA CLIENTE_CONTATOS
    public static final String[] TBL_CLIENTE_CONTATOS_COLUNAS = {
            BaseDB.CLIENTE_CONTATOS_ID,
            BaseDB.CLIENTE_CONTATOS_CADASTRO,
            BaseDB.CLIENTE_CONTATOS_CONTATO,
            BaseDB.CLIENTE_CONTATOS_DDD1,
            BaseDB.CLIENTE_CONTATOS_FONE1,
            BaseDB.CLIENTE_CONTATOS_DDD2,
            BaseDB.CLIENTE_CONTATOS_FONE2,
            BaseDB.CLIENTE_CONTATOS_EMAIL,
    };

    // SOMENTE A COLUNA ID PARA O METODO GETCODIGONOVO
    public static final String[] TBL_CLIENTE_CONTATOS_SOMENTE_ID = {
            BaseDB.CLIENTE_CONTATOS_ID};

    // DDL CRIAÇÃO DA TABELA
    private static final String CREATE_CLIENTE_CONTATOS =
            "create table "+TBL_CLIENTE_CONTATOS+"(" +
                    CLIENTE_CONTATOS_ID+" integer AUTO_INCREMENT primary key, "+
                    CLIENTE_CONTATOS_CADASTRO+" text not null, "+
                    CLIENTE_CONTATOS_CONTATO+" text not null, "+
                    CLIENTE_CONTATOS_DDD1+" text, "+
                    CLIENTE_CONTATOS_FONE1+" text,"+
                    CLIENTE_CONTATOS_DDD2+" text, "+
                    CLIENTE_CONTATOS_FONE2+" text, "+
                    CLIENTE_CONTATOS_EMAIL+" text "+
                    ");";

    // DDL EXCLUSÃO DA TABELA CLIENTE_CONTATOS
    private static final String DROP_CLIENTE_CONTATOS =
            "drop table if exists " + TBL_CLIENTE_CONTATOS;





    /* ############### Tabela produtos ################ */

    public static final String TBL_PRODUTOS = "produtos";
    public static final String PRODUTOS_ID = "id_produto";
    public static final String PRODUTOS_DESCRICAO = "descricao";
    public static final String PRODUTOS_UND_MEDIDA = "und_medida";
    public static final String PRODUTOS_PRECO = "preco";
    public static final String PRODUTOS_PRECO_MIN = "preco_min";
    public static final String PRODUTOS_PRECO_SUGERIDO = "preco_sugerido";

    public static final String[] TBL_PRODUTOS_COLUNAS = {
            BaseDB.PRODUTOS_ID,
            BaseDB.PRODUTOS_DESCRICAO,
            BaseDB.PRODUTOS_UND_MEDIDA,
            BaseDB.PRODUTOS_PRECO,
            BaseDB.PRODUTOS_PRECO_MIN,
            BaseDB.PRODUTOS_PRECO_SUGERIDO
    };

    //DDL - criação da(s) tabela(s)
    public static final String CREATE_PRODUTOS =
            "CREATE TABLE "+TBL_PRODUTOS+"(" +
                    PRODUTOS_ID+" integer AUTO_INCREMENT primary key, "+
                    PRODUTOS_DESCRICAO+" text not null, "+
                    PRODUTOS_UND_MEDIDA+" text not null, "+
                    PRODUTOS_PRECO+" REAL not null, "+
                    PRODUTOS_PRECO_MIN+" REAL, "+
                    PRODUTOS_PRECO_SUGERIDO+" REAL"+
                    ");";

    //DDL - exclusão da(s) tabela(s)
    public static final String DROP_PRODUTOS =
            "drop table if exists " + TBL_PRODUTOS;




    // TABELA PEDIDOS ####################
    public static final String TBL_PEDIDO = "pedido";
    public static final String PEDIDO_FINALIZADO = "finalizado";
    public static final String PEDIDO_ID = "id_pedido";
    public static final String PEDIDO_CLIENTE = "cliente_do_pedido";
    public static final String PEDIDO_COND_PGTO = "cond_pgto";
    public static final String PEDIDO_OBS = "obs_pedido";
    public static final String PEDIDO_TOTAL = "total";

    /* Colunas da Tabela PEDIDO. São públicos para qualquer classe. */
    public static final String[] TBL_PEDIDO_COLUNAS = {
            BaseDB.PEDIDO_FINALIZADO,
            BaseDB.PEDIDO_ID,
            BaseDB.PEDIDO_CLIENTE,
            BaseDB.PEDIDO_COND_PGTO,
            BaseDB.PEDIDO_OBS,
            BaseDB.PEDIDO_TOTAL
    };

    public static final String[] TBL_PEDIDO_COLUNAS_SOMENTE_ID = {
            BaseDB.PEDIDO_ID
            };

    //DDL - criação da(s) tabela(s)
    protected static final String CREATE_PEDIDO =
            "create table "+TBL_PEDIDO+"(" +
                    PEDIDO_FINALIZADO+" NUMERIC NOT NULL, "+
                    PEDIDO_ID+" integer AUTO_INCREMENT primary key, "+
                    PEDIDO_CLIENTE+" text not null, "+
                    PEDIDO_COND_PGTO+" text, "+
                    PEDIDO_OBS+" text, "+
                    PEDIDO_TOTAL+" REAL "+
                    ");";

    //DDL - exclusão da(s) tabela(s)
    protected static final String DROP_PEDIDO =
            "drop table if exists " + TBL_PEDIDO;




    //####### TABELA PEDIDO_PRODUTOS
    public static final String TBL_PEDIDO_PRODUTOS = "pedido_produtos";
    public static final String PEDIDO_PRODUTOS_ID = "id";
    public static final String PEDIDO_PRODUTOS_ID_PEDIDO = "id_pedido";
    public static final String PEDIDO_PRODUTOS_ID_PRODUTO = "id_produto";
    public static final String PEDIDO_PRODUTOS_QTD = "quantidade";
    public static final String PEDIDO_PRODUTOS_VALOR = "valor_und";
    public static final String PEDIDO_PRODUTOS_TOTAL_ITEM = "total_item";

    /* Colunas da Tabela PEDIDO_PRODUTOS. São públicos para qualquer classe. */
    public static final String[] TBL_PEDIDO_PRODUTOS_COLUNAS = {
            BaseDB.PEDIDO_PRODUTOS_ID,
            BaseDB.PEDIDO_PRODUTOS_ID_PEDIDO,
            BaseDB.PEDIDO_PRODUTOS_ID_PRODUTO,
            BaseDB.PEDIDO_PRODUTOS_QTD,
            BaseDB.PEDIDO_PRODUTOS_VALOR,
            BaseDB.PEDIDO_PRODUTOS_TOTAL_ITEM
    };

    //DDL - criação da(s) tabela(s)
    protected static final String CREATE_PEDIDO_PRODUTOS =
            "create table "+TBL_PEDIDO_PRODUTOS+"(" +
                    PEDIDO_PRODUTOS_ID+" INTEGER NOT NULL PRIMARY KEY, "+
                    PEDIDO_PRODUTOS_ID_PEDIDO+" integer NOT NULL, "+
                    PEDIDO_PRODUTOS_ID_PRODUTO+" INTEGER not null, "+
                    PEDIDO_PRODUTOS_QTD+" REAL NOT NULL, "+
                    PEDIDO_PRODUTOS_VALOR+" REAL NOT NULL, "+
                    PEDIDO_PRODUTOS_TOTAL_ITEM+" REAL NOT NULL "+
                    ");";

    //DDL - exclusão da(s) tabela(s)
    protected static final String DROP_PEDIDO_PRODUTOS =
            "drop table if exists " + TBL_PEDIDO_PRODUTOS;









    //####### TABELA GRUPOS
    public static final String TBL_GRUPOS = "grupos";
    public static final String GRUPOS_ID = "id";
    public static final String GRUPOS_DESC = "desc";

    /* Colunas da Tabela GRUPOS. São públicos para qualquer classe. */
    public static final String[] TBL_GRUPOS_COLUNAS = {
            BaseDB.GRUPOS_ID,
            BaseDB.GRUPOS_DESC
    };

    //DDL - criação da(s) tabela(s)
    public static final String CREATE_GRUPOS =
            "create table "+TBL_GRUPOS+"(" +
                    GRUPOS_ID+" INTEGER NOT NULL PRIMARY KEY, "+
                    GRUPOS_DESC+" TEXT NOT NULL "+
                    ");";

    //DDL - exclusão da(s) tabela(s)
    public static final String DROP_GRUPOS =
            "drop table if exists " + TBL_GRUPOS;







    // BANCO, NOME, VERSAO ###########################################################

    private static final String BANCO_NOME = "watt.sqlite";
    private static final int BANCO_VERSAO = 36;

    public BaseDB(Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* Criando a tabela cliente */
        db.execSQL(CREATE_CLIENTE);
        db.execSQL(CREATE_CLIENTE_CONTATOS);
        db.execSQL(CREATE_PRODUTOS);
        db.execSQL(CREATE_PEDIDO);
        db.execSQL(CREATE_PEDIDO_PRODUTOS);
        db.execSQL(CREATE_GRUPOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CLIENTES);
        db.execSQL(DROP_CLIENTE_CONTATOS);
        db.execSQL(DROP_PRODUTOS);
        db.execSQL(DROP_PEDIDO);
        db.execSQL(DROP_PEDIDO_PRODUTOS);
        db.execSQL(DROP_GRUPOS);
        onCreate(db);
    }
}
