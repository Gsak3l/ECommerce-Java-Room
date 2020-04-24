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
    //female clothing
    private ImageView female_dress;
    private ImageView female_trousers;
    private ImageView female_skirt;
    private ImageView female_shoes;
    //female accessories
    private ImageView accessories_purse;
    private ImageView accessories_scarf;
    private ImageView accessories_hat;
    private ImageView accessories_watch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        //giving reference to all the ImageViews of our xml
        male_t_shirts = (ImageView) findViewById(R.id.male_t_shirts_admin_category_page);
        male_trousers = (ImageView) findViewById(R.id.male_trousers_admin_category_page);
        male_sweater = (ImageView) findViewById(R.id.male_sweaters_admin_category_page);
        male_shoes = (ImageView) findViewById(R.id.male_shoes_admin_category_page);
        female_dress = (ImageView) findViewById(R.id.female_dresses_admin_category_page);
        female_trousers = (ImageView) findViewById(R.id.female_trousers_admin_category_page);
        female_skirt = (ImageView) findViewById(R.id.female_skirts__admin_category_page);
        female_shoes = (ImageView) findViewById(R.id.female_shoes__admin_category_page);
        accessories_purse = (ImageView) findViewById(R.id.accessories_purse_admin_cattegory_page);
        accessories_scarf = (ImageView) findViewById(R.id.accessories_scarf_admin_cattegory_page_cate);
        accessories_hat = (ImageView) findViewById(R.id.accessories_hat_admin_cattegory_page);
        accessories_watch = (ImageView) findViewById(R.id.accessories_watch_admin_cattegory_page_cate);



        //---------------MALE CLOTHES ONCLICK LISTENERS-----------------

        //male t-shirts onclick listener
        male_t_shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "T-Shirts for Men");  //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male trousers onclick listener
        male_trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Trousers for Men"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male sweaters onclick listener
        male_sweater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Sweaters for Men"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male shoes onclick listener
        male_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Shoes for Men"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });


        //---------------FEMALE CLOTHES ONCLICK LISTENERS-----------------

        //female dress onclick listener
        female_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Dresses for Women"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female trousers onclick listener
        female_trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Trousers for Women"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female skirt onclick listener
        female_skirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Skirts for Women"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female shoes onclick listener
        female_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Shoes for Women"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });


        //---------------ACCESSORIES ONCLICK LISTENERS-----------------

        //male hat on click listener
        accessories_hat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Hats"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male watch onclick listener
        //male sunglasses onclick listener
        accessories_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Watches"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //accessory purse onclick listener
        accessories_purse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Purses"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //accessory scarf onclick listener
        accessories_scarf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "Scarfs"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
    }
}
