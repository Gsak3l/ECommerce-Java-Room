package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
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
    Toolbar toolbar;
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
        //giving values to the fields
        productTitle.setText(product.getTitle());
        Picasso.get().load(product.getImageURL()).into(productImage);
        productQuantity.setMinValue(0); //setting max and min values for the spinner
        productQuantity.setMaxValue(product.getQuantity() - 1);
        toolbar = findViewById(R.id.toolbar);
        setToolbarStuff();
        //Toast.makeText(UserProductBuy.this, "i am alive you son of a bitch: " + id, Toast.LENGTH_SHORT).show();
    }

    public void setToolbarStuff() {
        toolbar.setBackground(ResourcesCompat.getDrawable(getResources(), R.color.primary_dark, null));
    }
}
