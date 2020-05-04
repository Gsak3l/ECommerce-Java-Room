package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.model.Product;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserProductBuy extends AppCompatActivity {
    //xml elements
    TextView productTitle;
    ImageView productImage;
    TextView productPrice;
    NumberPicker productQuantity;
    //id that comes from the clicked product on the productshowcase page
    int id;
    //database stuff
    private ProductDAO productDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product_buy);
        //locating the item clicked by the id that we got when they clicked the product
        id = Integer.parseInt(getIntent().getExtras().get("id").toString());
        //database stuff
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        //finding the xml elements by id
        productTitle = findViewById(R.id.user_buy_product_title);
        productImage = findViewById(R.id.user_buy_product_image);
        productPrice = findViewById(R.id.user_buy_product_price);
        productQuantity = findViewById(R.id.user_buy_product_quantity);
        Product product = productDAO.getProduct(id);
        //table that contains all the numbers for the numberPicker
        String quantity[] = new String[product.getQuantity()];
        //Toast.makeText(UserProductBuy.this, "i am alive you son of a bitch: " + id, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < quantity.length; i++) {
            quantity[i] = "" + i;
        }
        //giving values to the fields
        productTitle.setText(product.getTitle());
        Picasso.get().load(product.getImageURL()).into(productImage);
        productQuantity.setDisplayedValues(quantity);
    }
}
