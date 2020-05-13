package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UserProductBuy extends AppCompatActivity {
    //xml elements
    private TextView productTitle;
    private ImageView productImage;
    private TextView productPrice;
    private NumberPicker productQuantity;
    private Button buyProductButton;
    //minimizing the decimal length of a double
    DecimalFormat df = new DecimalFormat();
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
    //user id
    private int userId;
    //id that comes from the clicked product on the productshowcase page
    private int productId;
    //recycle view lists
    private ArrayList<String> orderImages;
    private ArrayList<Integer> orderIds;
    private ArrayList<Integer> orderProductIds;
    private ArrayList<Integer> orderProductQuantity;
    private ArrayList<Double> orderTotalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product_buy);
        //locating the item clicked by the id that we got when they clicked the product
        productId = Integer.parseInt(getIntent().getExtras().get("productId").toString());
        userId = Integer.parseInt(getIntent().getExtras().get("userId").toString());
        //database stuff
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").
                allowMainThreadQueries().build().getUserDao();
        orderDAO = Room.databaseBuilder(this, OrderDatabase.class, "Order")
                .allowMainThreadQueries().build().getOrderDao();
        //finding the xml elements by id
        productTitle = findViewById(R.id.user_buy_product_title);
        productImage = findViewById(R.id.user_buy_product_image);
        productPrice = findViewById(R.id.user_buy_product_price);
        productQuantity = findViewById(R.id.user_buy_product_quantity);
        buyProductButton = findViewById(R.id.user_buy_add_to_card);
        final Product product = productDAO.getProduct(productId);
        //table that contains all the numbers for the numberPicker
        //giving values to the fields
        productTitle.setText(product.getTitle());
        Picasso.get().load(product.getImageURL()).into(productImage);
        productQuantity.setMinValue(0); //setting max and min values for the spinner
        productQuantity.setMaxValue(product.getQuantity() - 1);
        productPrice.setText(df.format(product.getPrice()) + "$");
        toolbar = findViewById(R.id.toolbar_buy);
        //setting up the tool bar
        setToolbar();
        //action for the onclick add purchase button
        buyProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting all the values for price, quantity, etc...
                int quantity = productQuantity.getValue(); //works
                if (quantity != 0) {
                    double orderPrice = Double.parseDouble(df.format(product.getPrice() * quantity));
                    productDAO.updateQuantity(productId, -1 * quantity);
                    Order order = new Order(productId, userId, orderPrice, quantity);
                    orderDAO.insert(order);
                    Toast.makeText(UserProductBuy.this, "Success!", Toast.LENGTH_LONG).show();
                    productQuantity.setValue(0);
                } else {
                    Toast.makeText(UserProductBuy.this, "Transaction Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //initializing and sending the values to RecyclerViewAdapter
    private void initOrderRecyclerView() {
        RecyclerView orderRecyclerView = findViewById(R.id.order_recyclerview);
        RecyclerViewAdapterOrder orderAdapter = new RecyclerViewAdapterOrder(this,
                orderImages, orderIds, orderProductIds, orderProductQuantity, orderTotalPrice);
        orderRecyclerView.setAdapter(orderAdapter); //setting the adapter to the recycler view
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //giving values to the arrays at the top
    private void initOrderDetails() {
        List<Order> userOrders = orderDAO.getUserOrders(userId);
        for (int i = 0; i < userOrders.size(); i++) {
            //getting the product that has the productId from the order
            //images for the circle image
            orderImages.add("https://preview.redd.it/gk5hz3ww2gy41.jpg?width=640&crop=smart&auto=webp&s=2089aa437d9154f0d91806d8b33b131d118f888c");
            //other
            orderIds.add(orderDAO.getOrderById(productId).getId());
            orderProductIds.add(orderDAO.getOrderById(productId).getProductId());
            orderProductQuantity.add(orderDAO.getOrderById(productId).getOrderProductQuantity());
            orderTotalPrice.add(orderDAO.getOrderById(productId).getOrderPrice());
            //calling initOrderRecyclerView
            initOrderRecyclerView();
        }
    }

    private void setToolbar() {
        //creating everything that will be contained in the drawer
        User user = userDAO.getUserById(userId);
        //on home nav home button click
        homeNav = new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(UserProductBuy.this, MainMenuActivity.class);
                        intent.putExtra("type", "user");
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        return false;
                    }
                });
        orderHistory = new PrimaryDrawerItem().withName("Order History").withIcon(FontAwesome.Icon.faw_history).
                withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        initOrderDetails();
                        Intent intent = new Intent(UserProductBuy.this, RecyclerViewAdapterOrder.class);
                        startActivity(intent);
                        return false;
                    }
                });
        //on product availability click listener
        availability = new PrimaryDrawerItem().withName("Product Availability").withIcon(FontAwesome.Icon.faw_list).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(UserProductBuy.this, ProductSearch.class);
                startActivity(intent);
                return false;
            }
        });
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
