package com.example.usuario.quedapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FormularioActivity extends ActionBarActivity {

    private TextView diaMostrado;
    private TextView horaMostrada;
    private Button btnFormulario;

    private void initComponents() {
        // No permite girar la pantalla.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnFormulario = (Button) findViewById(R.id.btnFormulario);
        diaMostrado = (TextView) this.findViewById(R.id.diaMostrado);
        horaMostrada = (TextView) this.findViewById(R.id.horaMostrada);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        // No permite girar la pantalla.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // iniciar componentes visuales
        initComponents();

        // Mostramos el dia seleccionado en el calendario.
        diaMostrado.setText(" " + getIntent().getStringExtra("diaMostrar"));

        // Mostramos el dia seleccionado en el calendario.
        horaMostrada.setText(" " + getIntent().getStringExtra("horaMostrar"));

        OnClickListener miEscuchadorClick = new OnClickListener() {

            @Override
            public void onClick(View v) {

                // INTENT //
                // Iniciamos el intent.
                Intent navegar = new Intent(FormularioActivity.this, ContactosActivity.class);
                // Lanzamos el intent.
                startActivity(navegar);
                //

            }
        };

        btnFormulario.setOnClickListener(miEscuchadorClick);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.formulario, menu);
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
