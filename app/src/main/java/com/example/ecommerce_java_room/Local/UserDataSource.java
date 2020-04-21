package com.example.ecommerce_java_room.Local;

import android.service.autofill.UserData;

import com.example.ecommerce_java_room.Database.IUserDataSource;
import com.example.ecommerce_java_room.Model.User;

import java.util.List;

import io.reactivex.Flowable;

public class UserDataSource implements IUserDataSource {

    private UserDAO userDAO;
    private static UserDataSource mInstance;


    public UserDataSource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserDataSource getInstance(UserDAO userDAO){
        if (mInstance == null) {
            mInstance = new UserDataSource(userDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<User> getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void insertUser(User... users) {
        userDAO.insertUser(users);
    }

    @Override
    public void updateUser(User... users) {
        userDAO.updateUser(users);
    }

    @Override
    public void deleteUser(User... users) {
        userDAO.deleteUser(users);
    }

    @Override
    public void deleteAllUsers() {
        userDAO.deleteAllUsers();
    }
}
