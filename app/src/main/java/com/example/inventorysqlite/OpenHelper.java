package com.example.inventorysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Product.db";
    private static final String CREATE_PRODUCT_TABLE = "CREATE TABLE " + DataBaseInfo.TABLE_NAME + "( " +
            DataBaseInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DataBaseInfo.productCode + " text," +
            DataBaseInfo.productName + " text," +
            DataBaseInfo.productDes + " text," +
            DataBaseInfo.productQuantity + " INTEGER)";

    private static final String DELETE_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + DataBaseInfo.TABLE_NAME;

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_PRODUCT_TABLE);
        onCreate(sqLiteDatabase);
    }
}
