package com.sergio98.sendmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sendmessage.R;
import com.sergio98.sendmessage.model.data.Message;
import com.sergio98.sendmessage.model.data.Person;

public class ViewActivity extends AppCompatActivity {

    private TextView tvUserInfo;
    private TextView tvMessage;
    private  Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        tvUserInfo = findViewById(R.id.tvUserInfo);
        tvMessage = findViewById(R.id.tvMessage);
        //1. Recorger directamente el Intent y el Bundle
        Bundle bundle = getIntent().getExtras();
        //EJEMPLO 1: Con Serializable
        /*
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            message = bundle.getSerializable(Message.KEY, Message.class);
        } else {
            message = (Message) bundle.getSerializable(Message.KEY);
        }*/

        //EJEMPLO 2: Con Parcelable
        message = bundle.getParcelable(Message.KEY);
        initialiceView();
    }

    /**
     * Metodo que inicializa todas las vistas o widgets de la interfaz o del Layout
     */
    private void initialiceView(){
        //Codigo que hemos estudiado en el ejercicio para inicializar componentes

        //Person person = bundle.getParcelable(Person.KEY);
        //tvUserInfo.setText(person.getName()+" "+person.getSurname()+" "+ "te manda el siguiente mensaje.");

        //tvMessage.setText(bundle.getString("message"));

        tvUserInfo.setText(message.getSender().getName() + " " + message.getSender().getSurname() + " con DNI " + message.getSender().getDni());
        tvMessage.setText(message.getContent());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Se ha iniciado la Activity ViewActivity", Toast.LENGTH_SHORT).show();
    }
}