package com.example.shoppingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jason on 20171104.
 */

public class UseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_list);

        Intent intent = getIntent();
        String message = intent.getStringExtra(create_list.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(message);

        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        ArrayList<String> allItems = new ArrayList<String>();
        allItems = newDb.feedNewList();

        ListView lv = (ListView) findViewById(R.id.listView2);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, allItems));

        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
        editor.putString("name", message);
        editor.apply();
    }

    public void onEditProduct(View view){
        Intent intent = new Intent(this, EditProduct.class);
        startActivity(intent);
    }
}