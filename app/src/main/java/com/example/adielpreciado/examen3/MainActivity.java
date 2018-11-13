package com.example.adielpreciado.examen3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
private EditText nombr,apelli,user,clav,corre,telf;
private Button btnagr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombr=(EditText)findViewById(R.id.Nombr);
        apelli=(EditText)findViewById(R.id.apelli);
        user =(EditText)findViewById(R.id.user);
        clav=(EditText)findViewById(R.id.clave);
        corre=(EditText)findViewById(R.id.Corre);
        telf=(EditText)findViewById(R.id.telef);
        btnagr=(Button)findViewById(R.id.btnagr);
    }
    public void SaveUser(){
        String nombres = nombr.getText().toString();
        String apellidos = apelli.getText().toString();
        String correo = corre.getText().toString();
        String telefono = telf.getText().toString();
        datosp(nombres,apellidos,correo,telefono);
    }
    public void datosp(String nombres, String apellidos, String correo, String telefono){
        AsyncHttpClient client = new AsyncHttpClient();
        Toast.makeText(MainActivity.this, "SI", Toast.LENGTH_SHORT).show();
        String url = "http://10.0.3.2/Teach/registro.php?";
        String parametros = "nombres=" + nombres + "&apellidos=" + apellidos + "&correo=" + correo + "&telefono=" + telefono;
        client.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resultado = new String(responseBody);
                    Toast.makeText(MainActivity.this, "Ok: " + resultado, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "piña", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Mal: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

}
