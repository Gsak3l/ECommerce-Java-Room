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

    //database stuff
    private ProductDAO productDAO;
    private ProductDatabase productDatabase;
    List<Product> products = new ArrayList<>();

    //other stuff
    boolean flag = true;
    private String categoryName;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_showcase_admin);
        categoryName = getIntent().getExtras().get("category").toString();
        userType = getIntent().getExtras().get("userType").toString();
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        initImageBitmaps(userType);
        addNewProduct = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.admin_add_new_product_button);
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(productShowcaseAdmin.this, AdminAddNewProductActivity.class);
                startActivity(login);
            }
        });
    }

    private void initImageBitmaps(String userType) {
        /*deleting all products
        if(flag) {
            productDAO.getAllProducts().size();
            productDAO.deleteAllProducts();
            productDAO.getAllProducts().size();
            flag = false;
        }*/
        //productDAO.updateCategory(49, "Dresses for Women");

        products = productDAO.getProductsByCategory(categoryName);
        for (int i = 0; i < products.size(); i++) {
            productImageUrl.add(products.get(i).getImageURL());
            productTitles.add(products.get(i).getTitle());
            productQuantity.add(products.get(i).getQuantity());
            productPrice.add(products.get(i).getPrice());
            productCode.add(products.get(i).getId());
        }
        initRecyclerView(userType);
    }

    private void initRecyclerView(String userType) {
        if (userType.equals("admin")) {
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, productImageUrl, productTitles,
                    productQuantity, productPrice, productCode);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } /*else if (userType.equals("user")) {
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, productImageUrl, productTitles, productPrice);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }*/
    }
}
