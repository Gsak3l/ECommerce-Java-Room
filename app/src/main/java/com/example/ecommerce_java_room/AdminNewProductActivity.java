package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rey.material.widget.FloatingActionButton;

import java.util.ArrayList;

public class AdminNewProductActivity extends AppCompatActivity {

    private String categoryName;
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private com.google.android.material.floatingactionbutton.FloatingActionButton addNewProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_product);
        categoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();
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
        if (categoryName.equals("T-Shirts for Men")) {

            mImageUrls.add("https://images.asos-media.com/products/nike-jordan-jumpan-t-shirt-in-white/14027889-1-white?$XXL$&wid=513&fit=constrain");
            mTitles.add("Nike Jordan Jumpan t-shirt in white");

            mImageUrls.add("https://images.asos-media.com/products/nike-running-miler-t-shirt-in-stone-jacquard/14036849-1-beige?$XXL$&wid=513&fit=constrain");
            mTitles.add("Nike Running Miler t-shirt in stone jacquard");

            mImageUrls.add("https://images.asos-media.com/products/asos-design-organic-t-shirt-with-crew-neck-in-white/13112623-1-white?$XXL$&wid=513&fit=constrain");
            mTitles.add("organic t-shirt with crew neck in white");

            mImageUrls.add("https://images.asos-media.com/products/asos-design-organic-t-shirt-with-crew-neck-in-black/13112617-1-black?$XXL$&wid=513&fit=constrain");

            mTitles.add("organic t-shirt with crew neck in black");

            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mTitles, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
