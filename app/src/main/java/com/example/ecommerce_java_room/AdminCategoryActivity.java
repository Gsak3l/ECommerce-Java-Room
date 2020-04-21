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

        //giving reference to all the ImageViews of our xml
        male_t_shirts = (ImageView) findViewById(R.id.male_t_shirts_admin_category_page);
        male_trousers = (ImageView) findViewById(R.id.male_trousers_admin_category_page);
        male_sweater = (ImageView) findViewById(R.id.male_sweaters_admin_category_page);
        male_shoes = (ImageView) findViewById(R.id.male_shoes_admin_category_page);
        male_hat = (ImageView) findViewById(R.id.male_hat_admin_cattegory_page);
        male_watch = (ImageView) findViewById(R.id.male_watch_admin_cattegory_page_cate);
        male_sunglasses = (ImageView) findViewById(R.id.male_sunglasses_admin_cattegory_page);
        male_suitcases = (ImageView) findViewById(R.id.male_suitcase_admin_cattegory_page);
        female_dress = (ImageView) findViewById(R.id.female_dresses_admin_category_page);
        female_trousers = (ImageView) findViewById(R.id.female_trousers_admin_category_page);
        female_skirt = (ImageView) findViewById(R.id.female_skirts__admin_category_page);
        female_shoes = (ImageView) findViewById(R.id.female_shoes__admin_category_page);
        female_purse = (ImageView) findViewById(R.id.female_purse_admin_cattegory_page);
        female_scarf = (ImageView) findViewById(R.id.female_scarf_admin_cattegory_page_cate);
        female_necklace = (ImageView) findViewById(R.id.female_necklace_admin_cattegory_page);
        female_bracelet = (ImageView) findViewById(R.id.female_bracelet_admin_cattegory_page);


        //---------------MALE CLOTHES ONCLICK LISTENERS-----------------

        //male t-shirts onclick listener
        male_t_shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_t_shirts");  //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male trousers onclick listener
        male_trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_trousers"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male sweaters onclick listener
        male_sweater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_sweater"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male shoes onclick listener
        male_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_shoes"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });


        //---------------MALE ACCESSORIES ONCLICK LISTENERS-----------------

        //male hat on click listener
        male_hat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_hat"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male watch onclick listener
        male_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_watch"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male sunglasses onclick listener
        male_sunglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_sunglasses"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //male suitcase onclick listener
        male_suitcases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "male_suicase"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });


        //---------------FEMALE CLOTHES ONCLICK LISTENERS-----------------

        //female dress onclick listener
        female_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_dress"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female trousers onclick listener
        female_trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_trousers"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female skirt onclick listener
        female_skirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_skirt"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female shoes onclick listener
        female_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_shoes"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });


        //---------------FEMALE ACCESSORIES ONCLICK LISTENERS-----------------

        //female purse onclick listener
        female_purse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_purse"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female scarf onclick listener
        female_scarf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_scarf"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female necklace onclick listener
        female_necklace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_necklace"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
        //female bracelet onclick listener
        female_bracelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewProductActivity.class);
                intent.putExtra("category", "female_bracelet"); //passing to the AdminNewProductActivity.java the category with the name x
                startActivity(intent);
            }
        });
    }
}
