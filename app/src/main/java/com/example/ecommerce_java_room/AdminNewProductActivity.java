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
import com.rey.material.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AdminNewProductActivity extends AppCompatActivity {

    //lists used to fill the recycler from our database
    private String categoryName;
    private ArrayList<String> productTitles = new ArrayList<>();
    private ArrayList<String> productImageUrl = new ArrayList<>();
    private ArrayList<Integer> productQuantity = new ArrayList<>();
    private ArrayList<Double> productPrice = new ArrayList<>();
    private ArrayList<Integer> productCode = new ArrayList<>();

    private com.google.android.material.floatingactionbutton.FloatingActionButton addNewProduct;

    //database stuff
    private ProductDAO productDAO;
    private ProductDatabase productDatabase;
    List<Product> products = new ArrayList<>();

    //onetime stuff
    boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_product);
        categoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        initImageBitmaps();
        addNewProduct = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.admin_add_new_product_button);
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(AdminNewProductActivity.this, AdminAddNewProductActivity.class);
                startActivity(login);
            }
        });
    }

    private void initImageBitmaps() {
        /*deleting all products
        if(flag) {
            productDAO.getAllProducts().size();
            productDAO.deleteAllProducts();
            productDAO.getAllProducts().size();
            flag = false;
        }*/

        productDAO.updateCategory(49, "Dresses for Women");
        productDAO.updateCategory(46, "Dresses for Women");
        productDAO.updateCategory(47, "Dresses for Women");


        products = productDAO.getProductsByCategory(categoryName);
        for (int i = 0; i < products.size(); i++) {
            productImageUrl.add(products.get(i).getImageURL());
            productTitles.add(products.get(i).getTitle());
            productQuantity.add(products.get(i).getQuantity());
            productPrice.add(products.get(i).getPrice());
            productCode.add(products.get(i).getId());
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, productImageUrl, productTitles,
                productQuantity, productPrice, productCode);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
