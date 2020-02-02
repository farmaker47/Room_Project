package com.george.room_project.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.george.room_project.database.Apotheke;
import com.george.room_project.database.ItemsRepository;

import java.util.List;

public class ItemsViewModel extends AndroidViewModel {

    private ItemsRepository mRepository;

    private LiveData<List<Apotheke>> mAllItems;

    public ItemsViewModel(Application application) {
        super(application);
        mRepository = new ItemsRepository(application);
        mAllItems = mRepository.getAllItems();
    }

    LiveData<List<Apotheke>> getAllItems() {
        return mAllItems;
    }

    public void insert(Apotheke apotheke) {
        mRepository.insert(apotheke);
    }
}
