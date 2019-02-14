package com.rozan.higor.orderapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rozan.higor.orderapp.model.Product;
import com.rozan.higor.orderapp.model.utils.DatabaseHelper;
import com.rozan.higor.orderapp.utils.GUIUtils;

import java.sql.SQLException;

public class ProductsActivity extends AppCompatActivity {

    public static final String MODE = "MODE";
    public static final String ID = "ID";
    public static final int NEW = 1;
    public static final int UPDATE = 2;

    private EditText editTextName;
    private EditText editTextValue;
    private EditText editTextDescription;

    private int mode;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // add actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // get editText references
        editTextName =(EditText) findViewById(R.id.editTextPName);
        editTextValue =(EditText) findViewById(R.id.editTextValue);
        editTextDescription =(EditText) findViewById(R.id.editTextDescription);

        //get mode
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mode = bundle.getInt(MODE);

        if (mode == UPDATE){

            int id = bundle.getInt(ID);

            try {
                DatabaseHelper conexao = DatabaseHelper.getInstance(this);

                product = conexao.getProductDAO().queryForId(id);

                editTextName.setText(product.getName());
                editTextValue.setText(product.getValue());
                editTextDescription.setText(product.getDescription());

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{

            product = new Product();

        }


    }

    public static void newProduct(Activity activity, int requestCode) {

        Intent intent = new Intent(activity, ProductsActivity.class);

        intent.putExtra(MODE, NEW);

        activity.startActivityForResult(intent, NEW);
    }

    public static void update(Activity activity, int requestCode, Product product) {

        Intent intent = new Intent(activity, ProductsActivity.class);

        intent.putExtra(MODE, UPDATE);
        intent.putExtra(ID, product.getId());

        activity.startActivityForResult(intent, UPDATE);
    }

    public void saveProduct(View view){

        String name  = GUIUtils.validateTextField(this,
                editTextName,
                R.string.emptyFieldTxt);
        if (name == null){
            return;
        }

        String value  = GUIUtils.validateTextField(this,
                editTextValue,
                R.string.emptyFieldTxt);
        if (value == null){
            return;
        }

        String description = GUIUtils.validateTextField(this,
                editTextDescription,
                R.string.emptyFieldTxt);
        if (description == null){
            return;
        }


        product.setName(name);
        product.setValue(value);
        product.setDescription(description);

        try {

            DatabaseHelper connection = DatabaseHelper.getInstance(this);

            if (mode == NEW) {
                connection.getProductDAO().create(product);

            } else {

                connection.getProductDAO().update(product);
            }

            setResult(Activity.RESULT_OK);
            finish();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finish();
    }
}
