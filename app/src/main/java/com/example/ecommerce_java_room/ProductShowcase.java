package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.data.UserDAO;
import com.example.ecommerce_java_room.data.UserDatabase;
import com.example.ecommerce_java_room.model.Product;
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

import java.util.ArrayList;
import java.util.List;

public class ProductShowcase extends AppCompatActivity {

    //lists used to fill the recycler from our database
    private ArrayList<String> productTitles = new ArrayList<>();
    private ArrayList<String> productImageUrl = new ArrayList<>();
    private ArrayList<Integer> productQuantity = new ArrayList<>();
    private ArrayList<Double> productPrice = new ArrayList<>();
    private ArrayList<Integer> productCode = new ArrayList<>();

    //floating buttons
    private com.google.android.material.floatingactionbutton.FloatingActionButton addNewProduct;
    private com.google.android.material.floatingactionbutton.FloatingActionButton basket;

    //drawer stuff
    private Toolbar toolbar;
    private PrimaryDrawerItem homeNav;
    private PrimaryDrawerItem orderHistory;
    private PrimaryDrawerItem availability;
    private AccountHeader accountHeader;

    //database stuff
    private ProductDAO productDAO;
    private UserDAO userDAO;
    private List<Product> products = new ArrayList<>();

    //other stuff
    private String categoryName;
    private String userType;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_showcase);
        //getting all the values sent from MainMenu
        categoryName = getIntent().getExtras().get("category").toString();
        userType = getIntent().getExtras().get("userType").toString();
        userId = Integer.parseInt(getIntent().getExtras().get("userId").toString());
        //making text for the category name
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").
                allowMainThreadQueries().build().getUserDao();
        //showing images
        initProductDetails(userType);
        addNewProduct = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.admin_add_new_product_button);
        basket = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.user_basket_button);
        //showing different buttons for user and admin
        if (userType.equals("user")) {
            basket.setVisibility(View.VISIBLE);
            addNewProduct.setVisibility(View.INVISIBLE);
            basket.setOnClickListener(new View.OnClickListener() { //onclick listener for the floating button
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProductShowcase.this, OrderShowcase.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("userType", userType);
                    Toast.makeText(ProductShowcase.this, "Order History", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            });
        }
        //showing different buttons for user and admin
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ProductShowcase.this, AdminAddNewProductActivity.class);
                startActivity(login);
            }
        });
        toolbar = findViewById(R.id.toolbar_showcase);
        //setting up the tool bar
        setToolbar();
    }

    private void initProductDetails(String userType) {
        //giving all the values like price quantity etc for all the products
        products = productDAO.getProductsByCategory(categoryName);
        for (int i = 0; i < products.size(); i++) {
            productImageUrl.add(products.get(i).getImageURL());
            productTitles.add(products.get(i).getTitle());
            productQuantity.add(products.get(i).getQuantity());
            productPrice.add(products.get(i).getPrice());
            productCode.add(products.get(i).getId());
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view); //finding the recyclerview
        RecyclerViewAdapterProduct adapter = new RecyclerViewAdapterProduct(this, productImageUrl, productTitles,
                productQuantity, productPrice, productCode, userType, userId); //creating an adapter with the type of RecyclerViewAdapterProduct
        recyclerView.setAdapter(adapter); //setting the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                        Intent intent = new Intent(ProductShowcase.this, MainMenuActivity.class);
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
                        Intent intent = new Intent(ProductShowcase.this, OrderShowcase.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userId", userId);
                        Toast.makeText(ProductShowcase.this, "Order History", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        return false;
                    }
                });
        //on product availability click listener
        availability = new PrimaryDrawerItem().withName("Product Availability").withIcon(FontAwesome.Icon.faw_list).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(ProductShowcase.this, ProductSearch.class);
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
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Logout").withIcon(FontAwesome.Icon.faw_sign_out).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(ProductShowcase.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        }));
    }
}
