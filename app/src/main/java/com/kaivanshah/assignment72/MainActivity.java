package com.kaivanshah.assignment72;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> ProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null)

        {

            DBHelper db = new DBHelper(this);
            // Inserting Products
            Log.d("Insert: ", "Inserting ..");
            db.addProduct("iPhone 6", 500);
            db.addProduct("iPhone 6S", 600);
            db.addProduct("iPhone 7", 700);
            db.addProduct("Mac Book", 1000);
            db.addProduct("Apple Watch1", 200);
            db.addProduct("Apple Watch2", 300);
            db.addProduct("Apple TV", 200);
            db.addProduct("iPad", 300);
            db.addProduct("Mini iPad", 500);

            ProductList = db.getAllProducts();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, ProductList);
            AutoCompleteTextView textView = (AutoCompleteTextView)
                    findViewById(R.id.AutoTV_Product);
            textView.setAdapter(adapter);
        }





    }
}
