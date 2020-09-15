package src.Services;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Para que este servicio funcione correctamente, al inicio de cada "Activity" hay que setear el Context
 * De esa forma, se evita hacer Prop Drilling del contexto en cada metodo de la app
 */
public abstract class ContextManagerService {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void setContext(@NonNull Context context) {
        ContextManagerService.context = context;
    }

    public static Context getContext() {
        return ContextManagerService.context;
    }
}
