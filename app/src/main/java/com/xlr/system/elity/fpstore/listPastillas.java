package com.xlr.system.elity.fpstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class listPastillas extends AppCompatActivity {

    private ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pastillas);
        getSupportActionBar().hide();
        listado = (ListView)findViewById(R.id.listView5);
        ObtDato();
    }
    //METODO PARA CONECTAR AL SERVIDOR
    public void ObtDato(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://systemxlr.96.lt/FastPrice/allPastillas.php";

        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) { //NUMERO DE CODIGO DE HTTP A SI COMO 404 NO FOUND
                    //cargar el listView del los datos de json servidos en la nuve pasando por
                    //parametro los bytes convertidos a cadena del cuerpo de la respuesta
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
