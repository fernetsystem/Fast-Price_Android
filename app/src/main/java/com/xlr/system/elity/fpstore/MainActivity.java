package com.xlr.system.elity.fpstore;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void activityLogin(View view){
        Intent i = new Intent(this,PanelControl.class);
        startActivity(i);
    }
    public void activityListProducts(View view){
        Intent i = new Intent(this,listProducts.class);
        startActivity(i);
    }
    public void activitySearch(View view){
        Intent i = new Intent(this,Search.class);
        startActivity(i);
    }
    public void activityListSustancias(View view){
        Intent i = new Intent(this,listSustancias.class);
        startActivity(i);
    }
    public void activityListPastillas(View view){
        Intent i = new Intent(this,listPastillas.class);
        startActivity(i);
    }
    public void activityListKids(View view){
        Intent i = new Intent(this,listKidsProducts.class);
        startActivity(i);
    }
    public void activityListBridge(View view){
        Intent i = new Intent(this,listBridge.class);
        startActivity(i);
    }
}
