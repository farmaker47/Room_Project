package com.george.room_project.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table")
public class Apotheke {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "item")
    private String mItem;
    @ColumnInfo(name = "quantity")
    private int mQuantity;

    public Apotheke(String item, int quantity) {
        mItem = item;
        mQuantity = quantity;
    }

    public String getItem() {
        return mItem;
    }

    public int getQuantity(){
        return mQuantity;
    }
}
