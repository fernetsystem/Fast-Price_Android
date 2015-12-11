package com.xlr.system.elity.fpstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Search extends AppCompatActivity {

    private EditText et_word;
    private ListView listado;
    String word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        listado = (ListView)findViewById(R.id.listView6);
        et_word = (EditText)findViewById(R.id.wordSearch);

    }
    public void realizaBusqueda(View view){
        word = et_word.getText().toString();
        AsyncHttpClient myClient = new AsyncHttpClient();
        String url="http://systemxlr.96.lt/FastPrice/search_pro.php";
        RequestParams requestParams = new RequestParams();
        requestParams.add("word", word);

        RequestHandle post = myClient.post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) { //CODIGO DIFERENTE DEL 404 QUE ENCUENTRA LA PAGINA
                    cargarLista(objDatosJSON(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    //METODO PARA DIBUJAR EL JSON EN EL LISTview
    public void cargarLista(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listado.setAdapter(adapter);
    }
    //OBTENER FORMATO DE JSON Y PASARLOS A UNA LISTA ENLAZADA
    public ArrayList<String> objDatosJSON(String response){
        ArrayList<String> lista = new ArrayList<String>(); //CREAR UNA LISTA ENLAZADA
        try{
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            //ITERAR HASTA QUE TENGA TODOS LOS CAMPOS DE JSON
            //PONER LOS CAMPOS DE FORMATO DE JSON
            for(int i=0;i<jsonArray.length();i++){
                texto="\nPRODUCTO: " + jsonArray.getJSONObject(i).getString("nombre_pro")+ "\nDESCRIPCION: "+
                        jsonArray.getJSONObject(i).getString("descripcion")+ "\nCONTENIDO: "+
                        jsonArray.getJSONObject(i).getString("contenido")+ "\nEXISTENCIA: "+
                        jsonArray.getJSONObject(i).getString("existencia")+ "\nPRECIO: "+
                        jsonArray.getJSONObject(i).getString("precio")+ "\nSUCURSAL: "+
                        jsonArray.getJSONObject(i).getString("nombre_suc")+ "\nDIRECCION:"+
                        jsonArray.getJSONObject(i).getString("direccion")+ "\n";
                lista.add(texto);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }


}
