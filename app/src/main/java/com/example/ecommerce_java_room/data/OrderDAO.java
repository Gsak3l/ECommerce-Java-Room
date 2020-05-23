package com.example.ecommerce_java_room.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ecommerce_java_room.model.Order;

import java.util.List;

@Dao
public interface OrderDAO {

    //get order by id
    @Query("SELECT * FROM [Order] WHERE id = :id")
    //reserved word ftw....
    Order getOrderById(int id); //i wasted 45 minutes on nothing :D

    @Insert
    void insert(Order order);

    //get all orders for a specific user
    @Query("SELECT * FROM [Order] WHERE userId = :userId")
    List<Order> getUserOrders(int userId);

    //update order by id (idk when should i use this one)
    @Query("UPDATE [Order] SET orderProductQuantity = :orderProductQuantity")
    void updateOrderQuantity(int orderProductQuantity);

    //getting all orders
    @Query("SELECT * FROM [Order]")
    List<Order> getAllOrders();

    //deleting a specific order
    @Query("DELETE FROM [Order] WHERE id= :id")
    void deleteOrder(int id);

    //delete all orders
    @Query("DELETE FROM [Order]")
    void deleteAllOrders();

    //get orders by product
    @Query("SELECT * FROM [Order] WHERE productId = :productId")
    List<Order> getProductOrders(int productId);
}
