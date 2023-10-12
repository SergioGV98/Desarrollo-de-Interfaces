package com.sergio98.sendmessageviewbinding;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public class MainMenu extends MaterialAboutActivity {
    @Override
    @NonNull
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        MaterialAboutCard.Builder autor = new MaterialAboutCard.Builder();
        autor.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.nameCard)
                .subText(R.string.descriptionCard)
                .icon(R.drawable.ic_action_person)
                .build());
        autor.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.githubCard)
                .icon(R.drawable.ic_action_shared)
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
    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return null;
    }

}


