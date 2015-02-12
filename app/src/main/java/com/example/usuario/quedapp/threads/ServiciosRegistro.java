package com.example.usuario.quedapp.threads;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.example.usuario.quedapp.PantallaRegistro;
import com.example.usuario.quedapp.PantallaCargaActivity;
import com.example.usuario.quedapp.beans.Usuario;
import com.example.usuario.quedapp.datos.QuedAPPDatos;
import com.example.usuario.quedapp.utils.Post;

public class ServiciosRegistro {
    private static String messageUser = "";
    private final static Handler manejador = new Handler();
    private static JSONArray datos;
    private static boolean registro = false;

    // Seccion critica que se ejecuta en 2º plano
    // Importante

    public static void accionRegistro(final String user, final String phone, final String email) {
        Toast.makeText(PantallaRegistro.getInstance().getBaseContext(),
                "Registrando....", Toast.LENGTH_LONG).show();
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

                    Post post = new Post();
                    datos = post
                            .getServerData(parametros,
                                    "http://quedapp.x10host.com/quedapp/Registro.php");

                    // FIN

                    // Fin petición HTTP
                    messageUser = "Todo correcto";
                    registro = true;
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

                if (registro = true) {
                    Toast.makeText(
                            PantallaRegistro.getInstance().getBaseContext(),
                            "Registro completado. ", Toast.LENGTH_SHORT)
                            .show();

                    Toast.makeText(
                            PantallaRegistro.getInstance().getBaseContext(),
                            messageUser, Toast.LENGTH_SHORT).show();

                    Intent navegar = new Intent(PantallaRegistro.getInstance(), PantallaCargaActivity.class);

                    PantallaRegistro.getInstance().startActivity(navegar);

                } else {
                    Toast.makeText(
                            PantallaRegistro.getInstance().getBaseContext(),
                            "Registro fallido. ", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
            }
        }
    };
}