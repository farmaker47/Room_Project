package com.george.room_project.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ApothekeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Apotheke apotheke);

    @Query("DELETE FROM items_table")
    void deleteAll();

    @Query("SELECT * from items_table")
    List<Apotheke> getItems();

}
