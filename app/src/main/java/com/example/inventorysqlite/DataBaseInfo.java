package com.example.inventorysqlite;

import android.provider.BaseColumns;

public class DataBaseInfo implements BaseColumns {

    private DataBaseInfo () {
    }

    public static final String TABLE_NAME = "ProductTable";
    public static final String productCode = "ProductCode";
    public static final String productName = "ProductName";
    public static final String productDes = "ProductDes";
    public static final String productQuantity = "ProductQuantity";
}
