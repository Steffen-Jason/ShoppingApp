package com.example.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is Jason's comment! I hope this works!
        //This is Martin's comment!
        //This is James' comment!
    }

    public void onCreateList(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        Product milk = new Product("milk", 1, "dairy" );
        Product eggs = new Product("eggs", 5, "dairy");
        newDb.addProduct(milk);
        newDb.addProduct(eggs);
        Intent intent = new Intent(this, create_list.class);
        startActivity(intent);
    }
     public void onShare(View view){

     }
}
