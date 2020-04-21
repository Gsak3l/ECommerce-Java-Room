package com.example.ecommerce_java_room.Local;

import android.content.Context;
import android.service.autofill.UserData;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ecommerce_java_room.Model.User;
import com.example.ecommerce_java_room.R;

import static com.example.ecommerce_java_room.Local.UserDatabase.DATABASE_VERSION;


//Creating Room Database for Users
@Database(entities = User.class, version = DATABASE_VERSION)
public abstract class UserDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME= "EMDT-Database-Room";

    public abstract UserDAO userDAO();
    private static UserDatabase mInstance;
    public static UserDatabase getInstance(Context context) {
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context, UserDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
