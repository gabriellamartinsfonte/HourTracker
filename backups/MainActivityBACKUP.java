package com.example.hourtracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityBACKUP extends AppCompatActivity {

    private Button btnCheckIn, btnCheckOut, btnReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associar os botões aos elementos do layout
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        btnReports = findViewById(R.id.btnReports);

        // Configurar os eventos de clique
        btnCheckIn.setOnClickListener(v -> {
            // Lógica para registrar entrada
            Toast.makeText(MainActivityBACKUP.this, "Entrada registrada!", Toast.LENGTH_SHORT).show();
        });

        btnCheckOut.setOnClickListener(v -> {
            // Lógica para registrar saída
            Toast.makeText(MainActivityBACKUP.this, "Saída registrada!", Toast.LENGTH_SHORT).show();
        });

        btnReports.setOnClickListener(v -> {
            // Lógica para acessar relatórios
            Toast.makeText(MainActivityBACKUP.this, "Abrindo relatórios...", Toast.LENGTH_SHORT).show();
        });
    }
}


