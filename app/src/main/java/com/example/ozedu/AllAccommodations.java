package com.example.ozedu;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AllAccommodations extends AppCompatActivity {

    AccommodationAdapter adapter;
    DatabaseHelper db;

    EditText editText;

    List<Accommodation> accommodationsList;
    ListView listAccommodations;
    List<AccommodationAdapter> adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_accommodations);

        listAccommodations = (ListView) findViewById(R.id.listViewAccommodations);
        accommodationsList = new ArrayList<>();

        showAllAccommodations();

        editText = (EditText) findViewById(R.id.searchView);

        adapterList = new ArrayList<AccommodationAdapter>();
        listAccommodations.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                AllAccommodations.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg) {
            }
        });
    }

    private void showAllAccommodations() {

        Cursor cursorAccommodations = db.selectAccommodation();

        if (cursorAccommodations.moveToFirst()) {

            do {
                accommodationsList.add(new Accommodation(
                        cursorAccommodations.getString(0),
                        cursorAccommodations.getString(1),
                        cursorAccommodations.getString(2),
                        cursorAccommodations.getString(3),
                        cursorAccommodations.getString(4)
                ));
            } while (cursorAccommodations.moveToNext());
        }
        cursorAccommodations.close();

        adapter = new AccommodationAdapter(this,  R.layout.accommodation, accommodationsList,db);

        listAccommodations.setAdapter(adapter);
    }
}
