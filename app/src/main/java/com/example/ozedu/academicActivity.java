package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class academicActivity extends AppCompatActivity {
    EditText enic,eol,eals,er1,er2,er3,ez;
    Button add2,update2,delete2,view2;
    SQLiteDatabase DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);

        enic = (EditText) findViewById(R.id.nic);
        eol = (EditText) findViewById(R.id.ol);
        eals = (EditText) findViewById(R.id.als);
        er1=(EditText) findViewById(R.id.r1);
        er2=(EditText) findViewById(R.id.r2);
        er3=(EditText)findViewById(R.id.r3);
        ez=(EditText) findViewById(R.id.z);

        add2 = (Button) findViewById(R.id.add2);
        delete2 = (Button) findViewById(R.id.delete2);
        view2 = (Button) findViewById(R.id.view2);
        update2 = (Button) findViewById(R.id.update2);


        DB = openOrCreateDatabase("college_app", Context.MODE_PRIVATE, null);
        DB.execSQL("CREATE TABLE IF NOT EXISTS coll_a(nic VARCHAR,ol VARCHAR,al VARCHAR,r1 VARCHAR,r2 VARCHAR,r3 VARCHAR,z DOUBLE);");

        add2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (enic.getText().toString().trim().length() == 0 ||
                        eol.getText().toString().trim().length() == 0 ||
                        eals.getText().toString().trim().length() == 0 ||
                        er1.getText().toString().trim().length() == 0 ||
                        er2.getText().toString().trim().length() == 0 ||
                        er3.getText().toString().trim().length() == 0 ||
                        ez.getText().toString().trim().length() == 0 ) {
                    showMessage("Error","Add the Records");
                    return;
                }

                if (enic.getText() != null) {

                    DB.execSQL("INSERT INTO coll_a VALUES('" + enic.getText() + "' ,  '" + eol.getText() + "'  , '" + eals.getText() + "' , '" + er1.getText() + "' , '" + er2.getText() + "' , '" + er3.getText() + "' , '" + ez.getText() + "'  ) ; ");

                    showMessage("Success","Records added succesfully");
                    clearText()
                    ;
                } else {
                    showMessage("Error","NIC invalid");
                    return;
                }
            }
        });

        delete2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (enic.getText().toString().trim().length() == 0) {
                    showMessage("Error", "please enter the NIC");
                    return;
                }

                Cursor c = DB.rawQuery("SELECT * FROM coll_a WHERE nic = '" + enic.getText() + "'", null);

                if (c.moveToFirst()) {
                    DB.execSQL("DELETE FROM coll_a WHERE nic= '" + enic.getText() + "'");
                    showMessage("Success", "Record deleted");
                } else {
                    showMessage("error", "Invalid NIC");
                }
                clearText();


            }
        });
        update2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (enic.getText().toString().trim().length() == 0) {
                    showMessage("Error", "please enter NIC");
                    return;
                }

                Cursor c = DB.rawQuery("SELECT * FROM coll_a WHERE nic = '" + enic.getText() + "'", null);

                if (c.moveToFirst()) {
                    DB.execSQL(" UPDATE coll_a SET ol = '"+ eol.getText() +"' , als = '"+ eals.getText() +"' , r1 = '"+ er1.getText() +"' , r2 = '"+ er2.getText() +"' , r3 = '"+ er3.getText() +"' , z = '"+ ez.getText() +"'  WHERE nic = '"+ enic.getText() +"' ");
                    showMessage("Success", "Record modified");
                } else {
                    showMessage("error", "Invalid NIC");
                }
                clearText();
            }


        });
        view2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (enic.getText().toString().trim().length() == 0) {
                    showMessage("Error", "please enter NIC");
                    return;
                }

                Cursor c = DB.rawQuery("SELECT * FROM coll_a WHERE nic = '" + enic.getText() + "'", null);

                if (c.moveToFirst()) {
                    eol.setText(c.getString(1));
                    eals.setText(c.getString(2));
                    er1.setText(c.getString(3));
                    er2.setText(c.getString(4));
                    er3.setText(c.getString(5));
                    ez.setText(c.getString(6));

                } else {
                    showMessage("error", "Invalid NIC");
                    clearText();
                }

            }
        });

//on
    }
    public void clearText(){

        enic.setText("");
        eol.setText("");
        eals.setText("");
        er1.setText("");
        er2.setText("");
        er3.setText("");
        ez.setText("");
        enic.requestFocus();
    }
    public void showMessage(String title,String message){

        Builder builder= new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}//end
