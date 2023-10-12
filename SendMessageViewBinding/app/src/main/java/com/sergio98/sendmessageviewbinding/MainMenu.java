package com.sergio98.sendmessageviewbinding;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

/**
 * Clase MainMenu
 * Muestra información "Acerca de" que incluye detalles sobre el autor de la aplicación y la versión.
 * Esta clase extiende MaterialAboutActivity, que es parte de la biblioteca Material About, utilizada
 * para crear la pantalla de "Acerca de" de la aplicación.
 * @author Sergio Garcia Vico
 * @version 1.0.0
 */
public class MainMenu extends MaterialAboutActivity {

    /**
     * Funcion que construye una lista de informacion "Acerca de" para monstrar en la aplicacion.
     * Crea dos tarjetas: Una para el autor de la aplicacion (Nombre, descripcion, icono) y  otra para la version de la aplicacion
     * ademas la seccion que contiene un icono de compartir tiene un ClickAction que te redirige a la pagina Github del autor.
     * @param context El contexto de la aplicación.
     * @return Una lista de elementos de "Acerca de" para mostrar en la interfaz de usuario.
     */
    @Override
    protected MaterialAboutList getMaterialAboutList(Context context) {
        MaterialAboutCard.Builder autor = new MaterialAboutCard.Builder();
        autor.title(R.string.autorTitle);
        autor.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.nameCard)
                .subText(R.string.descriptionCard)
                .icon(R.drawable.ic_action_person)
                .build());
        autor.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.githubCard)
                .icon(R.drawable.ic_action_shared)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://github.com/SergioGV98")))
                .build());

        MaterialAboutCard.Builder version = new MaterialAboutCard.Builder();
        version.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.versionText)
                .subText(R.string.versionInfo)
                .icon(R.drawable.ic_action_info)
                .build());

        return new MaterialAboutList.Builder()
                .addCard(autor.build())
                .addCard(version.build())
                .build();
    }

    protected CharSequence getActivityTitle() {
        return null;
    }
}


