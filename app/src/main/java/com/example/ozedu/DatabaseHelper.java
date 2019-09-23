package com.example.ozedu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    Spinner states,cuisine;
    CuisinesAvailableInDifferentStates getStates,getCuisines;
    private static final int DATABASE_VERSION = 8;

    // Database Name
    private static final String DATABASE_NAME = "AustralianEducation.db";
    private static final String TABLE1_NAME = "State";
    private static final String COLUMN1_NAME = "State_ID";
    private static final String COLUMN2_NAME = "State_name";

    private static final String TABLE2_NAME = "Restaurant";
    private static final String COLUMN1_ID = "Restaurant_ID";
    private static final String COLUMN2_RESTAURANTNAME = "Restaurant_name";
    private static final String COLUMN3_CUISINENAME = "Cuisine_name";
    private static final String COLUMN4_STATENAME = "State_name";

    private static final String TABLE3_NAME = "Cuisine";
    private static final String COLUMN2_CUISINENAME = "Cuisine_name";
    private static final String COLUMN3_StateName = "State_Cuisine_name";

    private static final String TABLE4_NAME = "Accommodation";
    private static final String ACCOMMODATION_ID = "Accommodation_ID";
    private static final String ACCOMMODATION_NAME = "Accommodation_Name";
    private static final String ADDRESS = "Address";
    private static final String CONTACT_NO = "Contact_No";
    private static final String CONTACT_PERSON = "Contact_Person";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {

            String TABLE_STATE = "CREATE TABLE " + TABLE1_NAME + "("
                    + COLUMN1_NAME + " TEXT ,"
                    + COLUMN2_NAME + " TEXT PRIMARY KEY )";

            sqLiteDatabase.execSQL(TABLE_STATE);


            String TABLE_RESTAURANT = "CREATE TABLE Restaurant"  + "("
                    + COLUMN1_ID + " TEXT  PRIMARY KEY ,"
                    + COLUMN2_RESTAURANTNAME + " TEXT ,"
                    + COLUMN3_CUISINENAME + " TEXT ,"
                    + COLUMN4_STATENAME + " TEXT )";

            System.out.println("I am then here");

            sqLiteDatabase.execSQL(TABLE_RESTAURANT);

            String TABLE_CUISINE = "CREATE TABLE " + TABLE3_NAME + "("
                    + COLUMN2_CUISINENAME + " INTEGER PRIMARY KEY ,"
                    + COLUMN3_StateName + " TEXT )";

            sqLiteDatabase.execSQL(TABLE_CUISINE);

            String TABLE_ACCOMMODATION = "CREATE TABLE Accommodation" + "("
                    + ACCOMMODATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + ACCOMMODATION_NAME + " TEXT NOT NULL,"
                    + ADDRESS + " TEXT NOT NULL,"
                    + CONTACT_NO + " TEXT NOT NULL,"
                    + CONTACT_PERSON + " TEXT NOT NULL)";

            sqLiteDatabase.execSQL(TABLE_ACCOMMODATION);

        }
        catch (Exception ex){
            System.out.println("I am here");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        if(newVersion >oldVersion) {

            onCreate(sqLiteDatabase);
        }
    }
    public boolean insertData_State(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COLUMN1_NAME,"NSW");
        contentvalues.put(COLUMN1_NAME,"QLD");
        contentvalues.put(COLUMN1_NAME,"TAS");
        contentvalues.put(COLUMN1_NAME,"SA");
        contentvalues.put(COLUMN1_NAME,"VIC");
        contentvalues.put(COLUMN1_NAME,"WA");

        contentvalues.put(COLUMN2_NAME,"New South Wales");
        contentvalues.put(COLUMN2_NAME,"Queensland");
        contentvalues.put(COLUMN2_NAME,"Tasmania");
        contentvalues.put(COLUMN2_NAME,"South Australia");
        contentvalues.put(COLUMN2_NAME,"Victoria");
        contentvalues.put(COLUMN2_NAME,"Western Australia");
        long result = sqLiteDatabase.insert(TABLE1_NAME,null,contentvalues);
        if(result == -1){
            return false;
        }
        else
            return true;
    }
    public boolean insertData_Restaurant(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentvalues = new ContentValues();

        try {
            contentvalues.put(COLUMN2_RESTAURANTNAME, "https:www.tripadvisor.com/Restaurants-g255060-c2-Sydney_New_South_Wales.html");
            contentvalues.put(COLUMN3_CUISINENAME, "American");
            contentvalues.put(COLUMN4_STATENAME, "New South Wales");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255093-c26-Adelaide_Greater_Adelaide_South_Australia.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Italian");
            contentvalues.put(COLUMN4_STATENAME, "New South Wales");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com.au/Restaurants-g255093-c11-Adelaide_Greater_Adelaide_South_Australia.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Chinese");
            contentvalues.put(COLUMN4_STATENAME, "New South Wales");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255060-c24-Sydney_New_South_Wales.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Indian");
            contentvalues.put(COLUMN4_STATENAME, "New South Wales");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/ShowUserReviews-g1049646-d5097758-r308759127-B_U-Marion_Greater_Adelaide_South_Australia.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Sri Lankan");
            contentvalues.put(COLUMN4_STATENAME, "New South Wales");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255068-c2-Brisbane_Brisbane_Region_Queensland.html");
            contentvalues.put(COLUMN3_CUISINENAME, "American");
            contentvalues.put(COLUMN4_STATENAME, "Queensland");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255337-c26-Gold_Coast_Queensland.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Italian");
            contentvalues.put(COLUMN4_STATENAME, "Queensland");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255068-c11-Brisbane_Brisbane_Region_Queensland.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Chinese");
            contentvalues.put(COLUMN4_STATENAME, "Queensland");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255068-c24-Brisbane_Brisbane_Region_Queensland.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Indian");
            contentvalues.put(COLUMN4_STATENAME, "Queensland");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.bestrestaurants.com.au/qld/brisbane/all-suburbs/cuisines-sri-lankan/all-features/search");
            contentvalues.put(COLUMN3_CUISINENAME, "Sri Lankan");
            contentvalues.put(COLUMN4_STATENAME, "Queensland");

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255097-c2-Hobart_Greater_Hobart_Tasmania.html");
            contentvalues.put(COLUMN3_CUISINENAME, "American");
            contentvalues.put(COLUMN4_STATENAME, "Tasmania");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255097-c26-Hobart_Greater_Hobart_Tasmania.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Italian");
            contentvalues.put(COLUMN4_STATENAME, "Tasmania");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255097-c11-Hobart_Greater_Hobart_Tasmania.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Chinese");
            contentvalues.put(COLUMN4_STATENAME, "Tasmania");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255097-c24-Hobart_Greater_Hobart_Tasmania.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Indian");
            contentvalues.put(COLUMN4_STATENAME, "Tasmania");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/ShowUserReviews-g504291-d3601811-r474377375-Derwent_Bridge_Wilderness_Hotel-Derwent_Bridge_Tasmania.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Sri Lankan");
            contentvalues.put(COLUMN4_STATENAME, "Tasmania");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);


            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255100-c2-Melbourne_Victoria.html");
            contentvalues.put(COLUMN3_CUISINENAME, "American");
            contentvalues.put(COLUMN4_STATENAME, "Victoria");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255100-c26-Melbourne_Victoria.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Italian");
            contentvalues.put(COLUMN4_STATENAME, "Victoria");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255100-c11-Melbourne_Victoria.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Chinese");
            contentvalues.put(COLUMN4_STATENAME, "Victoria");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255100-c24-Melbourne_Victoria.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Indian");
            contentvalues.put(COLUMN4_STATENAME, "Victoria");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "http://www.upalis.com/melbourne/menu/default.aspx");
            contentvalues.put(COLUMN3_CUISINENAME, "Sri Lankan");
            contentvalues.put(COLUMN4_STATENAME, "Victoria");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);


            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255103-c2-Perth_Greater_Perth_Western_Australia.html");
            contentvalues.put(COLUMN3_CUISINENAME, "American");
            contentvalues.put(COLUMN4_STATENAME, "Western Australia");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255103-c26-Perth_Greater_Perth_Western_Australia.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Italian");
            contentvalues.put(COLUMN4_STATENAME, "Western Australia");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255103-c11-Perth_Greater_Perth_Western_Australia.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Chinese");
            contentvalues.put(COLUMN4_STATENAME, "Western Australia");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.tripadvisor.com/Restaurants-g255103-c24-Perth_Greater_Perth_Western_Australia.html");
            contentvalues.put(COLUMN3_CUISINENAME, "Indian");
            contentvalues.put(COLUMN4_STATENAME, "Western Australia");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);

            contentvalues.put(COLUMN2_RESTAURANTNAME, "https://www.zomato.com/perth/restaurants/sri-lankan");
            contentvalues.put(COLUMN3_CUISINENAME, "Sri Lankan");
            contentvalues.put(COLUMN4_STATENAME, "Western Australia");

            sqLiteDatabase.insert(TABLE2_NAME, null, contentvalues);
        }
        catch (Exception ex){
            return false;
        }
        return true;
    }
    public List<RestaurantWrapper> getRestaurants(String res1, String res2){
        List<String> restaurants = new ArrayList<String>();
        //  String res1 = "Western Australia";
        //   String res2 = "Sri lankan";

        System.out.println("res 1 : " + res1);
        System.out.println("res 2 : " + res2);
        // Log.d("res1", res1);
        // Log.d("res2", res2);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN2_RESTAURANTNAME + "," +COLUMN3_CUISINENAME +"," +COLUMN4_STATENAME+ " FROM " + TABLE2_NAME +
                " WHERE " + COLUMN4_STATENAME +" = ?  AND "+ COLUMN3_CUISINENAME + "=? ", new String[] {res1,res2});


        List<RestaurantWrapper> restaurantsList = new ArrayList<RestaurantWrapper>();

        while(cursor.moveToNext()) {
            int index;

            index = cursor.getColumnIndexOrThrow(COLUMN2_RESTAURANTNAME);
            String restName = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow(COLUMN3_CUISINENAME);
            String cuisineName = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow(COLUMN4_STATENAME);
            String stateName = cursor.getString(index);

            RestaurantWrapper res = new RestaurantWrapper();
            res.setState(stateName);
            res.setCuisine(cuisineName);
            res.setRestaurant(restName);

            restaurantsList.add(res);


        }
        cursor.close();
        db.close();

        return restaurantsList;
    }

    public boolean insertAccommodation(String name,String address,String contactNumber, String contactPerson){
        System.out.println("I am here");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(ACCOMMODATION_NAME,name);
        contentvalues.put(ADDRESS,address);
        contentvalues.put(CONTACT_NO,contactNumber);
        contentvalues.put(CONTACT_PERSON,contactPerson);

        if((name != null && !name.isEmpty())  &&
                (address != null && !address.isEmpty())  &&
                (contactNumber != null && !contactNumber.isEmpty())  &&
                (contactPerson != null && !contactPerson.isEmpty())) {

            long result = sqLiteDatabase.insert(TABLE4_NAME, null, contentvalues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return  false;
        }
    }

    public void updateAccommodation(String sql, String[] array){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();



        sqLiteDatabase.execSQL(sql, array);

    }

    public void deleteAccommodation(String sql, String[] array){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();



        sqLiteDatabase.execSQL(sql, array);

    }

    public Cursor  selectAccommodation(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String sql = "SELECT * FROM  " + TABLE4_NAME ;

        Cursor data  = sqLiteDatabase.rawQuery(sql,null);

        return  data;

    }
}
