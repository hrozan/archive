package com.rozan.higor.orderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnClientsHandler(View view){
        Intent intent = new Intent(this, AllClientsActivity.class);
        startActivity(intent);
    }

    public void btnProductsHandler(View view){
        Intent intent = new Intent(this, AllProductsActivity.class);
        startActivity(intent);
    }

    public void btnOrdersHandler(View view){
        Intent intent = new Intent(this, AllOrdersActivity.class);
        startActivity(intent);
    }
}
