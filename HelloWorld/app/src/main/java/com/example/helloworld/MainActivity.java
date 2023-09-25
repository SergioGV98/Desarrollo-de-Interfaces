package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Aplicacion que muestra el tipico ejemplo de Hola mundo
 * @author Sergio Garcia Vico
 * @version 0.0.1
 * <ol>
 *     <li>Se ha visto como se crean los recursos en XML</li>
 *     <li>Se ha instanciado en Java un objeto TextView</li>
 *     <li>Se ha personalizado la imagen de la aplicacion</li>
 * </ol>
 */

public class MainActivity extends AppCompatActivity {

    private TextView tvMessageStart;
    private TextView tvMessageEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMessageStart = findViewById(R.id.tvMessageStart);
        tvMessageEnd = findViewById(R.id.tvMessageEnd);

        tvMessageStart.setTextColor(getColor(R.color.textColorChange));
        tvMessageEnd.setText(R.string.txtMessageOptimist);
    }

}