package com.example.ozedu;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;


public class AccommodationAdapter extends ArrayAdapter<Accommodation> {

    Context mCtx;
    int listLayoutRes;
    List<Accommodation> accommodationsList;
    DatabaseHelper mDatabase;
    private Filter filter;

    public AccommodationAdapter(Context mCtx, int listLayoutRes, List<Accommodation> accommodationsList, DatabaseHelper mDatabase) {
        super(mCtx, listLayoutRes, accommodationsList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.accommodationsList = accommodationsList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        final Accommodation accommodation = accommodationsList.get(position);

        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewAddress = view.findViewById(R.id.textViewAddress);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);
        TextView textViewContactPerson = view.findViewById(R.id.textViewContactPerson);


        textViewName.setText(accommodation.getName());
        textViewAddress.setText(accommodation.getAddress());
        textViewPhone.setText(String.valueOf(accommodation.getPhone()));
        textViewContactPerson.setText(accommodation.getContactPerson());

        Button buttonDelete = view.findViewById(R.id.buttonDelete);
        Button buttonEdit = view.findViewById(R.id.buttonEdit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                updateAccommodation(accommodation);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM Accommodation WHERE Accommodation_ID = ?";
                        mDatabase.deleteAccommodation(sql,new String[]{String.valueOf(accommodation.getId())} );
                        accommodationsList.remove(accommodation);
                        reloadAccommodations();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    private void updateAccommodation(final Accommodation accommodation) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.update_accommodation, null);
        builder.setView(view);


        final EditText editTextName = view.findViewById(R.id.editAccommodationName);
        final EditText editTextAddress = view.findViewById(R.id.editAccommodationAddress);
        final EditText editTextContactNumber = view.findViewById(R.id.editAccommodationContactNumber);
        final EditText editTextContactPerson = view.findViewById(R.id.editAccommodationContactPerson);

        editTextName.setText(accommodation.getName());
        editTextAddress.setText(accommodation.getAddress());
        editTextContactNumber.setText(accommodation.getPhone());
        editTextContactPerson.setText(accommodation.getContactPerson());

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.buttonUpdateAccommodation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sql = "UPDATE " + "Accommodation" +"\n" +
                        "SET " + "Accommodation_Name" + "= ?, \n" +
                        "Address" + "= ?, \n" +
                        "Contact_No" + "= ?, \n" +
                        "Contact_Person" +"= ? \n" +
                        "WHERE Accommodation_ID = ?;\n";


                mDatabase.updateAccommodation(sql, new String[]{editTextName.getText().toString(), editTextAddress.getText().toString(), editTextContactNumber.getText().toString(), editTextContactPerson.getText().toString(),String.valueOf(accommodation.getId())});
                Toast.makeText(mCtx, "Accommodation Updated", Toast.LENGTH_SHORT).show();
                reloadAccommodations();
                dialog.dismiss();
            }
        });
    }

    private void reloadAccommodations() {
        Cursor cursorAccommodation = mDatabase.selectAccommodation();
        System.out.println("accommodationsList: "+cursorAccommodation.moveToFirst());
        if (cursorAccommodation.moveToFirst()) {
            accommodationsList.clear();
            do {
                accommodationsList.add(new Accommodation(
                        cursorAccommodation.getString(0),
                        cursorAccommodation.getString(1),
                        cursorAccommodation.getString(2),
                        cursorAccommodation.getString(3),
                        cursorAccommodation.getString(4)
                ));
            } while (cursorAccommodation.moveToNext());
        }

        cursorAccommodation.close();
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new AppFilter<Accommodation>(accommodationsList);
        return filter;
    }

    private class AppFilter<T> extends Filter {

        private ArrayList<T> sourceObjects;

        public AppFilter(List<T> objects) {
            sourceObjects = new ArrayList<T>();
            synchronized (this) {
                sourceObjects.addAll(objects);
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence chars) {
            String filterSeq = chars.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (filterSeq != null && filterSeq.length() > 0) {
                ArrayList<T> filter = new ArrayList<T>();

                for (Accommodation object : (List<Accommodation>)sourceObjects) {
                    if (object.name.toString().toLowerCase().contains(filterSeq))
                        filter.add((T)object);
                }
                result.count = filter.size();
                result.values = filter;
            } else {
                synchronized (this) {
                    result.values = sourceObjects;
                    result.count = sourceObjects.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            ArrayList<T> filtered = (ArrayList<T>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = filtered.size(); i < l; i++)
                add((Accommodation) filtered.get(i));
            notifyDataSetInvalidated();
        }
    }
}