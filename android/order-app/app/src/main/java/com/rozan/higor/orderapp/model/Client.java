package com.rozan.higor.orderapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Higor on 20/11/2017.
 */

@DatabaseTable
public class Client {

    public static final String ID      = "id";
    public static final String NAME    = "name";
    public static final String EMAIL   = "email";
    public static final String CEL = "cel";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;

    @DatabaseField(canBeNull = false, columnName = NAME)
    private String name;

    @DatabaseField(canBeNull = false, columnName = EMAIL)
    private String email;

    @DatabaseField(canBeNull = false, columnName = CEL)
    private String cel;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    @Override
    public String toString() {
        return getName() + " " + getEmail();
    }
}
