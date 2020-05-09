package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.model.Product;

import java.util.ArrayList;
import java.util.List;

public class productShowcaseAdmin extends AppCompatActivity {

    //lists used to fill the recycler from our database
    private ArrayList<String> productTitles = new ArrayList<>();
    private ArrayList<String> productImageUrl = new ArrayList<>();
    private ArrayList<Integer> productQuantity = new ArrayList<>();
    private ArrayList<Double> productPrice = new ArrayList<>();
    private ArrayList<Integer> productCode = new ArrayList<>();

    private com.google.android.material.floatingactionbutton.FloatingActionButton addNewProduct;
    private com.google.android.material.floatingactionbutton.FloatingActionButton basket;


    //database stuff
    private ProductDAO productDAO;
    private ProductDatabase productDatabase;
    List<Product> products = new ArrayList<>();

    //other stuff
    boolean flag = true;
    private String categoryName;
    private String userType;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_showcase_admin);
        //getting all the values sent from MainMenu
        categoryName = getIntent().getExtras().get("category").toString();
        userType = getIntent().getExtras().get("userType").toString();
        userId = Integer.parseInt(getIntent().getExtras().get("userId").toString());
        //making text for the category name
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        //showing images
        initImageBitmaps(userType);
        addNewProduct = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.admin_add_new_product_button);
        basket = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.user_basket_button);
        //showing different buttons for user and admin
        if (userType.equals("user")) {
            basket.setVisibility(View.VISIBLE);
            addNewProduct.setVisibility(View.INVISIBLE);
        }
        //showing different buttons for user and admin
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(productShowcaseAdmin.this, AdminAddNewProductActivity.class);
                startActivity(login);
            }
        });
    }

    private void initImageBitmaps(String userType) {
        //giving all the values like price quantity etc for all the products
        products = productDAO.getProductsByCategory(categoryName);
        for (int i = 0; i < products.size(); i++) {
            productImageUrl.add(products.get(i).getImageURL());
            productTitles.add(products.get(i).getTitle());
            productQuantity.add(products.get(i).getQuantity());
            productPrice.add(products.get(i).getPrice());
            productCode.add(products.get(i).getId());
        }
        initRecyclerView(userType, userId);
    }

    private void initRecyclerView(String userType, int userId) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, productImageUrl, productTitles,
                productQuantity, productPrice, productCode, userType, userId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
