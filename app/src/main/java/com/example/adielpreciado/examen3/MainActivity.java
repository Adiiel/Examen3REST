package com.example.adielpreciado.examen3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
private EditText nombr,apelli,usr,clav,corre,telf;
private TextView iddatos;
private Button btnagre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombr=(EditText)findViewById(R.id.Nombr);
        apelli=(EditText)findViewById(R.id.apelli);
        usr =(EditText)findViewById(R.id.user);
        clav=(EditText)findViewById(R.id.clave);
        corre=(EditText)findViewById(R.id.Corre);
        telf=(EditText)findViewById(R.id.telef);
        btnagre=(Button)findViewById(R.id.btnagr);
        iddatos=(TextView)findViewById(R.id.iddatos);
    }
    public void SaveUser(View view){
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
                    ListarIdDatos();
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
    public void CrearUser(String idd){
        AsyncHttpClient client = new AsyncHttpClient();
        Toast.makeText(getApplicationContext(),"Si",Toast.LENGTH_SHORT).show();
        String url = "http://10.0.3.2/Teach/regisuser.php?";
        String param = "user=" + usr.getText().toString() + "&clave=" + clav.getText().toString() + "&iddatosp=" + idd;
        client.post(url + param, new AsyncHttpResponseHandler() {
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
    public void cargarId(String id){

        iddatos.setText(id);
        CrearUser(id);


    }
    public void ListarIdDatos(){
        AsyncHttpClient client = new AsyncHttpClient();
        Toast.makeText(getApplicationContext(),"Si",Toast.LENGTH_SHORT).show();
        String url = "http://10.0.3.2/Teach/lastiddatos.php";
        RequestParams params = new RequestParams();
        client.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    cargarId(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public ArrayList<String> getJson(String response){
        ArrayList<String> lista = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(response);
            String cadena;
            for(int i=0; i<array.length();i++){
                cadena = array.getJSONObject(i).getString("usuario");
                lista.add(cadena);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

}
