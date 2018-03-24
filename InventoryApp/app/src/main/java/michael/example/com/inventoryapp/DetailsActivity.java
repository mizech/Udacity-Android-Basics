package michael.example.com.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import michael.example.com.inventoryapp.data.ProductInventoryContract;
import michael.example.com.inventoryapp.data.ProductInventoryDatabaseHelper;

public class DetailsActivity extends AppCompatActivity {
    private ProductInventoryDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dbHelper = new ProductInventoryDatabaseHelper(this);
        database = dbHelper.getReadableDatabase();

        Cursor testCursor = database.rawQuery("SELECT * FROM products WHERE _id = 1", null);
        testCursor.moveToFirst();

        int productNameIndex =
                testCursor.getColumnIndex(ProductInventoryContract.ProductItem.PRODUCT_NAME);
        String name = testCursor.getString(productNameIndex);

        Intent i = getIntent();
        String id = i.getStringExtra("id");


        Log.e("IN DETAILS", name);
        Log.e("ID", id + "");
    }

}
