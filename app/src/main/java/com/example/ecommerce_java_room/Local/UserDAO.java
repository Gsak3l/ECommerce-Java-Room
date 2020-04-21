package com.example.ecommerce_java_room.Local;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecommerce_java_room.Model.User;

import java.util.List;

import io.reactivex.Flowable;

public interface UserDAO {
    @Query("SELECT * FROM users WHERE id=:userId")
    Flowable<User> getUserById(int userId);

    @Query("SELECT * FROM USERS")
    Flowable<List<User>> getAllUsers();

    @Insert
    void insertUser(User... users);

    @Update
    void updateUser(User... users);

    @Delete
    void deleteUser(User... users);

    @Query("DELETE FROM users")
    void deleteAllUsers();

}
