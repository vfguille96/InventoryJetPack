package com.vfguille.inventoryjetpack.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vfguille.inventoryjetpack.data.dao.DependencyDao;
import com.vfguille.inventoryjetpack.data.model.Dependency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Dependency.class}, version = 2, exportSchema = false)
public abstract class InventoryDatabase extends RoomDatabase {


    public abstract DependencyDao dependencyDao();

    private static volatile InventoryDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    private static final String DATABASE_NAME = "inventory";
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Sol. GOOGLE
     * @param context
     * @return
     */
    static InventoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * Sol. Lurde
     * @param context
     */
   public static void createDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
    }

    public static InventoryDatabase getDatabase() {
        return INSTANCE;
    }
}