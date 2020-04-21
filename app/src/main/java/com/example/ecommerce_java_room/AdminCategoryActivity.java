package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.ImageWriter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    //male clothing
    private ImageView male_t_shirts;
    private ImageView male_trousers;
    private ImageView male_sweater;
    private ImageView male_shoes;
    //male accessories
    private ImageView male_hat;
    private ImageView male_watch;
    private ImageView male_sunglasses;
    private ImageView male_suitcases;
    //female clothing
    private ImageView female_dress;
    private ImageView female_trousers;
    private ImageView female_skirt;
    private ImageView female_shoes;
    //female accessories
    private ImageView female_purse;
    private ImageView female_scarf;
    private ImageView female_necklace;
    private ImageView female_bracelet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        //---------------MALE CLOTHES ONCLICK LISTENERS-----------------

        //male t-shirts onclick listener
        male_t_shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_t_shirts");
                startActivity(intent);
            }
        });
        //male trousers onclick listener
        male_trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_trousers");
                startActivity(intent);
            }
        });
        //male sweaters onclick listener
        male_sweater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_sweater");
                startActivity(intent);
            }
        });
        //male shoes onclick listener
        male_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_shoes");
                startActivity(intent);
            }
        });


        //---------------MALE ACCESSORIES ONCLICK LISTENERS-----------------

        //male hat on click listener
        male_hat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_hat");
                startActivity(intent);
            }
        });
        //male watch onclick listener
        male_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_watch");
                startActivity(intent);
            }
        });
        //male sunglasses onclick listener
        male_sunglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_sunglasses");
                startActivity(intent);
            }
        });
        //male suitcase onclick listener
        male_suitcases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_suicase");
                startActivity(intent);
            }
        });


        //---------------FEMALE CLOTHES ONCLICK LISTENERS-----------------

        //female dress onclick listener
        female_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_dress");
                startActivity(intent);
            }
        });
        //female trousers onclick listener
        female_trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_trousers");
                startActivity(intent);
            }
        });
        //female skirt onclick listener
        female_skirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_skirt");
                startActivity(intent);
            }
        });
        //female shoes onclick listener
        female_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_shoes");
                startActivity(intent);
            }
        });


        //---------------FEMALE ACCESSORIES ONCLICK LISTENERS-----------------

        //female purse onclick listener
        female_purse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_purse");
                startActivity(intent);
            }
        });
        //female scarf onclick listener
        female_scarf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_scarf");
                startActivity(intent);
            }
        });
        //female necklace onclick listener
        female_necklace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_necklace");
                startActivity(intent);
            }
        });
        //female bracelet onclick listener
        female_bracelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_bracelet");
                startActivity(intent);
            }
        });
    }
}
