package com.example.usuario.quedapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class HorarioActivity extends ActionBarActivity {

    private ListView listView;
    private TextView diaMostrar;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_horario);

        // No permite girar la pantalla.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        diaMostrar = (TextView) this.findViewById(R.id.diaMostrar);
        diaMostrar.setText(" " + getIntent().getStringExtra("diaSeleccionado"));

        this.listView = (ListView) findViewById(R.id.listView);

        items = new ArrayList<Item>();

        for (int i = 0; i <= 23; i++) {
            // Horas
            if ((i - 1) < 9) {
                items.add(new Item("0" + i + ":00", ""));
            } else {
                items.add(new Item(i + ":00", ""));
            }
        }

        this.listView.setAdapter(new ItemAdapter(this, items));

        listView.setOnItemClickListener(new OnItemClickListener() {



				/*
				for(int i = 0; i < parent.getChildCount(); i++)
				{
				     if(i == position)
				     {
				               parent.getChildAt(i).setBackgroundColor(Color.BLUE);
				     }
				     else
				     {
				               parent.getChildAt(i).setBackgroundColor(Color.BLACK);
				     }

				 }


				LinearLayout layout=(LinearLayout) view.findViewById(R.id.list_item);
	            layout.setBackgroundColor(Color.YELLOW);






					*/


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // INTENT //
                // Iniciamos el intent.
                Intent navegar = new Intent(HorarioActivity.this, FormularioActivity.class);
                //Le pasamos el día.
                navegar.putExtra("diaMostrar", getIntent().getStringExtra("diaSeleccionado"));
                //Le pasamos el día.
                navegar.putExtra("horaMostrar", items.get(position).getTitle());
                //Lanzamos el intent.
                startActivity(navegar);
                //

            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.horario, menu);
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
