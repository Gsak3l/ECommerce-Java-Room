package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;

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
    TextInputLayout productURL;
    TextInputLayout productTitle;
    MaterialSpinner productTypeSpinner;
    TextInputLayout productQuantity;
    TextInputLayout productPrice;
    TextView live_tester;
    List<String> typesAvailable = new ArrayList<>();
    FloatingActionButton confirmAddProductButton;


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

        productURL = findViewById(R.id.add_new_product_imageURL);
        productTitle = findViewById(R.id.add_new_product_title);
        productQuantity = findViewById(R.id.add_new_product_quantity);
        productPrice = findViewById(R.id.add_new_product_price);
        productTypeSpinner = findViewById(R.id.add_new_product_category);
        confirmAddProductButton = findViewById(R.id.add_new_product_confirm_button);




        ArrayAdapter products = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typesAvailable);
        products.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productTypeSpinner.setAdapter(products);

        confirmAddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toastme = productURL.getEditText().getText() +  " "
                        + productTitle.getEditText().getText() + " "
                        + productQuantity.getEditText().getText() + " "
                        + productPrice.getEditText().getText() + " "
                        + typesAvailable.get(productTypeSpinner.getSelectedIndex());
                Toast.makeText(AdminAddNewProductActivity.this, toastme, Toast.LENGTH_LONG).show();
            }
        });

        /*productTypeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                live_tester.setText(item);
            }
        });
        */

    }
}
