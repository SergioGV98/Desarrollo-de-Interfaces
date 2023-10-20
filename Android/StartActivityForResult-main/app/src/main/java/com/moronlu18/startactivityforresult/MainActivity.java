package com.moronlu18.startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Actividad que muestra pide al usuario dos números para que una segunda actividad realice la suma
 * de ambos números y se devuelva a la primera actividad
 * USO DE API de startActivityForResult() y onActivityResult()
 */
public class MainActivity extends AppCompatActivity {
    private EditText edtX, edtY;
    private TextView txvSum;
    private static final int SUM_REQUEST=301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtX =findViewById(R.id.edtX);
        edtY =findViewById(R.id.edtY);
        txvSum=findViewById(R.id.txvSum);
    }

    /**
     * Este método pide el resultado de la suma. Se pide al usuario que obligatoriamente debe introducir dos valores numéricos.
     * El código del método es "spaghetti" para facilitar la compresión y posteriormente se realizará la refactorización
     * modificando el código para dejarlo correcto.
     * @param view
     */
    public void getResult(View view){
        //Se comprueba que se ha introducido los dos números para poder realizar la suma
        if (!TextUtils.isEmpty(edtX.getText()) && !TextUtils.isEmpty(edtY.getText())) {
            if(TextUtils.isDigitsOnly(edtX.getText())&& TextUtils.isDigitsOnly(edtY.getText())) {
                Bundle bundle = new Bundle();
                bundle.putInt("x", Integer.parseInt(edtX.getText().toString()));
                bundle.putInt("y", Integer.parseInt(edtY.getText().toString()));
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, SUM_REQUEST);
            }
            else {
                showMessage(getResources().getString(R.string.onlydigit));
            }
        }else
        {
           showMessage(getResources().getString(R.string.setnumber));
        }

    }

    /**
     * Método que muestra un mensaje en la aplicación
     */
    private void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SUM_REQUEST:
            {
                if(resultCode == RESULT_OK){
                    txvSum.setText(data.getIntExtra("sum",-1)+"");
                }
            }
        }
    }
}
