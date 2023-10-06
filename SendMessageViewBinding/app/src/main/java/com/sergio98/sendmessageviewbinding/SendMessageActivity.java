package com.sergio98.sendmessageviewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sergio98.sendmessageviewbinding.databinding.ActivitySendMessageBinding;
import com.sergio98.sendmessageviewbinding.model.data.Message;
import com.sergio98.sendmessageviewbinding.model.data.Person;

public class SendMessageActivity extends AppCompatActivity {

    private ActivitySendMessageBinding binding;
    public static final String TAG = "MessageApplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fab.setOnClickListener(v -> sendMessage());
        Log.d(TAG, "SendMessageActivity -> onCreate()");
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
        Person persone = new Person("Sergio", "Garcia Vico", "2651N");
        Person persond = new Person("Jose Luiz", "Benitez", "2312");
        Message message = new Message(binding.edMessage.getText().toString(), persone, persond, 1);
        bundle.putParcelable(Message.KEY, message);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}