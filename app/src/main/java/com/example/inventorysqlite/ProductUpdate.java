package com.example.inventorysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductUpdate extends AppCompatActivity {

    EditText eCode;
    EditText eName;
    EditText eDes;
    EditText eQuanity;

    String stCode;
    String stName;
    String stDes;
    String stQuanity;

    List<MOJO> productDetails;
    OpenHelper openHelper;
    SQLiteDatabase sqLiteDatabase;
    int rowId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);
        openHelper = new OpenHelper(this);
        sqLiteDatabase=openHelper.getWritableDatabase();
        eCode=findViewById(R.id.editCodeUpdate);
        eName=findViewById(R.id.editNameUpdate);

        eDes=findViewById(R.id.editDesUpdate);
        eQuanity=findViewById(R.id.editQuantityUpdate);
        rowId = getIntent().getIntExtra("prodId", -1);
        Cursor cursor = sqLiteDatabase.query(DataBaseInfo.TABLE_NAME, null, DataBaseInfo._ID + " = " + rowId, null, null,null, null);
        productDetails= new ArrayList<MOJO>();
        productDetails.clear();

        if(cursor != null && cursor.getCount() != 0){
            while (cursor.moveToNext()) {
                eCode.setText(cursor.getString(cursor.getColumnIndex(DataBaseInfo.productCode)));
                eName.setText(cursor.getString(cursor.getColumnIndex(DataBaseInfo.productName)));
                eDes.setText(cursor.getString(cursor.getColumnIndex(DataBaseInfo.productDes)));
                eQuanity.setText(cursor.getString(cursor.getColumnIndex(DataBaseInfo.productQuantity)));
            }
        }else{
            Toast.makeText(this, "No Data Found!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void clickUpdate(View view){
        stCode=eCode.getText().toString();
        stName=eName.getText().toString();
        stDes=eDes.getText().toString();
        stQuanity=eQuanity.getText().toString();
        if(TextUtils.isEmpty(stCode) || TextUtils.isEmpty(stName) || TextUtils.isEmpty(stDes) || TextUtils.isEmpty(stQuanity)){
            Toast.makeText(this, "Check the Empty Fields", Toast.LENGTH_SHORT).show();
        }else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseInfo.productCode, stCode);
            contentValues.put(DataBaseInfo.productDes, stDes);
            contentValues.put(DataBaseInfo.productName, stName);
            contentValues.put(DataBaseInfo.productQuantity, stQuanity);
            int updateId = sqLiteDatabase.update(DataBaseInfo.TABLE_NAME, contentValues,

                    DataBaseInfo._ID + " = " + rowId, null);
            if (updateId != -1) {
                Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Something Wrong!!", Toast.LENGTH_SHORT).show();            }
        }    }
    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}