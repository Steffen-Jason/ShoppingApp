package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/*
Created by Jason 20171103
 */

public class choose_inventory extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_inventory);

        Intent intent = getIntent();

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME2", MODE_PRIVATE);
        String message = prefs.getString("inventory", "");

        Context context = getApplicationContext();
        CharSequence text = "The list that was last used was " + message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        if(message != "")
            toast.show();

    }

    public void onChooseInventory(View view){
        Intent intent = new Intent(this, UseInventory.class);
        startActivity(intent);
    }
}