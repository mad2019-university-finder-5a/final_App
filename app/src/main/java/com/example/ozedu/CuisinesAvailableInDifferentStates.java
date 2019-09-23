package com.example.ozedu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CuisinesAvailableInDifferentStates extends AppCompatActivity {

    Spinner AllStates,AllCuisines;
    Button btn_go;
    DatabaseHelper myDb;
    WebView webView;

    public String fileName = "CuisinesAvailableInDifferentStates.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuisines_available_in_different_states);

        btn_go = (Button) findViewById(R.id.go);

        AllStates = (Spinner) findViewById(R.id.spinner_state);

        myDb = new DatabaseHelper(this);
        myDb.insertData_State();
        myDb.insertData_Restaurant();

        webView = (WebView) findViewById(R.id.italianWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/" + fileName);

        addItemsOnSpinnerStates();
        addItemsOnSpinnerCuisine();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        getState();
        getCuisine();

        btn_go.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                RestaurantDescriptionActivity();
            }
        });
    }
    public void RestaurantDescriptionActivity(){
        Intent intent = new Intent(this, RestaurantsDetails.class);
        intent.putExtra("State",getState());
        intent.putExtra("Cuisine",getCuisine());
        startActivity(intent);
    }
    public String getState() {
        Spinner States = (Spinner) findViewById(R.id.spinner_state);
        String state = States.getSelectedItem().toString();
        return state;
    }
    public String getCuisine() {
        Spinner Cuisines = (Spinner) findViewById(R.id.spinner_cuisine);
        String cuisine = Cuisines.getSelectedItem().toString();
        return cuisine;
    }

    public void addItemsOnSpinnerCuisine() {

        AllCuisines = (Spinner) findViewById(R.id.spinner_cuisine);
        List<String> list = new ArrayList<String>();
        list.add("American");
        list.add("Italian");
        list.add("Chinese");
        list.add("Indian");
        list.add("Sri Lankan");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AllCuisines.setAdapter(dataAdapter);
    }
    public void addItemsOnSpinnerStates() {

        AllStates = (Spinner) findViewById(R.id.spinner_state);
        List<String> list = new ArrayList<String>();
        list.add("New South Wales");
        list.add("Queensland");
        list.add("Tasmania");
        list.add("Victoria");
        list.add("Western Australia");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AllStates.setAdapter(dataAdapter);
    }
    public void addListenerOnSpinnerItemSelection() {
        AllStates = (Spinner) findViewById(R.id.spinner_state);
        AllStates.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        AllCuisines = (Spinner) findViewById(R.id.spinner_cuisine);
        AllCuisines.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    public void addListenerOnButton() {

        AllStates = (Spinner) findViewById(R.id.spinner_state);
        AllCuisines = (Spinner) findViewById(R.id.spinner_cuisine);
        btn_go = (Button) findViewById(R.id.go);

        btn_go.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(CuisinesAvailableInDifferentStates.this,
                        String.valueOf(AllStates.getSelectedItem()) +"\n"+String.valueOf(AllCuisines.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}






