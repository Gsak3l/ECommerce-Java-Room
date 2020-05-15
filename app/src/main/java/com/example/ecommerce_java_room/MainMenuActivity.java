package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.UserDAO;
import com.example.ecommerce_java_room.data.UserDatabase;
import com.example.ecommerce_java_room.model.User;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainMenuActivity extends AppCompatActivity {

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
    private ImageView accessories_bag;
    private ImageView accessories_scarf;
    private ImageView accessories_hat;
    private ImageView accessories_watch;
    //other
    private String userType;
    private int userId;
    //drawer stuff
    private Toolbar toolbar;
    private PrimaryDrawerItem homeNav;
    private PrimaryDrawerItem orderHistory;
    private PrimaryDrawerItem availability;
    private AccountHeader accountHeader;
    //database stuff
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        userType = getIntent().getExtras().get("userType").toString();
        userId = Integer.parseInt(getIntent().getExtras().get("userId").toString());
        Toast.makeText(MainMenuActivity.this, "Products", Toast.LENGTH_LONG).show();
        //allowing userDAO to execute queries etc
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").
                allowMainThreadQueries().build().getUserDao();

        //giving reference to all the ImageViews of our xml
        male_t_shirts = (ImageView) findViewById(R.id.male_tshirts);
        male_trousers = (ImageView) findViewById(R.id.male_trousers);
        male_sweater = (ImageView) findViewById(R.id.male_sweaters);
        male_shoes = (ImageView) findViewById(R.id.male_shoes);
        female_dress = (ImageView) findViewById(R.id.female_dresses);
        female_trousers = (ImageView) findViewById(R.id.female_trousers);
        female_skirt = (ImageView) findViewById(R.id.female_skirts);
        female_shoes = (ImageView) findViewById(R.id.female_shoes);
        accessories_bag = (ImageView) findViewById(R.id.accessories_bag);
        accessories_scarf = (ImageView) findViewById(R.id.accessories_scarf);
        accessories_hat = (ImageView) findViewById(R.id.accessories_hat);
        accessories_watch = (ImageView) findViewById(R.id.accessories_watch);
        toolbar = findViewById(R.id.toolbar_main_menu);


        //---------------MALE CLOTHES ONCLICK LISTENERS-----------------

        //male t-shirts onclick listener
        male_t_shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "T-Shirts for Men");  //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //male trousers onclick listener
        male_trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Trousers for Men"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //male sweaters onclick listener
        male_sweater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Sweaters for Men"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //male shoes onclick listener
        male_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Shoes for Men"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });


        //---------------FEMALE CLOTHES ONCLICK LISTENERS-----------------

        //female dress onclick listener
        female_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Dresses for Women"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //female trousers onclick listener
        female_trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Trousers for Women"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //female skirt onclick listener
        female_skirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Skirts for Women"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //female shoes onclick listener
        female_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Shoes for Women"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });


        //---------------ACCESSORIES ONCLICK LISTENERS-----------------

        //male hat on click listener
        accessories_hat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Hats"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //male watch onclick listener
        accessories_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Watches"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //accessory purse-bag onclick listener
        accessories_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Purses&Bags"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        //accessory scarf onclick listener
        accessories_scarf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProductShowcase.class);
                intent.putExtra("category", "Scarfs"); //passing to the AdminNewProductActivity.java the category with the name x
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        setToolbar();
    }


    private void setToolbar() {
        //creating everything that will be contained in the drawer
        if (userId == -1) {
            accountHeader = new AccountHeaderBuilder().withActivity(this).withTranslucentStatusBar(true)
                    .addProfiles(new ProfileDrawerItem().withName("admin").withEmail("admin@admin.admin"))
                    .build();
        } else {
            User user = userDAO.getUserById(userId);
            accountHeader = new AccountHeaderBuilder().withActivity(this).withTranslucentStatusBar(true)
                    .addProfiles(new ProfileDrawerItem().withName(user.getFullName()).withEmail(user.getEmail()))
                    .build();
        }
        //on home nav home button click
        homeNav = new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(MainMenuActivity.this, MainMenuActivity.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        return false;
                    }
                });
        orderHistory = new PrimaryDrawerItem().withName("Order History").withIcon(FontAwesome.Icon.faw_history).
                withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(MainMenuActivity.this, OrderShowcase.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userId", userId);
                        Toast.makeText(MainMenuActivity.this, "Order History", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        return false;
                    }
                });
        //on product availability click listener
        availability = new PrimaryDrawerItem().withName("Product Availability").withIcon(FontAwesome.Icon.faw_list).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(MainMenuActivity.this, ProductSearch.class);
                intent.putExtra("userType", userType);
                intent.putExtra("userId", userId);
                startActivity(intent);
                return false;
            }
        });

        //giving color to the drawer
        toolbar.setBackground(ResourcesCompat.getDrawable(getResources(), R.color.primary_dark, null));
        //adding options to the drawer
        Drawer drawer = new DrawerBuilder().withActivity(this).withToolbar(toolbar).withAccountHeader(accountHeader).addDrawerItems(
                homeNav,
                orderHistory,
                new DividerDrawerItem(),
                availability
        ).build();
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Logout").withIcon(FontAwesome.Icon.faw_sign_out));
    }
}

