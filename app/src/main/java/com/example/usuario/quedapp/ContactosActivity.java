package com.example.usuario.quedapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactosActivity extends ListActivity {
    private ArrayList<String> miLista = new ArrayList<String>();
    private Button btnCrear;

    /** Called when the activity is first created. */
    private void initComponents() {
        // No permite girar la pantalla.
        btnCrear = (Button) findViewById(R.id.btnCrear);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        initComponents();
        setTitle("Choose a phone");

        // Query: contacts with phone shorted by name
        Cursor mCursor = getContentResolver().query(
                Data.CONTENT_URI,
                new String[] { Data._ID, Data.DISPLAY_NAME, Phone.NUMBER,
                        Phone.TYPE },
                Data.MIMETYPE + "='" + Phone.CONTENT_ITEM_TYPE + "' AND "
                        + Phone.NUMBER + " IS NOT NULL", null,
                Data.DISPLAY_NAME + " ASC");

        startManagingCursor(mCursor);

        // Setup the list
        ListAdapter adapter = new SimpleCursorAdapter(this, // context
                android.R.layout.simple_list_item_2, // Layout for the rows
                mCursor, // cursor
                new String[] { Data.DISPLAY_NAME, Phone.NUMBER }, // cursor
                // fields
                new int[] { android.R.id.text1, android.R.id.text2 } // view
                // fields
        );
        setListAdapter(adapter);
        View.OnClickListener miEscuchadorClick = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // INTENT //
                // Iniciamos el intent.
                Intent navegar = new Intent(ContactosActivity.this, ChatActivity.class);
                // Lanzamos el intent.
                startActivity(navegar);
                //

            }
        };

        btnCrear.setOnClickListener(miEscuchadorClick);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //Intent result = new Intent();

        // Get the data
        Cursor c = (Cursor) getListAdapter().getItem(position);
        int colIdx = c.getColumnIndex(Phone.NUMBER);
        String phone = c.getString(colIdx);
        Toast.makeText(getBaseContext(), "Añdido: " + phone , Toast.LENGTH_LONG).show();
        miLista.add("Número: " + phone);
        // Save the phone to return it to the caller
        //result.putExtra("phone", phone);
        //setResult(Activity.RESULT_OK, result);

        // Close this activity (return to caller)
        //finish();
    }


}
