package com.sergio98.sendmessageviewbinding;

import android.app.Application;
import android.util.Log;

/**
 * Clase MessageApplication que representa la aplicacion principal.
 * Extiende la clase Application de Android y proporciona métodos para la inicialización y finalización de la aplicación.
 * @author Sergio Garcia Vico
 * @version 1.0.0
 */
public class MessageApplication extends Application {

    public static final String TAG = "MessageApplication";

    /**
     * Este método se llama cuando se inicia la aplicación. Aquí se pueden realizar tareas de inicialización
     * o configuración necesarias para toda la aplicación.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MessageApplication -> onCreate()");
    }

    /**
     * Este método se llama cuando se inicia la aplicación. Aquí se pueden realizar tareas de inicialización
     * o configuración necesarias para toda la aplicación.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "MessageApplication -> onTerminate()");
    }
}
