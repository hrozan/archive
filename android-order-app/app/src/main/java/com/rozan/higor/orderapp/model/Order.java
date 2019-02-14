package com.rozan.higor.orderapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Higor on 21/11/2017.
 */
@DatabaseTable
public class Order {

    public static final String ID      = "id";
    public static final String STATE    = "state";
    public static final String CLIENT_ID   = "client_id";
    public static final String PRODUCT_ID = "product_id";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;

    @DatabaseField(canBeNull = false, columnName = STATE)
    private int state;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh=true, columnName = CLIENT_ID)
    private Client client;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh=true, columnName = PRODUCT_ID)
    private Product product;

    public Order() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return getId() + " " + client.getName() + " R$ " + product.getValue();
    }
}
