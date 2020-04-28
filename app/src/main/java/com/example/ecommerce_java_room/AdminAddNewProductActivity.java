package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.ProductDAO;
import com.example.ecommerce_java_room.data.ProductDatabase;
import com.example.ecommerce_java_room.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.w3c.dom.Text;

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
    FloatingActionButton confirmAddProductButton;

    //database stuff
    private ProductDAO productDAO;
    private ProductDatabase productDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        //adding available categories to our product spinner
        typesAvailable.add("T Shirts for Men");
        typesAvailable.add("Trousers for Men");
        typesAvailable.add("Sweaters for Men");
        typesAvailable.add("Shoes for Men");
        typesAvailable.add("Dresses for Women");
        typesAvailable.add("Trousers for Women");
        typesAvailable.add("Skirts for Women");
        typesAvailable.add("Shoes for Women");
        typesAvailable.add("Hats");
        typesAvailable.add("Purses");
        typesAvailable.add("Watches");
        typesAvailable.add("Scarfs");

        //finding all the elements of our xml file
        productURL = findViewById(R.id.add_new_product_imageURL);
        productTitle = findViewById(R.id.add_new_product_title);
        productQuantity = findViewById(R.id.add_new_product_quantity);
        productPrice = findViewById(R.id.add_new_product_price);
        productTypeSpinner = findViewById(R.id.add_new_product_category);
        confirmAddProductButton = findViewById(R.id.add_new_product_confirm_button);

        //giving the available values of the typesAvailable list to the spinner
        final ArrayAdapter products = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typesAvailable);
        products.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productTypeSpinner.setAdapter(products);

        //allowing queries
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "Product")
                .allowMainThreadQueries().build().getProductDao();

        confirmAddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toastText = "Please Fill all the Available Fields";
                List<Product> products = productDAO.getAllProducts();
                System.out.println(products.size());
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
}
