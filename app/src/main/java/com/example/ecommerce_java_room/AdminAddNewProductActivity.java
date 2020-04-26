package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fr.ganfra.materialspinner.MaterialSpinner;

public class AdminAddNewProductActivity extends AppCompatActivity {
    MaterialSpinner product_category_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);
        product_category_spinner = (MaterialSpinner) findViewById(R.id.spinner);
    }
}
