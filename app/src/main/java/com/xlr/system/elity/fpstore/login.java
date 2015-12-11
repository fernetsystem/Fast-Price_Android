package com.xlr.system.elity.fpstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {

    private EditText email,pass;
    private TextView tv_account;
    String myEmail,myPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.et_emaill);
        pass  = (EditText)findViewById(R.id.et_passl);
        tv_account = (TextView)findViewById(R.id.tv_newAccountl);
    }

    public void realizaLogin(View view){
        myEmail = email.getText().toString();
        myPass = pass.getText().toString();
        AsyncHttpClient myClient = new AsyncHttpClient();
        String url="http://systemxlr.96.lt/FastPrice/login.php";
        RequestParams requestParams = new RequestParams();
        requestParams.add("email", myEmail);
        requestParams.add("pass", myPass);

        RequestHandle post = myClient.post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta;
                if (statusCode == 200) { //CODIGO DIFERENTE DEL 404 QUE ENCUENTRA LA PAGINA
                    try{
                        JSONObject o = new JSONObject(new String(responseBody));
                        //tye foreach
                        //OBETENGO LA RESPUESTA DEL SERVIDOR
                        respuesta = o.getString("aceptado"); //OBJETO JSON 'ACEPTADO' CON VALOR OK O NO
                        if(respuesta.equals("OK")) { // SI ES SI IMPRIMIR CARACTERSITICAS
                            tv_account.setText("CORRECTO ");
                        } else {
                            tv_account.setText("ACCESO DENEGADO");
                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}