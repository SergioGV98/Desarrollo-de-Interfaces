package com.example.dynamicfragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by lourdes on 16/11/17.
 */

public class FragmentB extends Fragment {


    private TextView txvMessage;
    private String message;
    private int size;
    public static String TAG = "fragmentb";

    /**
     * PATRÓN FACTORY, que es una simplificación del patrón BUILDER
     *
     * @param bundle
     * @return
     */
    public static Fragment newInstance(Bundle bundle) {
        FragmentB fragmentB = new FragmentB();
        if (bundle != null) {
            fragmentB.setArguments(bundle);
        }
        return fragmentB;
    }

    /**
     * ATENCIÓN: Para que el estado dinámico de un fragment sea permanente ante un cambio de
     * configuración usar setRetainInstance(). Los objetos txMessage si guardan el estado pero no así el
     * valor en int size
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
        Log.d(TAG, "FragmentB: onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentb, container, false);
        if (rootView != null) {
            txvMessage = rootView.findViewById(R.id.txvMessage);
        }
        Log.d(TAG, "FragmentB: onCreateView()");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (savedInstanceState == null) //No hay cambio de configuración. Se ejecuta por primera vez
        {
            if (bundle != null) { //Si hay parámetros se asignan
                message = bundle.getString("message");
                size = bundle.getInt("size");
            }
        }
        changeTextAndSize(message, size);
        Log.d(TAG, "FragmentB: onViewCreated()");
    }

    public void changeTextAndSize(String message, int size) {
        txvMessage.setText(message);
        txvMessage.setTextSize(size);

    }

   /* @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", txvMessage.getText().toString());
        outState.putFloat("size", txvMessage.getTextSize() / getResources().getDisplayMetrics().scaledDensity); //Convert px to sp
        Log.d(TAG,"FragmentB: onSaveInstanceState()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            txvMessage.setTextSize(savedInstanceState.getFloat("size"));
            txvMessage.setText(savedInstanceState.getString("message"));
            Log.d(TAG,"FragmentB: onViewStateRestored()");
        }
    }*/

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"FragmentB: onPause()");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"FragmentB: onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"FragmentB: onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"FragmentB: onDestroy()");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"FragmentB: onDetach()");
    }


}
