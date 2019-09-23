package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class personalActivity extends AppCompatActivity {
    EditText ename,eemail,ephone;
    Button add,delete,view,update;
    SQLiteDatabase DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        ename = (EditText) findViewById(R.id.name);
        eemail = (EditText) findViewById(R.id.email);
        ephone = (EditText) findViewById(R.id.phone);

        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);
        view = (Button) findViewById(R.id.view);
        update = (Button) findViewById(R.id.update);

        DB = openOrCreateDatabase("college_app", Context.MODE_PRIVATE, null);
        DB.execSQL("CREATE TABLE IF NOT EXISTS coll_p(name VARCHAR,email VARCHAR,phone INTEGER);");

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (ename.getText().toString().trim().length() == 0 ||
                        eemail.getText().toString().trim().length() == 0 ||
                        ephone.getText().toString().trim().length() == 0) {
                    showMessage("Error","Add the Records");
                    return;
                }

                if (ephone.getText() != null) {

                    DB.execSQL("INSERT INTO coll_p VALUES('" + ename.getText() + "' ,  '" + eemail.getText() + "'  , '" + ephone.getText() + "' ) ; ");

                    showMessage("Success","Records added succesfully");
                    clearText()
                    ;
                } else {
                    showMessage("Error","Phone number invalid");
                    return;
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (ephone.getText().toString().trim().length() == 0) {
                    showMessage("Error", "please enter the phone number");
                    return;
                }

                Cursor c = DB.rawQuery("SELECT * FROM coll_p WHERE phone = '" + ephone.getText() + "'", null);

                if (c.moveToFirst()) {
                    DB.execSQL("DELETE FROM coll_p WHERE phone= '" + ephone.getText() + "'");
                    showMessage("Success", "Record deleted");
                } else {
                    showMessage("error", "Invalid Phone Number");
                }
                clearText();


            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (ephone.getText().toString().trim().length() == 0) {
                    showMessage("Error", "please enter phone number");
                    return;
                }

                Cursor c = DB.rawQuery("SELECT * FROM coll_p WHERE phone = '" + ephone.getText() + "'", null);

                if (c.moveToFirst()) {
                    DB.execSQL("UPDATE coll_p SET name = '" + ename.getText() + "' , email='" + eemail.getText() + "'  WHERE phone='" + ephone.getText() + "' ");
                    showMessage("Sucess", "Record modified");
                } else {
                    showMessage("error", "Invalid Phone Number");
                }
                clearText();
            }


        });


        view.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (ephone.getText().toString().trim().length() == 0) {
                    showMessage("Error", "please enter phone number");
                    return;
                }

                Cursor c = DB.rawQuery("SELECT * FROM coll_p WHERE phone = '" + ephone.getText() + "'", null);

                if (c.moveToFirst()) {
                    ename.setText(c.getString(0));
                    eemail.setText(c.getString(1));


                } else {
                    showMessage("error", "Invalid PhoneNo");
                    clearText();
                }

            }
        });

    }

    public void clearText(){

        ephone.setText("");
        ename.setText("");
        eemail.setText("");
        ephone.requestFocus();
    }


    public void showMessage(String title,String message){

        Builder builder= new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
