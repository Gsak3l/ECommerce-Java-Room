package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.data.UserDAO;
import com.example.ecommerce_java_room.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import com.jaredrummler.materialspinner.MaterialSpinner;
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

public class AdminAddNewProductActivity extends AppCompatActivity {

    //initializing all the xml elements from the activity_admin_add_new_product.xml
    private TextInputLayout productURL;
    private TextInputLayout productTitle;
    private MaterialSpinner productTypeSpinner;
    private TextInputLayout productQuantity;
    private TextInputLayout productPrice;
    private List<String> typesAvailable = new ArrayList<>();
    private FloatingActionButton confirmAddProductButton;
    private FloatingActionButton deleteProductButton;

    //drawer stuff
    private Toolbar toolbar;
    private PrimaryDrawerItem homeNav;
    private PrimaryDrawerItem orderHistory;
    private PrimaryDrawerItem availability;
    private AccountHeader accountHeader;

    //database stuff
    private ProductDAO productDAO;

    //other
    private int editById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        //adding available categories to our product spinner
        typesAvailable.add("T-Shirts for Men");
        typesAvailable.add("Trousers for Men");
        typesAvailable.add("Sweaters for Men");
        typesAvailable.add("Shoes for Men");
        typesAvailable.add("Dresses for Women");
        typesAvailable.add("Trousers for Women");
        typesAvailable.add("Skirts for Women");
        typesAvailable.add("Shoes for Women");
        typesAvailable.add("Hats");
        typesAvailable.add("Purses&Bags");
        typesAvailable.add("Watches");
        typesAvailable.add("Scarfs");

        //finding all the elements of our xml file
        productURL = findViewById(R.id.add_new_product_imageURL);
        productTitle = findViewById(R.id.add_new_product_title);
        productQuantity = findViewById(R.id.add_new_product_quantity);
        productPrice = findViewById(R.id.add_new_product_price);
        productTypeSpinner = findViewById(R.id.add_new_product_category);
        confirmAddProductButton = findViewById(R.id.add_new_product_confirm_button);
        deleteProductButton = findViewById(R.id.delete_product_confirm_button);
        //giving the available values of the typesAvailable list to the spinner
        final ArrayAdapter products = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typesAvailable);
        products.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productTypeSpinner.setAdapter(products);

        //toolbar
        toolbar = findViewById(R.id.toolbar_add_admin);
        setToolbar();


        //allowing queries
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();

        //checking if its a product add or a product edit
        try {
            editById = Integer.parseInt(getIntent().getExtras().get("productId").toString());
            editProduct(editById);
        } catch (NullPointerException e) {
            addNewProduct();
        }
    }

    //admin adding new product
    public void addNewProduct() {
        confirmAddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toastText = "Please Fill all the Available Fields";
                List<Product> products;
                //checking if fields are blank
                if (!productURL.getEditText().getText().toString().trim().matches("") &&
                        !productTitle.getEditText().getText().toString().trim().matches("") &&
                        !productPrice.getEditText().getText().toString().trim().matches("") &&
                        !productQuantity.getEditText().getText().toString().trim().matches("")) {

                    try {
                        String URL = productURL.getEditText().getText().toString().trim();
                        String title = productTitle.getEditText().getText().toString().trim();
                        int quantity = Integer.parseInt(productQuantity.getEditText().getText().toString().trim());
                        double price = Double.parseDouble(productPrice.getEditText().getText().toString().trim());
                        String category = typesAvailable.get(productTypeSpinner.getSelectedIndex());
                        Product product = new Product(URL, title, quantity, price, category);
                        productDAO.insert(product);
                        toastText = "Product Created Successfully!";

                        //blanking the fields after the product has been created
                        productURL.getEditText().setText("");
                        productTitle.getEditText().setText("");
                        productQuantity.getEditText().setText("");
                        productPrice.getEditText().setText("");
                        productTypeSpinner.setSelectedIndex(0);

                        products = productDAO.getAllProducts();
                        System.out.println(products.size());
                        System.out.println(products.get(0).getCategory());

                    } catch (NumberFormatException e) {
                        toastText = "Give the Right Values at the Given Fields";
                    }

                }
                Toast.makeText(AdminAddNewProductActivity.this, toastText, Toast.LENGTH_LONG).show();
            }
        });
    }

    //editing existing product
    public void editProduct(final int id) {

        //making the delete button visible
        deleteProductButton.setVisibility(View.VISIBLE);
        //getting the product from our database using the id
        Product product = productDAO.getProduct(id);
        //setting the product editTextTextAreas with the product values
        productURL.getEditText().setText(product.getImageURL());
        productTitle.getEditText().setText(product.getTitle());
        productQuantity.getEditText().setText("" + product.getQuantity());
        productPrice.getEditText().setText("" + product.getPrice());
        //deleting the product on click
        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDAO.deleteProduct(id);
                Toast.makeText(AdminAddNewProductActivity.this, "Product Deleted Successfully", Toast.LENGTH_LONG).show();
                productURL.getEditText().setText("");
                productTitle.getEditText().setText("");
                productQuantity.getEditText().setText("");
                productPrice.getEditText().setText("");
            }
        });
        //update product button short of...
        confirmAddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDAO.deleteProduct(id);
                String toastText = "Please Fill all the Available Fields";
                List<Product> products;
                if (!productURL.getEditText().getText().toString().trim().matches("") &&
                        !productTitle.getEditText().getText().toString().trim().matches("") &&
                        !productPrice.getEditText().getText().toString().trim().matches("") &&
                        !productQuantity.getEditText().getText().toString().trim().matches("")) {

                    try {
                        String URL = productURL.getEditText().getText().toString().trim();
                        String title = productTitle.getEditText().getText().toString().trim();
                        int quantity = Integer.parseInt(productQuantity.getEditText().getText().toString().trim());
                        double price = Double.parseDouble(productPrice.getEditText().getText().toString().trim());
                        String category = typesAvailable.get(productTypeSpinner.getSelectedIndex());
                        Product product = new Product(URL, title, quantity, price, category);
                        productDAO.insert(product);
                        toastText = "Product Updated Successfully!";

                        //blanking the fields after the product has been created
                        productURL.getEditText().setText("");
                        productTitle.getEditText().setText("");
                        productQuantity.getEditText().setText("");
                        productPrice.getEditText().setText("");
                        productTypeSpinner.setSelectedIndex(0);

                        products = productDAO.getAllProducts();
                        System.out.println(products.size());
                        System.out.println(products.get(0).getCategory());

                    } catch (NumberFormatException e) {
                        toastText = "Give the Right Values at the Given Fields";
                    }

                }
                Toast.makeText(AdminAddNewProductActivity.this, toastText, Toast.LENGTH_LONG).show();
            }
        });
    }

    //toolbar
    public void setToolbar() {
        //creating everything that will be contained in the drawer
        //on home nav home button click
        homeNav = new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(AdminAddNewProductActivity.this, MainMenuActivity.class);
                        intent.putExtra("userId", -1);
                        intent.putExtra("userType", "admin");
                        startActivity(intent);
                        return false;
                    }
                });
        orderHistory = new PrimaryDrawerItem().withName("Order History").withIcon(FontAwesome.Icon.faw_history).
                withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(AdminAddNewProductActivity.this, OrderShowcase.class);
                        intent.putExtra("userId", -1);
                        intent.putExtra("userType", "admin");
                        Toast.makeText(AdminAddNewProductActivity.this, "Order History", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        return false;
                    }
                });
        //on product availability click listener
        availability = new PrimaryDrawerItem().withName("Product Availability").withIcon(FontAwesome.Icon.faw_list).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(AdminAddNewProductActivity.this, ProductSearch.class);
                intent.putExtra("userId", -1);
                intent.putExtra("userType", "admin");
                startActivity(intent);
                return false;
            }
        });
        accountHeader = new AccountHeaderBuilder().withActivity(this).withTranslucentStatusBar(true)
                .addProfiles(new ProfileDrawerItem().withName("admin").withEmail("admin@admin.admin"))
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
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Logout").withIcon(FontAwesome.Icon.faw_sign_out).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(AdminAddNewProductActivity.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        }));
    }

}
