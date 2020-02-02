package com.george.room_project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Apotheke.class}, version = 1, exportSchema = false)
public abstract class ItemsRoomDatabase extends RoomDatabase {

    public abstract ApothekeDao apothekeDao();

    private static volatile ItemsRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ItemsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ItemsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemsRoomDatabase.class, "items_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
