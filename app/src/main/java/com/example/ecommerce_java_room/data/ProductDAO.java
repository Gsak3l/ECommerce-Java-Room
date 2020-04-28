package com.example.ecommerce_java_room.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ecommerce_java_room.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM Product WHERE id = :id")
    Product getProduct(int id);

    @Insert
    void insert(Product product);

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();
}
