package com.example.ecommerce_java_room.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecommerce_java_room.Model.User;

import java.util.List;

import io.reactivex.Flowable;

public interface IUserDataSource {
    Flowable<User> getUserById(int userId);

    Flowable<List<User>> getAllUsers();

    void insertUser(User... users);

    void updateUser(User... users);

    void deleteUser(User... users);

    void deleteAllUsers();
}
