package com.example.hourtracker;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private ListView listViewRecords;
    private Button btnBack;
    private DatabaseHelper dbHelper;
    private ArrayList<String> recordList; // Lista de registros
    private ArrayAdapter<String> adapter; // Adaptador para ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Inicializar componentes
        listViewRecords = findViewById(R.id.listViewRecords);
        btnBack = findViewById(R.id.btnBack);
        dbHelper = new DatabaseHelper(this);
        recordList = new ArrayList<>();

        // Carregar registros
        loadRecords();

        // Configurar botão "Voltar"
        btnBack.setOnClickListener(v -> finish());
    }

    private void loadRecords() {
        // Obter todos os registros do banco de dados
        Cursor cursor = dbHelper.getAllRecords();

        if (cursor.getCount() == 0) {
            // Se não houver registros, exibir uma mensagem
            Toast.makeText(this, "Nenhum registro encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Percorrer os resultados do Cursor e adicionar à lista
        while (cursor.moveToNext()) {
            String type = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TYPE));
            String timestamp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIMESTAMP));
            recordList.add(type + " - " + timestamp);
        }
        cursor.close();

        // Configurar o adaptador para a ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recordList);
        listViewRecords.setAdapter(adapter);
    }
}
