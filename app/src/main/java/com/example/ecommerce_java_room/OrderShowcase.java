package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.ecommerce_java_room.data.OrderDAO;
import com.example.ecommerce_java_room.data.OrderDatabase;
import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.model.Order;
import com.example.ecommerce_java_room.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderShowcase extends AppCompatActivity {
    //array lists for all the fields
    private ArrayList<String> orderImages = new ArrayList<>();
    private ArrayList<Integer> orderIds = new ArrayList<>();
    private ArrayList<Integer> orderProductIds = new ArrayList<>();
    private ArrayList<Integer> orderProductQuantity = new ArrayList<>();
    private ArrayList<Double> orderTotalPrice = new ArrayList<>();
    //minimizing the decimal length of a double
    private DecimalFormat df = new DecimalFormat();
    //database stuff
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    //others
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_showcase);
        userId = Integer.parseInt(getIntent().getExtras().get("userId").toString());
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        orderDAO = Room.databaseBuilder(this, OrderDatabase.class, "Order")
                .allowMainThreadQueries().build().getOrderDao();
        initOrderInfo();
    }

    //giving the order info to the arrays
    private void initOrderInfo() {
        List<Order> userOrders;
        if (userId == -1) {
            userOrders = orderDAO.getAllOrders();
        } else {
            userOrders = orderDAO.getUserOrders(userId);
        }
        for (int i = 0; i < userOrders.size(); i++) {
            //adding the image of the product to the orderImages array
            Product product = productDAO.getProduct(userOrders.get(i).getProductId());
            orderImages.add(product.getImageURL());
            //doing the same for the rest
            orderIds.add(userOrders.get(i).getId());
            orderProductIds.add(userOrders.get(i).getProductId());
            orderProductQuantity.add(userOrders.get(i).getOrderProductQuantity());
            orderTotalPrice.add(userOrders.get(i).getOrderPrice());
        }
        initOrderRecyclerView();
    }

    //setting the recycler view
    private void initOrderRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.order_recycler_view);
        RecyclerViewAdapterOrder recyclerViewAdapterOrder = new RecyclerViewAdapterOrder(this,
                orderImages, orderIds, orderProductIds, orderProductQuantity, orderTotalPrice);
        recyclerView.setAdapter(recyclerViewAdapterOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
