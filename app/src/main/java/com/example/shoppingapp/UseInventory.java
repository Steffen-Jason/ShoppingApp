package com.example.shoppingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Use Inventory class will allow the user to see the items on each list requested from Choose list
 * or create list and be able to add or remove items from them.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class UseInventory extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;

    AppDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_inventory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Use Inventory");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(create_list.EXTRA_MESSAGE);
        FORWARD = message;

        TextView textView = (TextView) findViewById(R.id.textView12);
        textView.setText(message);

        //displayAll();

        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME2", MODE_PRIVATE).edit();
        editor.putString("inventory", message);
        editor.apply();

        // Setup the list view
        final ListView productListViewReduced = (ListView) findViewById(R.id.listView3);
        final ProductListAdapterReduced productListAdapterReduced = new ProductListAdapterReduced(this, R.layout.adapter_view_layout_reduced);
        productListViewReduced.setAdapter(productListAdapterReduced);

        // Populate the list, through the adapter
        for(final ProductListReduced entry : getProducts()) {
            productListAdapterReduced.add(entry);
        }
    }

    /*public void onResume(){
        super.onResume();
        displayAll();
    }

    public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.listView3);
        myDB = new AppDatabaseHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.feedNewList();
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);
        }
    }
    /**
     * onAddProduct allows user to add products or items to each inventory list in use
     * @param view
     */
    public void onAddProduct(View view){
        Intent intent = new Intent(this, PickExisting.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }

    private List<ProductListReduced> getProducts() {

        myDB = new AppDatabaseHelper(this);
        float tempPrice = 0;
        List<ProductListReduced> theList = new ArrayList<ProductListReduced>();
        Cursor data = myDB.feedNewList();
        ProductListReduced product;
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                tempPrice = Float.valueOf(data.getString(2));
                product = new ProductListReduced(data.getString(1),  "$" + String.format("%.2f", tempPrice), data.getString(3));
                theList.add(product);
            }
        }
        return theList;
    }
}
