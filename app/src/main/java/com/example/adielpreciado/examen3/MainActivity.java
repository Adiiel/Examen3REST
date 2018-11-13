package com.example.adielpreciado.examen3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private EditText user;
private Button btnagr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user =(EditText)findViewById(R.id.nom);
        btnagr=(Button)findViewById(R.id.btnagr);
    }
}
