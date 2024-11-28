package com.example.hourtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCheckIn, btnCheckOut, btnReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando o dbHelper localmente
        DatabaseHelper dbHelper = new DatabaseHelper(this);  // Variável local

        // Associar os botões aos elementos do layout
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        btnReports = findViewById(R.id.btnReports);

        // Ação ao clicar no botão "Registrar Entrada"
        btnCheckIn.setOnClickListener(v -> {
            dbHelper.insertRecord("Entrada"); // Salvar registro no banco
            Toast.makeText(MainActivity.this, "Entrada registrada!", Toast.LENGTH_SHORT).show();
        });

        // Ação ao clicar no botão "Registrar Saída"
        btnCheckOut.setOnClickListener(v -> {
            dbHelper.insertRecord("Saída"); // Salvar registro no banco
            Toast.makeText(MainActivity.this, "Saída registrada!", Toast.LENGTH_SHORT).show();
        });

        // Ação ao clicar no botão "Ver Relatórios"
        btnReports.setOnClickListener(v -> {
            // Abrir a ReportActivity
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            startActivity(intent);
        });
    }
}
