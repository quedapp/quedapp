package com.example.usuario.quedapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.quedapp.threads.ServiciosLogin;
import com.example.usuario.quedapp.threads.ServiciosRegistro;

public class PantallaRegistro extends Activity {

    private EditText edtUser;
    private EditText edtPhone;
    private EditText edtEmail;
    private Button btnLogin;
    private SharedPreferences settings;

    private static PantallaRegistro registroActivity = null;

    public static PantallaRegistro getInstance() {
        return registroActivity;}

    private void initComponents() {
        // No permite girar la pantalla.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edtUser = (EditText) findViewById(R.id.edtNameEvent);
        edtEmail = (EditText) findViewById(R.id.edtDescripcion);
        edtPhone = (EditText) findViewById(R.id.edtLocation);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);

        // iniciar componentes visuales
        initComponents();

        // Asignacion de instancia del patron singleton.
        registroActivity = this;

        OnClickListener miEscuchadorClick = new OnClickListener() {

            @Override
            public void onClick(View v) {
                //
                // Con las cuatro siguientes líneas estamos asignando al “shared
                // preferences”
                // llamando “settings” la propiedad de usar datos compartidos
                // (getSharedPrefetences).
                settings = getSharedPreferences("perfil", MODE_PRIVATE);
                // Estamos indicándole que vamos a editar su valor
                // (SharedPreferences.Editor).
                SharedPreferences.Editor editor = settings.edit();
                // Y le estamos pasando el valor que queremos asignarle en forma
                // de String (editor.putString)
                editor.putString("User", edtUser.getText().toString());
                editor.putString("Phone", edtPhone.getText().toString());
                editor.putString("Email", edtEmail.getText().toString());
                editor.commit();
                //

                // Lanzar el THREAD
                ServiciosRegistro.accionRegistro(edtUser.getText().toString(),
                        edtPhone.getText().toString(), edtEmail.getText().toString());
                // THREAD-HANDLER-RUNNABLE
				/*
				
				*/
            }
        };

        btnLogin.setOnClickListener(miEscuchadorClick);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_registro, menu);
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
