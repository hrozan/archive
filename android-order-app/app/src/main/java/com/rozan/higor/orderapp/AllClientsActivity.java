package com.rozan.higor.orderapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.rozan.higor.orderapp.model.Client;
import com.rozan.higor.orderapp.model.Order;
import com.rozan.higor.orderapp.model.utils.DatabaseHelper;
import com.rozan.higor.orderapp.utils.GUIUtils;

import java.sql.SQLException;
import java.util.List;

public class AllClientsActivity extends AppCompatActivity {

    private ListView listViewClients;
    private ArrayAdapter<Client> arrayAdapter;
    private static final int REQUEST_NEW_CLIENT = 1;
    private static final int REQUEST_UPDATE_CLIENT = 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_NEW_CLIENT || requestCode == REQUEST_UPDATE_CLIENT)
                && resultCode == Activity.RESULT_OK) {

            populateList();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuItemNew:
                ClientsActivity.newClient(this, REQUEST_NEW_CLIENT);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_clients);

        // get list view reference
        listViewClients = (ListView) findViewById(R.id.listViewOrders);

        // add listener
        listViewClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Client client = (Client) parent.getItemAtPosition(position);

                ClientsActivity.update(AllClientsActivity.this, REQUEST_UPDATE_CLIENT, client);
            }

        });

        populateList();

        registerForContextMenu(listViewClients);
    }

    private void populateList() {

        List<Client> list = null;

        try {
            DatabaseHelper connection = DatabaseHelper.getInstance(this);

            list = connection.getClientDAO()
                    .queryBuilder()
                    .orderBy(Client.NAME, true)
                    .query();

        } catch (SQLException e) {
            Log.e("on populating list", e.getMessage(), e);
            return;
        }

        arrayAdapter = new ArrayAdapter<Client>(this,
                android.R.layout.simple_list_item_1,
                list);

        listViewClients.setAdapter(arrayAdapter);
    }

    private void deleteClient(final Client client) {
        String message = getString(R.string.deleteConfirmTxt) + "\n" + client.getName();

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        try {
                            DatabaseHelper connection = DatabaseHelper.getInstance(AllClientsActivity.this);

                            DeleteBuilder<Order, Integer> deleteBuilder = connection.getOrderDAO()
                                    .deleteBuilder();

                            // delete order linked to this client
                            deleteBuilder.where().eq(Order.CLIENT_ID, client.getId());
                            deleteBuilder.delete();

                            // delete client
                            connection.getClientDAO().delete(client);

                            // remove from adapter
                            arrayAdapter.remove(client);


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        // do nothing 
                        break;
                }
            }
        };

        GUIUtils.confirmAction(this, message, listener);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.selected_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;

        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Client client = (Client) listViewClients.getItemAtPosition(info.position);

        switch (item.getItemId()) {

            case R.id.menuItemOpen:
                ClientsActivity.update(this,
                        REQUEST_UPDATE_CLIENT,
                        client);
                return true;

            case R.id.menuItemDelete:
                deleteClient(client);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


}
