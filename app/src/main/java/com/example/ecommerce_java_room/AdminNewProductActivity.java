package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminNewProductActivity extends AppCompatActivity {

    private String categoryName;
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_product);
        categoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();
        initImageBitmaps();

    }

    private void initImageBitmaps() {
        mImageUrls.add("https://i.ibb.co/nmHhCSd/1959wallpaper.png");
        mTitles.add("1");

        mImageUrls.add("https://i.ibb.co/GWsQnWf/A-Light-in-the-Evening.png");
        mTitles.add("2");

        mImageUrls.add("https://i.ibb.co/nRc3gcK/Autumn-Sale-Store-Header.png");
        mTitles.add("3");

        mImageUrls.add("https://i.ibb.co/PQVzDWF/Baby-Yoda-and-Pikachu.png");
        mTitles.add("4");

        mImageUrls.add("https://i.ibb.co/kJdR7KQ/black-raven.jpg");
        mTitles.add("5");

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mTitles, mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
