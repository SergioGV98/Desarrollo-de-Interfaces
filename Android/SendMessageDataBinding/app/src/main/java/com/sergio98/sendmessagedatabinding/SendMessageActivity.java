package com.sergio98.sendmessagedatabinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.sergio98.sendmessagedatabinding.databinding.ActivitySendMessageBinding;
import com.sergio98.sendmessagedatabinding.model.data.Message;
import com.sergio98.sendmessagedatabinding.model.data.Person;

/**
 * Clase `SendMessageActivity` que representa la actividad principal de la aplicación para enviar mensajes.
 * Extiende la clase `AppCompatActivity` de Android y proporciona funcionalidad para enviar mensajes a través de otra actividad.
 * @author Sergio Garcia Vico
 * @version 1.0.0
 */
public class SendMessageActivity extends AppCompatActivity {

    private ActivitySendMessageBinding binding;
    public static final String TAG = "MessageApplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Person persone = new Person("Sergio", "Garcia Vico", "2651N");
        Person persond = new Person("Jose Luiz", "Benitez", "2312");
        Message message = new Message();
        message.setSender(persone);
        message.setReceiver(persond);
        binding.setMessage(message);
        binding.fab.setOnClickListener(v -> sendMessage());
        binding.setLifecycleOwner(this);
        Log.d(TAG, "SendMessageActivity -> onCreate()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ic_aboutas:
                Intent intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //region METODOS DEL CICLO DE VIDA DE LA ACTIVITY
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SendMessageActivity -> onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SendMessageActivity -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SendMessageActivity -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SendMessageActivity -> onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.d(TAG, "SendMessageActivity -> onDestroy()");
    }
    //endregion

    /*
     * Metodo que construye el mensaje y lo envia a otra Activity
     */
    public void sendMessage(){
        Intent intent = new Intent(this, ViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Message.KEY, binding.getMessage());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}