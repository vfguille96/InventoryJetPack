package com.vfguille.inventoryjetpack.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.vfguille.inventoryjetpack.data.model.Dependency;

import java.util.List;

@Dao
public interface DependencyDao {
    @Query("DELETE FROM DEPENDENCY")
    void deleteAll();

    @Delete
    void delete(Dependency dependency);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dependency dependency);

    @Update
    void update(Dependency dependency);

    @Query("SELECT * FROM DEPENDENCY ORDER BY shortName")
    List<Dependency> getAll();

    @Query("SELECT * FROM DEPENDENCY WHERE shortName = :shortName")
    Dependency getDependencyByShortName(String shortName);

    @Query("SELECT count(*) FROM DEPENDENCY")
    int getCount();

    @Query("SELECT shortName FROM DEPENDENCY ORDER BY shortName")
    List<String> getAllShortNames();
}
