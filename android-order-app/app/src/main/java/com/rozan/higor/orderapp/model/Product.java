package com.rozan.higor.orderapp.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable
public class Product {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String DESCRIPTION = "description";


    @DatabaseField(generatedId = true, columnName = ID)
    private int id;

    @DatabaseField(canBeNull = false, columnName = NAME)
    private String name;

    @DatabaseField(canBeNull = false, columnName = VALUE)
    private double value;

    @DatabaseField(canBeNull = false, dataType = DataType.LONG_STRING, columnName = DESCRIPTION)
    private String description;

    public Product() {
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

    public String getValue() {
        return String.valueOf(value);
    }

    public void setValue(String value) {
        this.value = Double.parseDouble(value);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getName() + "\tR$ "+ getValue();
    }
}
