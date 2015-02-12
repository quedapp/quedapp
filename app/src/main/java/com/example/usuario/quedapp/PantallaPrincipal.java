package com.example.usuario.quedapp;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.usuario.quedapp.threads.ServiciosLogin;

public class PantallaPrincipal extends Activity {

    private SharedPreferences settings;

    private static PantallaPrincipal mainActivity = null;

    public static PantallaPrincipal getInstance() {
        return mainActivity;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);





        // Comprobamos si ya se habia registrado.
        settings = getSharedPreferences("perfil", MODE_PRIVATE);
        String user = settings.getString("User", "default");
        String phone = settings.getString("Phone", "default");
        String email = settings.getString("Email", "default");




		if (phone != "default") {
			// Lanzar el THREAD

            Toast.makeText(getApplicationContext(), "Bienvenido " + user, Toast.LENGTH_SHORT).show();
            Intent navegar = new Intent(PantallaPrincipal.this, PantallaCargaActivity.class);
            startActivity(navegar);

			// THREAD-HANDLER-RUNNABLE
		}else{

        Intent navegar = new Intent(PantallaPrincipal.this, PantallaRegistro.class);
        startActivity(navegar);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
