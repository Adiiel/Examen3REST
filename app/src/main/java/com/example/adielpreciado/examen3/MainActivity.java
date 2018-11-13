package com.example.adielpreciado.examen3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private EditText nombr,apelli,user,clav,correo,telf;
private Button btnagr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombr=(EditText)findViewById(R.id.Nombr);
        apelli=(EditText)findViewById(R.id.apelli);
        user =(EditText)findViewById(R.id.user);
        clav=(EditText)findViewById(R.id.clave);
        correo=(EditText)findViewById(R.id.Corre);
        telf=(EditText)findViewById(R.id.telef);
        btnagr=(Button)findViewById(R.id.btnagr);
    }
}
