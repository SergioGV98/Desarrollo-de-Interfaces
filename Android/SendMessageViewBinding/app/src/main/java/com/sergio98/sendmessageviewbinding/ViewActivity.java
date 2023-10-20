package com.sergio98.sendmessageviewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sergio98.sendmessageviewbinding.databinding.ActivityViewBinding;
import com.sergio98.sendmessageviewbinding.model.data.Message;
/**
 * Clase `ViewActivity` que representa una actividad para ver un mensaje. Esta actividad muestra el remitente, el contenido del mensaje y otros detalles.
 * Extiende la clase `AppCompatActivity` de Android y proporciona funcionalidad para visualizar mensajes.
 * @author Sergio Garcia Vico
 * @version 1.0.0
 */
public class ViewActivity extends AppCompatActivity {

    private ActivityViewBinding binding;
    private Message message;
    public static final String TAG = "MessageApplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        message = bundle.getParcelable(Message.KEY);
        initialiceView();
    }

    //region METODOS DEL CICLO DE VIDA DE LA ACTIVITY
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ViewActivity -> onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ViewActivity -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ViewActivity -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ViewActivity -> onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.d(TAG, "ViewActivity -> onDestroy()");
    }
    //endregion

    /**
     * Metodo que inicializa todas las vistas o widgets de la interfaz o del Layout
     */
    private void initialiceView(){
       binding.tvUserInfo.setText(message.getSender().getName() + " " + message.getSender().getSurname() + " con DNI " + message.getSender().getDni());
       binding.tvMessage.setText(message.getContent());
    }
}