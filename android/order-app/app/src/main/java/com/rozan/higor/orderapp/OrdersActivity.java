package com.rozan.higor.orderapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.rozan.higor.orderapp.model.Client;
import com.rozan.higor.orderapp.model.Order;
import com.rozan.higor.orderapp.model.Product;
import com.rozan.higor.orderapp.model.utils.DatabaseHelper;
import com.rozan.higor.orderapp.utils.GUIUtils;

import java.sql.SQLException;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    public static final String MODE = "MODE";
    public static final String ID = "ID";
    public static final int NEW = 1;
    public static final int UPDATE = 2;

    private Spinner spinnerClients;
    private Spinner spinnerProducts;
    private RadioGroup radioGroupState;
    private List<Client> clientList;
    private List<Product> productList;

    private int mode;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        // add actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        spinnerClients = (Spinner) findViewById(R.id.spinnerClient);
        spinnerProducts = (Spinner) findViewById(R.id.spinnerProduct);
        radioGroupState = (RadioGroup) findViewById(R.id.radioGroupState);

        //get mode
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mode = bundle.getInt(MODE);

        if (mode == UPDATE){

            int id = bundle.getInt(ID);

            try {
                DatabaseHelper conexao = DatabaseHelper.getInstance(this);

                order = conexao.getOrderDAO().queryForId(id);

                spinnerClients.setSelection(order.getClient().getId());
                spinnerProducts.setSelection(order.getProduct().getId());
                radioGroupState.check(order.getState());


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{

            order = new Order();

        }

        populateClientSpinner();
        populateProductsSpinner();
    }

    private void populateClientSpinner(){
        clientList = null;

        try {
            DatabaseHelper conexao = DatabaseHelper.getInstance(this);
            clientList = conexao.getClientDAO()
                    .queryBuilder()
                    .orderBy(Client.ID, true)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Client> spinnerAdapter = new ArrayAdapter<Client>(this,
                R.layout.support_simple_spinner_dropdown_item,
                clientList);
        spinnerClients.setAdapter(spinnerAdapter);
    }

    private void populateProductsSpinner(){
        productList = null;

        try {
            DatabaseHelper conexao = DatabaseHelper.getInstance(this);
            productList = conexao.getProductDAO()
                    .queryBuilder()
                    .orderBy(Product.ID, true)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Product> spinnerAdapter = new ArrayAdapter<Product>(this,
                R.layout.support_simple_spinner_dropdown_item,
                productList);
        spinnerProducts.setAdapter(spinnerAdapter);
    }

    public static void newOrder(Activity activity, int requestCode) {

        Intent intent = new Intent(activity, OrdersActivity.class);

        intent.putExtra(MODE, NEW);

        activity.startActivityForResult(intent, NEW);
    }

    public static void update(Activity activity, int requestCode, Order order) {

        Intent intent = new Intent(activity, OrdersActivity.class);

        intent.putExtra(MODE, UPDATE);
        intent.putExtra(ID, order.getId());

        activity.startActivityForResult(intent, UPDATE);
    }

    public void saveOrder(View view){

        Client client = (Client) spinnerClients.getSelectedItem();
        if (client != null){
            order.setClient(client);
        }

        Product product = (Product) spinnerProducts.getSelectedItem();
        if (product != null){
            order.setProduct(product);
        }

        int state = radioGroupState.getCheckedRadioButtonId();

        order.setClient(client);
        order.setProduct(product);
        order.setState(state);

        try {

            DatabaseHelper connection = DatabaseHelper.getInstance(this);

            if (mode == NEW) {
                connection.getOrderDAO().create(order);

            } else {

                connection.getOrderDAO().update(order);
            }

            setResult(Activity.RESULT_OK);
            finish();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finish();
    }


}
