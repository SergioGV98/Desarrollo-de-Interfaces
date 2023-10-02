package com.sergio98.sendmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sendmessage.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SendMessageActivity extends AppCompatActivity {

    //OPCION 1: Creo la instancia del listener o delegado
    //private SendMessageOnClickListener onClickListener;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        fab = findViewById(R.id.fab); // Asigno a la instancia de un boton flotante 'fab' el id del boton flotante
        //onClickListener = new SendMessageOnClickListener(); OPCION 1

        //OPCION 1
        //fab.setOnClickListener(onClickListener); // Asigno al boton fab el listener.

        //OPCION 2: Clase anonima
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButton fab = (FloatingActionButton) v;
                Toast.makeText(SendMessageActivity.this, "Se crea el texto en la clase anonima. Button: " +v.getId(), Toast.LENGTH_SHORT).show();
            }
        });*/

        //OPCION 3: Expresion Lambda

        fab.setOnClickListener(v -> { // Si necesito mas de una linea abro llaves
            FloatingActionButton fab = (FloatingActionButton) v;
            Toast.makeText(this, "Se crea el texto con una expresion lambda. Button: " + fab.getId(), Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Opcion 1: Se crea una clase que implementa la interfaz View.OnClickListener
     */
/*
    class SendMessageOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(SendMessageActivity.this, "Se ha pulsado sobre el botón enviar.", Toast.LENGTH_SHORT).show();
        }
    }
*/

}