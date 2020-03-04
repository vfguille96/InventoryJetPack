package com.vfguille.inventoryjetpack.data.repository;


import com.vfguille.inventoryjetpack.data.InventoryDatabase;
import com.vfguille.inventoryjetpack.data.dao.SectionDao;
import com.vfguille.inventoryjetpack.data.model.Section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SectionRepository {
    private static SectionRepository sectionRepository;
    private static SectionDao sectionDao;

    private SectionRepository() {
        //initialize();
    }

    public static SectionRepository getInstance() {
        if (sectionRepository == null) {
            sectionDao = InventoryDatabase.getDatabase().SectionDao();
            sectionRepository = new SectionRepository();
        }
        return sectionRepository;
    }

    public List<Section> getList() {
        try {
            return InventoryDatabase.databaseWriteExecutor.submit(() -> sectionDao.getAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initialize() {
        add(new Section("Sección A", "A", "1º CFGS", "Sección de los imberbes de primero.", "https://i.kym-cdn.com/photos/images/newsfeed/001/003/188/daa.jpg"));
        add(new Section("Sección B", "B", "2º CFGS", "Sección de los imberbes sin título de bachillerato.", "https://i.kym-cdn.com/photos/images/newsfeed/001/003/188/daa.jpg"));
    }

    /**
     * Inserta una nueva sección en la BD.
     * @param section
     * @return
     */
    public boolean add(Section section) {
        InventoryDatabase.databaseWriteExecutor.execute(() -> sectionDao.insert(section));
        return true;
    }

    /**
     * Actualiza la sección de la BD.
     * @param section
     * @return
     */
    public boolean edit(Section section) {
        InventoryDatabase.databaseWriteExecutor.execute(() -> sectionDao.update(section));
        return true;
    }

    /**
     * Borra la section de la BD.
     * @param section
     * @return
     */
    public boolean delete(Section section) {
        InventoryDatabase.databaseWriteExecutor.execute(() -> sectionDao.delete(section));
        return true;
    }
}
