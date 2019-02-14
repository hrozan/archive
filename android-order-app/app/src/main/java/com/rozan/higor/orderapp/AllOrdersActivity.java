package com.rozan.higor.orderapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rozan.higor.orderapp.model.Order;
import com.rozan.higor.orderapp.model.utils.DatabaseHelper;
import com.rozan.higor.orderapp.utils.GUIUtils;

import java.sql.SQLException;
import java.util.List;

public class AllOrdersActivity extends AppCompatActivity {

    private ListView listViewOrder;
    private ArrayAdapter<Order> arrayAdapter;
    private static final int REQUEST_NEW_ORDER = 1;
    private static final int REQUEST_UPDATE_PRODUCT = 2;

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_NEW_ORDER || requestCode == REQUEST_UPDATE_PRODUCT)
                && resultCode == Activity.RESULT_OK) {

            populateList();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuItemNew:
                OrdersActivity.newOrder(this, REQUEST_NEW_ORDER);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        // get list view reference
        listViewOrder = (ListView) findViewById(R.id.listViewOrders);

        // add listener
        listViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Order order = (Order) parent.getItemAtPosition(position);

                OrdersActivity.update(AllOrdersActivity.this, REQUEST_UPDATE_PRODUCT, order);
            }

        });

        populateList();

        registerForContextMenu(listViewOrder);


    }

    private void populateList() {

        List<Order> list = null;

        try {
            DatabaseHelper connection = DatabaseHelper.getInstance(this);

            list = connection.getOrderDAO()
                    .queryBuilder()
                    .orderBy(Order.ID, true)
                    .query();

        } catch (SQLException e) {
            Log.e("on populating list", e.getMessage(), e);
            return;
        }

        arrayAdapter = new ArrayAdapter<Order>(this,
                android.R.layout.simple_list_item_1,
                list);

        listViewOrder.setAdapter(arrayAdapter);
    }

    private void deleteOrder(final Order order) {
        String message = getString(R.string.deleteConfirmTxt) + "\n Order Id:" + order.getId();

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        try {
                            DatabaseHelper connection = DatabaseHelper.getInstance(AllOrdersActivity.this);

                            // delete order
                            connection.getOrderDAO().delete(order);

                            // remove from adapter
                            arrayAdapter.remove(order);


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

        Order order = (Order) listViewOrder.getItemAtPosition(info.position);

        switch (item.getItemId()) {

            case R.id.menuItemOpen:
                OrdersActivity.update(this,
                        REQUEST_UPDATE_PRODUCT,
                        order);
                return true;

            case R.id.menuItemDelete:
                deleteOrder(order);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

}
