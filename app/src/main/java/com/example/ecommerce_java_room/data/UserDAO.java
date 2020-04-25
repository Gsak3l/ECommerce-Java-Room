package com.example.ecommerce_java_room.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ecommerce_java_room.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User getUser(String email, String password);

    @Insert
    void insert(User user);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();
}
