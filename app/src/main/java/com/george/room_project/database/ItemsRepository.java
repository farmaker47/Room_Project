package com.george.room_project.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemsRepository {

    private ApothekeDao mApothekeDao;
    private LiveData<List<Apotheke>> mAllItems;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ItemsRepository(Application application) {
        ItemsRoomDatabase db = ItemsRoomDatabase.getDatabase(application);
        mApothekeDao = db.apothekeDao();
        mAllItems = mApothekeDao.getItems();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Apotheke>> getAllItems() {
        return mAllItems;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Apotheke apotheke) {
        ItemsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mApothekeDao.insert(apotheke);
        });
    }
}

