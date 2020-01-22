package com.vfguille.inventoryjetpack.data.repository;

import android.os.AsyncTask;

import com.vfguille.inventoryjetpack.data.InventoryDatabase;
import com.vfguille.inventoryjetpack.data.dao.DependencyDao;
import com.vfguille.inventoryjetpack.data.model.Dependency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DependencyRepository {
    private static DependencyRepository dependencyRepository;
    private static DependencyDao dependencyDao;

    // Constructor privado porque sólo existe un objeto Repository.
    private DependencyRepository() {
        initializeList();
    }

    public static DependencyRepository getInstance() {
        if (dependencyRepository == null) {
            dependencyDao = InventoryDatabase.getDatabase().dependencyDao();
            dependencyRepository = new DependencyRepository();
        }
        return dependencyRepository;
    }

    public List<Dependency> getList() {
        return new AsyncTask<Void, Void, List<Dependency>>() {
            @Override
            protected List<Dependency> doInBackground(Void... voids) {
                return dependencyDao.getAll();
            }
        }.doInBackground();
    }

    private void initializeList(){
        insert(new Dependency("2º Desarrollo de Aplicaciones Multiplataforma", "2º CFGS", "Los más mejores", "AAA", "gf"));
        insert(new Dependency("1º Desarrollo de Aplicaciones Multiplataforma", "1º CFGS", "Los menos mejores", "AAA", "gf"));
    }

    public boolean insert(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute( () -> dependencyDao.insert(dependency));
        return true;
    }

    public boolean update(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute( () -> dependencyDao.update(dependency));
        return true;
    }

    public boolean delete(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute( () -> dependencyDao.delete(dependency));
        return true;
    }

    public int getCount() {
        try {
            return new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... voids) {
                    return dependencyDao.getCount();
                }
            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }


}