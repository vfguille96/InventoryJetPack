package com.vfguille.inventoryjetpack;

import android.app.Application;

import com.vfguille.inventoryjetpack.data.InventoryDatabase;

public class InventoryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InventoryDatabase.createDatabase(this);
    }
}
