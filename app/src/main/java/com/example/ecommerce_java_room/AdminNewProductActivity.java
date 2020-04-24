package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AdminNewProductActivity extends AppCompatActivity {

    private String categoryName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_product);


        categoryName = getIntent().getExtras().get("category").toString();
        //getting the category from our AdminCategoryActivity
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();
    }
}
