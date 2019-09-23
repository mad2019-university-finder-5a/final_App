package com.example.ozedu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccommodationCreator extends AppCompatActivity {

    DatabaseHelper db;
    Button addAccommodation,viewAccommodation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accommodation_creator);
        db = new DatabaseHelper(this);

        addAccommodation = (Button)findViewById(R.id.buttonAddAccommodation);
        viewAccommodation = (Button)findViewById(R.id.buttonViewAccommodation);

        addAccommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AccommodationCreator.this, "Add Accommodation Clicked", Toast.LENGTH_LONG).show();

                String name = getName();
                String address = getAddress();
                String phone = getContactNo();
                String contactPerson = getContactPerson();

                if ((name != null ) && (address != null ) &&  (phone != null) && (contactPerson != null )) {
                    Boolean resultInsert = db.insertAccommodation(getName(), getAddress(), getContactNo(), getContactPerson());
                    String header = "";
                    String message = "";

                    if (resultInsert) {
                        header = "Success";
                        message = "Accommodation Details Added Successfully!";

                    } else {
                        header = "Failure";
                        message = "An Error Occurred. Try Again!";
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(AccommodationCreator.this);
                    builder.setMessage(message)
                            .setTitle(header)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(), AccommodationCreator.class);
                                    startActivity(i);
                                }
                            }).show();

                }
            }
        });
        viewAccommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccommodationCreator.this, AllAccommodations.class);
                startActivity(intent);
            }
        });
    }

    public String getName(){
        TextView value = (TextView)findViewById(R.id.Name);
        String regex = "^[a-zA-Z ]+";
        Pattern pattern = Pattern.compile(regex);
        String name = value.getText().toString();
        Matcher matcher = pattern.matcher(name);
        if(!matcher.matches()) {
            value.setError("Name should contain only letters and spaces");
            return null;
        }
        return name;
    }
    public String getAddress(){
        TextView value = (TextView)findViewById(R.id.Address);
        String regex = "^[a-zA-Z0-9 ]+";
        Pattern pattern = Pattern.compile(regex);
        String address = value.getText().toString();
        Matcher matcher = pattern.matcher(address);
        if(!matcher.matches()) {
            value.setError("Address should contain only letters, digits and spaces");
            return null;
        }
        return address;
    }
    public String getContactNo(){
        TextView value = (TextView)findViewById(R.id.ContactNo);
        String regex = "^[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
        Pattern pattern = Pattern.compile(regex);
        String phone = value.getText().toString();
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.matches()) {
            value.setError("Phone should be of 10 digits with a hyphen(-) after the 3rd digit");
            return null;
        }
        return phone;
    }
    public String getContactPerson() {
        TextView value = (TextView) findViewById(R.id.ContactPerson);
        String regex = "^[a-zA-Z ]+";
        Pattern pattern = Pattern.compile(regex);
        String contactPerson = value.getText().toString();
        Matcher matcher = pattern.matcher(contactPerson);
        if(!matcher.matches()) {
            value.setError("Contact Person should contain only letters and spaces");
            return null;
        }
        return contactPerson;
    }
}
