package com.example.dynamicfragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements FragmentA.FragmentAListener {
    private Fragment fragmenta;
    private Fragment fragmentb;
    private static String TAG = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        fragmenta = fragmentManager.findFragmentByTag(FragmentA.TAG);
        if (fragmenta == null) {
            fragmenta = new FragmentA();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(android.R.id.content, fragmenta, FragmentA.TAG);
            fragmentTransaction.commit();
        }
        Log.d(TAG, "Activity: onCreate()");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Activity: onStart()");
    }

    @Override
    public void onFragmentAEvent(String message, int size) {
        //fragmentb = new FragmentB();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putInt("size", size);
        //Con el método setArguments se pasa la información que necesita el fragment
        //fragmentb.setArguments(bundle);
        //Se debe utilizar el patrón factoría donde la creación del objeto y el paso
        //de argumentos se ejecuten consecutivamente
        fragmentb=FragmentB.newInstance(bundle);

        // A continuación, se empieza la transacción de FragmemtA a Fragment B
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragmentb);
        //Antes de realiza el commit se debe guardar la transacción para cuando se pulse el botón
        //back se cree de nuevo el Fragment A
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Activity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Activity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Activity: onDestroy()");
    }
}
