package com.example.ozedu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LivingInAustralia extends AppCompatActivity {

    private Button btn_accommodation,btn_restaurant,btn_expenses,btn_permamnentResidency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.living_in_australia);

        btn_accommodation = (Button) findViewById(R.id.buttonAccommodation);
        btn_restaurant = (Button) findViewById(R.id.buttonRestaurant);
        btn_expenses = (Button)findViewById(R.id.buttonExpenses) ;
        btn_permamnentResidency = (Button)findViewById(R.id.buttonPermamnentResidency);


        btn_accommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToAccommodation();
            }
        });

        btn_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToRestaurantFinder();
            }
        });

        btn_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToExpensesCalculator();
            }
        });

        btn_permamnentResidency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToPermanentResidencyOptions();
            }
        });
    }

    public void redirectToAccommodation(){
        Intent intent = new Intent(this, AccommodationCreator.class);
        startActivity(intent);
    }
    public void redirectToRestaurantFinder(){
        Intent intent = new Intent(this, CuisinesAvailableInDifferentStates.class);
        startActivity(intent);
    }
    public void redirectToExpensesCalculator(){
        Intent intent = new Intent(this, Expenses.class);
        startActivity(intent);
    }
    public void redirectToPermanentResidencyOptions(){
        Intent intent = new Intent(this, SettlingInAustralia.class);
        startActivity(intent);
    }
}
