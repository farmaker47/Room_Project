package com.george.room_project;

import android.os.Bundle;

import com.george.room_project.database.Apotheke;
import com.george.room_project.viewModel.ItemsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ItemsListAdapter mItemsListAdapter;
    private List<Apotheke> mItems;
    private ItemsViewModel mItemsViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //RecyclerViewAdapter
        mRecyclerView = findViewById(R.id.recyclerview);
        mItemsListAdapter = new ItemsListAdapter(this, mItems);
        mRecyclerView.setAdapter(mItemsListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemsViewModel = new ViewModelProvider(this).get(ItemsViewModel.class);

        mItemsViewModel.getAllItems().observe(this, new Observer<List<Apotheke>>() {
            @Override
            public void onChanged(List<Apotheke> apothekes) {
                // Update the cached copy of the words in the adapter.
                mItemsListAdapter = new ItemsListAdapter(MainActivity.this, apothekes);
                mRecyclerView.setAdapter(mItemsListAdapter);
                mItemsListAdapter.updateAdapter();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
