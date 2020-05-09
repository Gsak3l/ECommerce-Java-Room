package com.example.ecommerce_java_room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.data.UserDAO;
import com.example.ecommerce_java_room.data.UserDatabase;
import com.example.ecommerce_java_room.model.Product;
import com.example.ecommerce_java_room.model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UserProductBuy extends AppCompatActivity {
    //xml elements
    private TextView productTitle;
    private ImageView productImage;
    private TextView productPrice;
    private NumberPicker productQuantity;
    private Toolbar toolbar;
    //id that comes from the clicked product on the productshowcase page
    private int id;
    //minimizing the decimal length of a double
    DecimalFormat df = new DecimalFormat();
    //database stuff
    private ProductDAO productDAO;
    private UserDAO userDAO;
    //drawer stuff
    private PrimaryDrawerItem homeNav;
    private PrimaryDrawerItem orderHistory;
    private AccountHeader accountHeader;
    private SecondaryDrawerItem availability;
    //
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product_buy);
        //locating the item clicked by the id that we got when they clicked the product
        id = Integer.parseInt(getIntent().getExtras().get("productId").toString());
        userId = Integer.parseInt(getIntent().getExtras().get("userId").toString());
        //database stuff
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").
                allowMainThreadQueries().build().getUserDao();
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
        productPrice.setText(df.format(product.getPrice()) + "$");
        toolbar = findViewById(R.id.toolbar);
        //setting up the tool bar
        setToolbarStuff();
        //Toast.makeText(UserProductBuy.this, "i am alive you son of a bitch: " + id, Toast.LENGTH_SHORT).show();

    }

    public void setToolbarStuff() {
        //creating everything that will be contained in the drawer
        User user = userDAO.getUserById(userId);
        homeNav = new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(UserProductBuy.this, MainMenuActivity.class);
                        intent.putExtra("type", "user");
                        startActivity(intent);
                        return false;
                    }
                });
        orderHistory = new PrimaryDrawerItem().withName("Order History").withIcon(FontAwesome.Icon.faw_history);
        availability = new SecondaryDrawerItem().withName("Product Availability").withIcon(FontAwesome.Icon.faw_list);
        accountHeader = new AccountHeaderBuilder().withActivity(this).withTranslucentStatusBar(true)
                .addProfiles(new ProfileDrawerItem().withName(user.getFullName()).withEmail(user.getEmail()))
                .build();
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
        drawer.setSelection(0);
    }
}
