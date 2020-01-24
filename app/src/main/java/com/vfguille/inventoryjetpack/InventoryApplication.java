package com.vfguille.inventoryjetpack;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.transition.Visibility;

import com.vfguille.inventoryjetpack.data.InventoryDatabase;

public class InventoryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        InventoryDatabase.createDatabase(this);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "canal";
            String description = "notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(getString(R.string.canal), name, importance);
            channel.setDescription(description);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Visibility.MODE_IN);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
