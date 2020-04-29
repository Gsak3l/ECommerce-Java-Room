package com.example.ecommerce_java_room.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ecommerce_java_room.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    //get product by id
    @Query("SELECT * FROM Product WHERE id = :id")
    Product getProduct(int id);

    //insert a new product
    @Insert
    void insert(Product product);

    //select product by category
    @Query("SELECT * FROM Product WHERE category = :category ORDER BY random()")
    List<Product> getProductsByCategory(String category);

    //select all products in the db
    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    //delete product by id
    @Query("DELETE FROM Product WHERE id = :id")
    void deleteProduct(int id);

    //delete all products
    @Query("DELETE FROM Product")
    void deleteAllProducts();
}