package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.rey.material.widget.Button;
import com.rey.material.widget.TextView;

public class ProductSearch extends AppCompatActivity {

    //initializing all the xml elements from the activity_product_search.xml
    private TextInputLayout searchId;
    private Button searchButton;
    private TextView showAvailability;
    private ImageView showImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);

        //finding all the elements of our xml file
        searchId = findViewById(R.id.search_find_product);
        searchButton = findViewById(R.id.search_button);
        showAvailability = findViewById(R.id.search_show_availability);
        showImage = findViewById(R.id.search_show_product_image);

        //onclick for the button
    }
}
