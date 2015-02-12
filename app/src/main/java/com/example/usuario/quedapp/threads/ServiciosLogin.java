package com.example.usuario.quedapp.threads;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;


import com.example.usuario.quedapp.PantallaPrincipal;

import com.example.usuario.quedapp.PantallaCargaActivity;
import com.example.usuario.quedapp.beans.Usuario;
import com.example.usuario.quedapp.datos.QuedAPPDatos;
import com.example.usuario.quedapp.utils.Post;

public class ServiciosLogin {
    private static String messageUser = "";
    private final static Handler manejador = new Handler();
    private static JSONArray datos;

    // Seccion critica que se ejecuta en 2º plano
    // Importante

    public static void accionLogin(final String user, final String phone, final String email) {


        // Vamos a preparar el hilo - Threads!!!
        Thread threadLogin = new Thread() {
            public void run() {
                try {
                    // PETICIÓN CLIENTE . SERVIDOR
                    // http:// Recurso del fichero PHP y parámetros para
                    // funcionar
                    ArrayList<String> parametros = new ArrayList<String>();
                    parametros.add("User"); // Clave
                    parametros.add(user); // Valor
                    parametros.add("Phone"); // Clave
                    parametros.add(phone); // Valor
                    parametros.add("Email"); // Clave
                    parametros.add(email); // Valor
                    // Clase que permite conectar por POST
                    //http://stoplaya.site40.net/examen/indexExamen.php
                    //http://stoplaya.site40.net/GrouponFSV/loginGroupon.php
                    Post post = new Post();
                    datos = post
                            .getServerData(parametros,
                                    "http://quedapp.netai.net/quedapp/Login.php");

                    // FIN

                    // Fin petición HTTP
                    messageUser = "Todo correcto";
                } catch (Exception e) {
                    messageUser = "Error al conectar con el Servidor";
                }
                // En este momento tengo que modificar al hilo principal, lo que
                // ha sucedido
                // Handler!!!
                manejador.post(procesoNotificacion);
            }
        };
        threadLogin.start();
    }

    private final static Runnable procesoNotificacion = new Runnable() {
        @Override
        public void run() {
            // Es el unico que puede ejecutar tareas sobre el hilo principal
            try {
                if (datos != null && datos.length() > 0) {
                    JSONObject json_data = datos.getJSONObject(0);
                    int numRegistrados = json_data.getInt("ID_USER");
                    if (numRegistrados > 0) {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(json_data.getInt("ID_USER"));
                        usuario.setUser(json_data.getString("NAME_USER"));
                        usuario.setPhone(json_data.getString("NUMBER_USER"));
                        usuario.setPhone(json_data.getString("EMAIL_USER"));

                        //EN EL EXAMEN CON GETTERS Y SETTERS
                        QuedAPPDatos.usuario=usuario;

                        Toast.makeText(
                                PantallaPrincipal.getInstance().getBaseContext(),
                                messageUser, Toast.LENGTH_SHORT).show();

                        Intent navegar = new Intent(PantallaPrincipal.getInstance(), PantallaCargaActivity.class);

                        PantallaPrincipal.getInstance().startActivity(navegar);


                    }
                } else {
                    Toast.makeText(
                            PantallaPrincipal.getInstance().getBaseContext(),
                            "Usuario incorrecto. ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(
                            PantallaPrincipal.getInstance().getBaseContext(),
                            messageUser, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
            }
        }
    };
}