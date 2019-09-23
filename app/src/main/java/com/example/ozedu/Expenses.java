package com.example.ozedu;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Expenses extends AppCompatActivity {

    WebView webView;
    String food,TraMode;
    String accom;
    Button btn_go;

    Spinner foodSpin,transportSpin,accommodationSpin;

    public String fileName = "ExpensesDetails.html";
    Spinner FoodOptions, TransportOptions, AccommodationOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses);

        webView = (WebView) findViewById(R.id.expensesWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/" + fileName);

        btn_go = (Button) findViewById(R.id.go);

        FillFoodOptions();
        FillTransportOptions();
        FillAccommodationOptions();
        calculateExpenses(food,TraMode,accom);

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float amount = calculateExpenses(getFoodOption(),getTransportOption(),getAccommodationOption());

                TextView text = (TextView) findViewById(R.id.textExpenses);
                text.setText(String.valueOf(amount));
            }
        });
    }

    public void FillFoodOptions() {

        FoodOptions  = (Spinner) findViewById(R.id.spinner_food);
        List<String> list = new ArrayList<String>();
        list.add("Foodie");
        list.add("Moderate");
        list.add("Only to Survive");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FoodOptions.setAdapter(dataAdapter);
    }

    public void FillTransportOptions() {

        TransportOptions  = (Spinner) findViewById(R.id.spinner_transport);
        List<String> list = new ArrayList<String>();
        list.add("By Car");
        list.add("Public");
        list.add("Carpool");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportOptions.setAdapter(dataAdapter);
    }

    public void FillAccommodationOptions() {

        AccommodationOptions  = (Spinner) findViewById(R.id.spinner_accommodation);
        List<String> list = new ArrayList<String>();
        list.add("Apartment");
        list.add("House");
        list.add("Room");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AccommodationOptions.setAdapter(dataAdapter);
    }

    private float calculateExpenses(String food, String traMode, String accomMode) {
        float foodExpenses = 0.0f;
        float transportExpenses = 0.0f;
        float accommodationExpenses = 0.0f;
        if(food == "Foodie"){
            foodExpenses = 250 ;
        }
        else if(food == "Moderate"){
            foodExpenses = 175;
        }
        else if (food == "Only to Survive"){
            foodExpenses = 100;
        }

        if(traMode == "By Car"){
            transportExpenses = 350 ;
        }
        else if(traMode == "Public"){
            transportExpenses = 300;
        }
        else if (traMode == "Carpool"){
            transportExpenses = 200;
        }

        if(accomMode == "Apartment"){
            accommodationExpenses = 500 ;
        }
        else if(accomMode == "House"){
            accommodationExpenses = 400;
        }
        else if (accomMode == "Room"){
            accommodationExpenses = 200;
        }

        return (foodExpenses + transportExpenses + accommodationExpenses);
    }

    public String getFoodOption() {
        Spinner foodSpin = (Spinner) findViewById(R.id.spinner_food);
        String food = foodSpin.getSelectedItem().toString();
        return food;
    }
    public String getTransportOption() {
        Spinner transportSpin = (Spinner) findViewById(R.id.spinner_transport);
        String transport = transportSpin.getSelectedItem().toString();
        return transport;
    }

    public String getAccommodationOption() {
        Spinner AccommodationOptions = (Spinner) findViewById(R.id.spinner_accommodation);
        String accommodation = AccommodationOptions.getSelectedItem().toString();
        return accommodation;
    }
}

