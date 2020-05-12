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

    //change the category of a product by id because i made so many mistakes when adding them
    @Query("UPDATE Product SET category = :category WHERE id = :id")
    void updateCategory(int id, String category);

    //update the availability of a product
    @Query("UPDATE Product SET quantity = quantity - :quantity WHERE id = :id")
    void updateQuantity(int id, int quantity);
}
