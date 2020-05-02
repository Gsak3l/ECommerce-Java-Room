package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

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
    private ImageView accessories_purse;
    private ImageView accessories_scarf;
    private ImageView accessories_hat;
    private ImageView accessories_watch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
}
