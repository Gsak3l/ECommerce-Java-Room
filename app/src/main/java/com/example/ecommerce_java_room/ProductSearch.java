package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.model.Product;
import com.google.android.material.textfield.TextInputLayout;
import com.rey.material.widget.Button;
import com.rey.material.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductSearch extends AppCompatActivity {

    //initializing all the xml elements from the activity_product_search.xml
    private TextInputLayout searchId;
    private Button searchButton;
    private TextView showAvailability;
    private ImageView showImage;
    //database stuff
    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);

        //finding all the elements of our xml file
        searchId = findViewById(R.id.search_find_product);
        searchButton = findViewById(R.id.search_button);
        showAvailability = findViewById(R.id.search_show_availability);
        showImage = findViewById(R.id.search_show_product_image);

        //allowing queries etc
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();

        //onclick for the button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchId.getEditText().getText().toString().trim().equals("")) {
                    try {
                        Product product = productDAO.getProduct(Integer.parseInt(searchId.getEditText().getText().toString().trim()));
                        showAvailability.setText("Product Stock: " + product.getQuantity());
                        showAvailability.setVisibility(View.VISIBLE);
                        Picasso.get().load(product.getImageURL()).into(showImage);
                        showImage.setVisibility(View.VISIBLE);
                        showImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                        showImage.getLayoutParams().height = 900;
                        searchId.getEditText().setText("");
                    }
                    catch (NullPointerException e) {
                        Toast.makeText(ProductSearch.this, "Product not Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProductSearch.this, "Please Type Something", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
