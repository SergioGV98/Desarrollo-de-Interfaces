package com.sergio98.sendmessageviewbinding;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.sergio98.sendmessageviewbinding.databinding.ActivitySendMessageBinding;

public class MainMenu extends MaterialAboutActivity {
    @Override
    @NonNull
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        MaterialAboutCard.Builder cardBuilder = new MaterialAboutCard.Builder();
        cardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("Material About Library")
                .icon(R.mipmap.ic_launcher)
                .build());

        return new MaterialAboutList.Builder()
                .addCard(cardBuilder.build())
                .build();
    }
    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return null;
    }

}


