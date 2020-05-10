package com.example.ecommerce_java_room.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ecommerce_java_room.model.Order;

@Database(entities = Order.class, version = 1, exportSchema = false)
public abstract class OrderDatabase extends RoomDatabase {
    public abstract OrderDAO getOrderDao();
}
