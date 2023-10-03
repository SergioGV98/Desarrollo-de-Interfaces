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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        tvUserInfo = findViewById(R.id.tvUserInfo);
        tvMessage = findViewById(R.id.tvMessage);
        //1. Recorger directamente el Intent y el Bundle
        Bundle bundle = getIntent().getExtras();
        //Person person = bundle.getParcelable(Person.KEY);
        //tvUserInfo.setText(person.getName()+" "+person.getSurname()+" "+ "te manda el siguiente mensaje.");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            Message message = bundle.getSerializable(Message.KEY, Message.class);
        }
        tvMessage.setText(bundle.getString("message"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Se ha iniciado la Activity ViewActivity", Toast.LENGTH_SHORT).show();
    }
}