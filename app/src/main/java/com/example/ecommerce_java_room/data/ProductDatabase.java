package com.example.ecommerce_java_room.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ecommerce_java_room.model.Product;

@Database(entities = Product.class, version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDAO getProductDao();
}
