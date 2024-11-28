package com.example.hourtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nome do banco de dados e versão
    private static final String DATABASE_NAME = "hour_tracker.db";
    private static final int DATABASE_VERSION = 1;

    // Definição da tabela de registros
    public static final String TABLE_RECORDS = "records";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // Comando SQL para criar a tabela
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_RECORDS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TYPE + " TEXT NOT NULL, " +
                    COLUMN_TIMESTAMP + " DATETIME NOT NULL" + // Remove DEFAULT CURRENT_TIMESTAMP
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Criar o banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    // Atualizar o banco de dados (se necedzssário)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Remover tabela antiga e criar uma nova
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }

    /**
     * Insere um registro no banco de dados com o horário do Brasil.
     *
     * @param type O tipo do registro (Entrada ou Saída).
     */
    public void insertRecord(String type) {
        // Obter o horário atual do Brasil no formato necessário
        String brazilTime = getCurrentBrazilTime();

        // Abrir conexão para escrita
        SQLiteDatabase db = this.getWritableDatabase();

        // Criar um objeto ContentValues para armazenar os valores
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, type); // Adiciona o tipo do registro
        values.put(COLUMN_TIMESTAMP, brazilTime); // Adiciona o timestamp ajustado

        // Usar método insert() para adicionar o registro
        db.insert(TABLE_RECORDS, null, values);

        // Fechar conexão com o banco de dados
        db.close();
    }

    /**
     * Recupera todos os registros do banco de dados.
     *
     * @return Um Cursor contendo todos os registros.
     */
    public Cursor getAllRecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_RECORDS,          // Nome da tabela
                null,                   // Todas as colunas
                null,                   // Sem cláusula WHERE
                null,                   // Sem argumentos
                null,                   // Sem agrupamento
                null,                   // Sem filtro de grupo
                COLUMN_TIMESTAMP + " DESC" // Ordenar por timestamp (mais recente primeiro)
        );
    }

    /**
     * Obtém o horário atual no formato DATETIME ajustado para o fuso horário do Brasil.
     *
     * @return Uma string com o horário atual formatado.
     */
    private String getCurrentBrazilTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        return sdf.format(new Date());
    }
}
