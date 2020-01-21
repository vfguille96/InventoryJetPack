package com.vfguille.inventoryjetpack.data.repository;


import com.vfguille.inventoryjetpack.data.model.Section;

import java.util.ArrayList;
import java.util.Iterator;

public class SectionRepository {
    private static SectionRepository sectionRepository;
    private ArrayList<Section> list;

    private SectionRepository(){
        list = new ArrayList<>();
        initialize();
    }

    public static SectionRepository getInstance(){
        if (sectionRepository == null)
            sectionRepository = new SectionRepository();
        return sectionRepository;
    }

    public ArrayList<Section> getList(){
        return this.list;
    }

    private void initialize() {
        list.add(new Section("Sección A", "A", "1CFGS", "Sección de los imberbes de primero.", "https://i.kym-cdn.com/photos/images/newsfeed/001/003/188/daa.jpg"));
        list.add(new Section("Sección B", "B", "2CFGS", "Los más aplicados.", "https://i.kym-cdn.com/photos/images/newsfeed/001/003/188/daa.jpg"));
        list.add(new Section("Sección C", "C", "1CFGM", "Dando ofimática con Schema.", "https://i.kym-cdn.com/photos/images/newsfeed/001/003/188/daa.jpg"));
    }

    public boolean add(Section Section) {
        if (!list.contains(Section)){
            list.add(Section);
            return true;
        }else
            return false;
    }

    public boolean edit(Section section) {
        try {
            for (Section sectionIt : list) {
                if (sectionIt.getShortName().equals(section.getShortName())) {
                    sectionIt.setName(section.getName());
                    sectionIt.setDescription(section.getDescription());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Section section){
        Iterator<Section> sectionIterator = list.iterator();
        while (sectionIterator.hasNext()){
            if (sectionIterator.next().equals(section)) {
                sectionIterator.remove();
                return true;
            }
        }
        return false;
    }
}
