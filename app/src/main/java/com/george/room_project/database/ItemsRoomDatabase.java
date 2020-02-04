package com.george.room_project.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //Callback
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ApothekeDao dao = INSTANCE.apothekeDao();
                /*dao.deleteAll();*/

                Apotheke word = new Apotheke("Fruits",2);
                dao.insert(word);
            });
        }
    };
}
