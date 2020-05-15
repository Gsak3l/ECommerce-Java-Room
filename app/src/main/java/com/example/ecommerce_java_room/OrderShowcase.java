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

import com.example.ecommerce_java_room.data.OrderDAO;
import com.example.ecommerce_java_room.data.OrderDatabase;
import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.data.UserDAO;
import com.example.ecommerce_java_room.data.UserDatabase;
import com.example.ecommerce_java_room.model.Order;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderShowcase extends AppCompatActivity {
    //array lists for all the fields
    private ArrayList<String> orderImages = new ArrayList<>();
    private ArrayList<Integer> orderIds = new ArrayList<>();
    private ArrayList<Integer> orderProductIds = new ArrayList<>();
    private ArrayList<Integer> orderProductQuantity = new ArrayList<>();
    private ArrayList<Double> orderTotalPrice = new ArrayList<>();
    //minimizing the decimal length of a double
    private DecimalFormat df = new DecimalFormat();
    //database stuff
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    //others
    private int userId;
    private String userType;
    //drawer stuff
    private Toolbar toolbar;
    private PrimaryDrawerItem homeNav;
    private PrimaryDrawerItem orderHistory;
    private PrimaryDrawerItem availability;
    private AccountHeader accountHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_showcase);
        userId = Integer.parseInt(getIntent().getExtras().get("userId").toString());
        userType = getIntent().getExtras().get("userType").toString();
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        orderDAO = Room.databaseBuilder(this, OrderDatabase.class, "Order")
                .allowMainThreadQueries().build().getOrderDao();
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").
                allowMainThreadQueries().build().getUserDao();
        initOrderInfo();
        toolbar = findViewById(R.id.toolbar_buy);
        //setting up the tool bar
        setToolbar();
    }

    //giving the order info to the arrays
    private void initOrderInfo() {
        List<Order> userOrders;
        if (userId == -1) {
            userOrders = orderDAO.getAllOrders();
        } else {
            userOrders = orderDAO.getUserOrders(userId);
        }
        for (int i = 0; i < userOrders.size(); i++) {
            //adding the image of the product to the orderImages array
            Product product = productDAO.getProduct(userOrders.get(i).getProductId());
            orderImages.add(product.getImageURL());
            //doing the same for the rest
            orderIds.add(userOrders.get(i).getId());
            orderProductIds.add(userOrders.get(i).getProductId());
            orderProductQuantity.add(userOrders.get(i).getOrderProductQuantity());
            orderTotalPrice.add(userOrders.get(i).getOrderPrice());
        }
        initOrderRecyclerView();
    }

    //setting the recycler view
    private void initOrderRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.order_recycler_view);
        RecyclerViewAdapterOrder recyclerViewAdapterOrder = new RecyclerViewAdapterOrder(this,
                orderImages, orderIds, orderProductIds, orderProductQuantity, orderTotalPrice);
        recyclerView.setAdapter(recyclerViewAdapterOrder);
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
                        Intent intent = new Intent(OrderShowcase.this, MainMenuActivity.class);
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
                        Intent intent = new Intent(OrderShowcase.this, OrderShowcase.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userId", userId);
                        Toast.makeText(OrderShowcase.this, "Order History", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        return false;
                    }
                });
        //on product availability click listener
        availability = new PrimaryDrawerItem().withName("Product Availability").withIcon(FontAwesome.Icon.faw_list).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(OrderShowcase.this, ProductSearch.class);
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
                Intent intent = new Intent(OrderShowcase.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        }));
    }


}
