package com.example.adielpreciado.examen3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private class CargarDatos extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try{
                return downloadUrl(urls[0]);

            }catch (IOException e){
                return "Unable to retrieve web page. URL may be invalid";
            }
        }
        @Override
        protected void onPostExecute(String result){
            Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
        }
    }
    private class ConsultarDatos extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            try{
                return downloadUrl(urls[0]);

            }catch (IOException e){
                return "Unable to retrieve web page. URL may be invalid";
            }
        }
        @Override
        protected void onPostExecute(String result){
            Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
        }
    }
    private String downloadUrl(String myurl) throws IOException{
        InputStream is = null;
        int len = 500;
        try{
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /*milisegundos*/);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("Respuesta", "The response is: " + response);
            is=conn.getInputStream();

            String contentAsString =  readIt(is, len);
            return contentAsString;
        }finally {
            if (is != null){
                is.close();
            }
        }


    }
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream,"UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
    public void SaveUser(View view){
        String nombres = nombr.getText().toString();
        String apellidos = apelli.getText().toString();
        String correo = corre.getText().toString();
        String telefono = telf.getText().toString();
        datosp(nombres,apellidos,correo,telefono);
        //new CargarDatos().execute("http://url/Teach/registro.php?nombres="+nombres+"&apellidos="+apellidos+"&");
    }
    public void datosp(String nombres, String apellidos, String correo, String telefono){
        AsyncHttpClient client = new AsyncHttpClient();
        Toast.makeText(MainActivity.this, "SI", Toast.LENGTH_SHORT).show();
        String url = "http://10.0.3.2/Teach/registro.php?";
        String parametros = "nombres=" + nombres + "&apellidos=" + apellidos + "&correo=" + correo + "&telefono=" + telefono;
        client.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 300) {
                    String resultado = new String(responseBody);
                    Toast.makeText(MainActivity.this, "Ok: " + resultado, Toast.LENGTH_SHORT).show();
                   // ListarIdDatos();
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
        String url = "http://10.0.2.2/Teach/regisuser.php?";
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
        String url = "http://10.0.2.2/Teach/lastiddatos.php";
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
