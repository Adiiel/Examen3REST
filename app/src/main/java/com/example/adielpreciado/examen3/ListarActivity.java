package com.example.adielpreciado.examen3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListarActivity extends AppCompatActivity {
    private Button btnatras;
    private ListView listadat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnatras = (Button) findViewById(R.id.btnatras);
        listadat = (ListView) findViewById(R.id.listadatos);
        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListarActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
