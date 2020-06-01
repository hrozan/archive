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

import com.j256.ormlite.stmt.DeleteBuilder;
import com.rozan.higor.orderapp.model.Order;
import com.rozan.higor.orderapp.model.Product;
import com.rozan.higor.orderapp.model.utils.DatabaseHelper;
import com.rozan.higor.orderapp.utils.GUIUtils;

import java.sql.SQLException;
import java.util.List;

public class AllProductsActivity extends AppCompatActivity {

    private ListView listViewProducts;
    private ArrayAdapter<Product> arrayAdapter;
    private static final int REQUEST_NEW_PRODUCT = 1;
    private static final int REQUEST_UPDATE_PRODUCT = 2;

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_NEW_PRODUCT || requestCode == REQUEST_UPDATE_PRODUCT)
                && resultCode == Activity.RESULT_OK) {

            populateList();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuItemNew:
                ProductsActivity.newProduct(this, REQUEST_NEW_PRODUCT);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        // get list view reference
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        // add listener
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Product product = (Product) parent.getItemAtPosition(position);

                ProductsActivity.update(AllProductsActivity.this, REQUEST_UPDATE_PRODUCT, product);
            }

        });

        populateList();

        registerForContextMenu(listViewProducts);


    }

    private void populateList() {

        List<Product> list = null;

        try {
            DatabaseHelper connection = DatabaseHelper.getInstance(this);

            list = connection.getProductDAO()
                    .queryBuilder()
                    .orderBy(Product.NAME, true)
                    .query();

        } catch (SQLException e) {
            Log.e("on populating list", e.getMessage(), e);
            return;
        }

        arrayAdapter = new ArrayAdapter<Product>(this,
                android.R.layout.simple_list_item_1,
                list);

        listViewProducts.setAdapter(arrayAdapter);
    }

    private void deleteProduct(final Product product) {
        String message = getString(R.string.deleteConfirmTxt) + "\n" + product.getName();

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        try {
                            DatabaseHelper connection = DatabaseHelper.getInstance(AllProductsActivity.this);

                            DeleteBuilder<Order, Integer> deleteBuilder = connection.getOrderDAO()
                                    .deleteBuilder();

                            // delete order linked to this product
                            deleteBuilder.where().eq(Order.PRODUCT_ID, product.getId());
                            deleteBuilder.delete();

                            // delete client
                            connection.getProductDAO().delete(product);

                            // remove from adapter
                            arrayAdapter.remove(product);


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

        Product product = (Product) listViewProducts.getItemAtPosition(info.position);

        switch (item.getItemId()) {

            case R.id.menuItemOpen:
                ProductsActivity.update(this,
                        REQUEST_UPDATE_PRODUCT,
                        product);
                return true;

            case R.id.menuItemDelete:
                deleteProduct(product);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


}
