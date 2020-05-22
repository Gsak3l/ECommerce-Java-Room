package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.OrderDAO;
import com.example.ecommerce_java_room.data.OrderDatabase;
import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.data.UserDAO;
import com.example.ecommerce_java_room.data.UserDatabase;
import com.example.ecommerce_java_room.model.Order;
import com.example.ecommerce_java_room.model.Product;
import com.example.ecommerce_java_room.model.User;
import com.google.android.material.textfield.TextInputLayout;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
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
    private UserDAO userDAO;
    private OrderDAO orderDAO;
    //drawer stuff
    private Toolbar toolbar;
    private PrimaryDrawerItem homeNav;
    private PrimaryDrawerItem orderHistory;
    private PrimaryDrawerItem availability;
    private AccountHeader accountHeader;
    //user stuff
    private int userId;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        userId = Integer.parseInt(getIntent().getExtras().get("userId").toString());
        userType = getIntent().getExtras().get("userType").toString();
        //finding all the elements of our xml file
        searchId = findViewById(R.id.search_find_product);
        searchButton = findViewById(R.id.search_button);
        showAvailability = findViewById(R.id.search_show_availability);
        showImage = findViewById(R.id.search_show_product_image);
        toolbar = findViewById(R.id.toolbar_search);
        //allowing queries etc
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").
                allowMainThreadQueries().build().getUserDao();
        orderDAO = Room.databaseBuilder(this, OrderDatabase.class, "Order")
                .allowMainThreadQueries().build().getOrderDao();
        //onclick for the button
        //showing different results for the admin
        if (userId == -1) {
            searchButton.setText("Check Details");
        }
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchId.getEditText().getText().toString().trim().equals("")) {
                    try {
                        //showing different results for the admin
                        if (userId == -1) {
                            //doing some extra things that i don't want to, but i have to
                            //getting the product by its id
                            Product product = productDAO.getProduct(Integer.parseInt(searchId.getEditText().getText().toString().trim()));
                            List<Order> productOrders = orderDAO.getProductOrders(Integer.parseInt(searchId.getEditText().getText().toString().trim()));
                            int productPieces = 0;
                            //total pieces sold for this specific item
                            for (int i = 0; i < productOrders.size(); i++) {
                                productPieces += productOrders.get(i).getOrderProductQuantity();
                            }
                            //displaying stuff
                            showAvailability.setText("Product Stock: " + product.getQuantity() + ", " +
                                    "Total Sales: " + orderDAO.getAllOrders().size() + "\n" +
                                    "Total Pieces Sold for this Item: " + productPieces);
                            //making the invisible text, visible
                            showAvailability.setVisibility(View.VISIBLE);
                            //getting the image by its url using picasso this time
                            Picasso.get().load(product.getImageURL()).into(showImage);
                            showImage.setVisibility(View.VISIBLE); //making the image visible
                            showImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT; //width for the image
                            showImage.getLayoutParams().height = 900; //height for the image
                            searchId.getEditText().setText(""); //emptying the text
                        } else {
                            Product product = productDAO.getProduct(Integer.parseInt(searchId.getEditText().getText().toString().trim()));
                            showAvailability.setText("Product Stock: " + product.getQuantity());
                            showAvailability.setVisibility(View.VISIBLE);
                            Picasso.get().load(product.getImageURL()).into(showImage);
                            showImage.setVisibility(View.VISIBLE);
                            showImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                            showImage.getLayoutParams().height = 900;
                            searchId.getEditText().setText("");
                        }
                    } catch (NullPointerException e) {
                        Toast.makeText(ProductSearch.this, "Product not Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProductSearch.this, "Please Type Something", Toast.LENGTH_SHORT).show();
                }
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
                        Intent intent = new Intent(ProductSearch.this, MainMenuActivity.class);
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
                        Intent intent = new Intent(ProductSearch.this, OrderShowcase.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userId", userId);
                        Toast.makeText(ProductSearch.this, "Order History", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        return false;
                    }
                });
        //on product availability click listener
        availability = new PrimaryDrawerItem().withName("Product Availability").withIcon(FontAwesome.Icon.faw_list).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(ProductSearch.this, ProductSearch.class);
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
                Intent intent = new Intent(ProductSearch.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        }));
    }
}
