package com.rozan.higor.orderapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.rozan.higor.orderapp.model.Client;
import com.rozan.higor.orderapp.model.utils.DatabaseHelper;
import com.rozan.higor.orderapp.utils.GUIUtils;

import java.sql.SQLException;

public class ClientsActivity extends AppCompatActivity {

    public static final String MODE = "MODE";
    public static final String ID = "ID";
    public static final int NEW = 1;
    public static final int UPDATE = 2;

    private static final int REQUEST_NEW_CLIENT = 1;
    private static final int REQUEST_UPDATE_CLIENT = 2;


    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;

    private int mode;
    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        // add actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // get editText references
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        
        //get mode
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mode = bundle.getInt(MODE);

        if (mode == UPDATE){

            int id = bundle.getInt(ID);

            try {
                DatabaseHelper conexao = DatabaseHelper.getInstance(this);

                client = conexao.getClientDAO().queryForId(id);

                editTextName.setText(client.getName());
                editTextEmail.setText(client.getEmail());
                editTextPhone.setText(client.getCel());

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{

            client = new Client();

        }


    }

    public static void newClient(Activity activity, int requestCode) {

        Intent intent = new Intent(activity, ClientsActivity.class);

        intent.putExtra(MODE, NEW);

        activity.startActivityForResult(intent, NEW);
    }

    public static void update(Activity activity, int requestCode, Client client) {

        Intent intent = new Intent(activity, ClientsActivity.class);

        intent.putExtra(MODE, UPDATE);
        intent.putExtra(ID, client.getId());

        activity.startActivityForResult(intent, UPDATE);
    }

    public void saveClient(View view){

        String name  = GUIUtils.validateTextField(this,
                editTextName,
                R.string.emptyFieldTxt);
        if (name == null){
            return;
        }

        String email  = GUIUtils.validateTextField(this,
                editTextEmail,
                R.string.emptyFieldTxt);
        if (email == null){
            return;
        }

        String cel = GUIUtils.validateTextField(this,
                editTextPhone,
                R.string.emptyFieldTxt);
        if (cel == null){
            return;
        }

        
        client.setName(name);
        client.setEmail(email);
        client.setCel(cel);

        try {

            DatabaseHelper connection = DatabaseHelper.getInstance(this);

            if (mode == NEW) {
                connection.getClientDAO().create(client);

            } else {

                connection.getClientDAO().update(client);
            }

            setResult(Activity.RESULT_OK);
            finish();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finish();
    }


}


