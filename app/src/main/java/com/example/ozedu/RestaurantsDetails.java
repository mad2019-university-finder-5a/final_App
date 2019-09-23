package com.example.ozedu;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RestaurantsDetails extends AppCompatActivity {

    WebView webView;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_details);

        webView = (WebView) findViewById(R.id.restaurantWebView);

        Intent intent = getIntent();
        String state = intent.getStringExtra("State");
        String cus = intent.getStringExtra("Cuisine");

        myDb = new DatabaseHelper(this);
        final List<RestaurantWrapper> restList = myDb.getRestaurants(state,cus);

        webView.getSettings().setJavaScriptEnabled(true);

        String stateName = "";
        String cusiine = "";
        String restName = "";
        if ((restList.size() > 0 )&& (restList != null)) {
            stateName = restList.get(0).getState();
            cusiine = restList.get(0).getCuisine();
            restName = restList.get(0).getRestaurant();
        }
        webView.loadUrl(restName);
    }
}
