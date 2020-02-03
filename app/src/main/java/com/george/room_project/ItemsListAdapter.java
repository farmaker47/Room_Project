package com.george.room_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.george.room_project.database.Apotheke;

import java.util.List;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ItemsListViewHolder> {

    private List<Apotheke> mItems;
    Context mContext;

    public ItemsListAdapter(Context context, List<Apotheke> items) {
        mItems = items;
        mContext = context;
    }

    @NonNull
    @Override
    public ItemsListAdapter.ItemsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsListAdapter.ItemsListViewHolder holder, int position) {

        if (mItems != null) {
            Apotheke current = mItems.get(position);
            holder.textViewName.setText(current.getItem());
            holder.textViewQuantity.setText(String.valueOf(current.getQuantity()));
        } else {
            // Covers the case of data not being ready yet.
            holder.textViewName.setText(mContext.getResources().getString(R.string.no_item));
            holder.textViewName.setText(mContext.getResources().getString(R.string.zero));
        }
    }

    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }

    class ItemsListViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewQuantity;

        public ItemsListViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
        }
    }

    public void updateAdapter(){
        notifyDataSetChanged();
    }
}
