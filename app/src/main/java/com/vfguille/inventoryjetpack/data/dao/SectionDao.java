package com.vfguille.inventoryjetpack.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.vfguille.inventoryjetpack.data.model.Section;

import java.util.List;

@Dao
public interface SectionDao {

    @Query("DELETE FROM SECTION")
    void deleteAll();

    @Delete
    void delete(Section section);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Section section);

    @Update
    void update(Section section);

    @Query("SELECT * FROM SECTION ORDER BY shortName")
    List<Section> getAll();

    @Query("SELECT * FROM SECTION WHERE shortName = :shortName")
    Section getSectionByShortName(String shortName);

    @Query("SELECT count(*) FROM SECTION")
    int getCount();
}
