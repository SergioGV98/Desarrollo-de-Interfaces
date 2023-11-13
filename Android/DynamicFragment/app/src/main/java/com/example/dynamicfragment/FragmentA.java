package com.example.dynamicfragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

/**
 * Created by lourdes on 16/11/17.
 */

public class FragmentA extends Fragment {


    private FragmentAListener mCallBack;
    private EditText edtMessage;
    private Button btSize;
    private SeekBar skbSize;
    public static String TAG="fragmenta";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"FragmentA: onCreate()");
    }

    /**
     * Se define la interfaz que servirá de contrato entre el Fragment y la Activity
     **/

    public interface FragmentAListener {
        void onFragmentAEvent(String message, int size);
    }


    /**
     * Este método sólo funciona desde la API 23 en adelante. Si se ejecuta en una API inferior NO DA ERROR; PERO NO FUNCIONA LA COMUNICACIÓN Activity-Fragment
     *
     * @param
     * @Override public void onAttach(Context context) {
     * super.onAttach(context);
     * }
     */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (FragmentAListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragmemtAListener");
        }
        Log.d(TAG,"FragmentA: onAttach()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmenta, container, false);

        edtMessage = view.findViewById(R.id.edtMessage);
        btSize = view.findViewById(R.id.btSize);
        skbSize = view.findViewById(R.id.skbSize);
        Log.d(TAG,"FragmentA: onCreateView()");
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onFragmentAEvent(edtMessage.getText().toString(), skbSize.getProgress());
            }
        });
        Log.d(TAG,"FragmentA: onViewCreated()");

    }
    @Override
    public void onPause() {
        super.onPause();
        mCallBack = null;
        Log.d(TAG,"FragmentA: onPause()");
    }
    @Override
    public void onStop() {
        super.onStop();
        mCallBack = null;
        Log.d(TAG,"FragmentA: onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallBack = null;
        Log.d(TAG,"FragmentA: onDestroy()");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
        Log.d(TAG,"FragmentA: onDetach()");
    }
}
