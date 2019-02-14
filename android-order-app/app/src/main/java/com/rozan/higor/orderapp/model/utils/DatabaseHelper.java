package com.rozan.higor.orderapp.model.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rozan.higor.orderapp.model.Client;
import com.rozan.higor.orderapp.model.Order;
import com.rozan.higor.orderapp.model.Product;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "orderApp.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper instance;
    private Dao<Client, Integer> clientDAO;
    private Dao<Product, Integer> productDAO;
    private Dao<Order, Integer> orderDAO;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Client.class);
            TableUtils.createTable(connectionSource, Product.class);
            TableUtils.createTable(connectionSource, Order.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "onCreate", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable(connectionSource, Client.class, true);
            TableUtils.dropTable(connectionSource, Product.class, true);
            TableUtils.dropTable(connectionSource, Order.class, true);

            onCreate(database, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "onUpgrade", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Client, Integer> getClientDAO() throws SQLException {
        if (clientDAO == null) {
            clientDAO = getDao(Client.class);
        }
        return clientDAO;
    }

    public Dao<Product, Integer> getProductDAO() throws SQLException {
        if (productDAO == null) {
            productDAO = getDao(Product.class);
        }
        return productDAO;
    }

    public Dao<Order, Integer> getOrderDAO() throws SQLException {
        if (orderDAO == null) {
            orderDAO = getDao(Order.class);
        }
        return orderDAO;
    }
}
