package michael.example.com.inventoryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import michael.example.com.inventoryapp.data.CrudHelper;
import michael.example.com.inventoryapp.data.ProductInventoryContract;
import michael.example.com.inventoryapp.data.ProductInventoryDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private ProductInventoryDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    CrudHelper crudHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Product> mockList = new ArrayList<Product>();
        mockList.add(new Product("Alpha", 2, 4.0));
        mockList.add(new Product("Beta", 4, 8.0));
        mockList.add(new Product("Gamme", 8, 16.0));

        ProductsAdapter productsAdapter = new ProductsAdapter(this, mockList);
        final ListView listView = findViewById(R.id.productsListView);
        listView.setAdapter(productsAdapter);

        /*
        TextView productsListEmpty = (TextView) findViewById(R.id.messageProductsListEmpty);
        listView.setEmptyView(productsListEmpty);
        */

        /*
        crudHelper = new CrudHelper(new ProductInventoryDatabaseHelper(this));
        dbHelper = new ProductInventoryDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        crudHelper.insert();

        Cursor cursor = database.rawQuery("SELECT * FROM " + ProductInventoryContract.ProductItem.TABLE_NAME, null);

        try {
            Log.i("Count of Rows", cursor.getCount() + "");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            cursor.close();
        }

        Cursor testCursor = crudHelper.queryData();

        try {
            Log.i("RESULT", testCursor.getCount() + "");
            Log.i("First column is ?", testCursor.getColumnName(0));
            Log.i("Second column is ?", testCursor.getColumnName(1));

            Boolean result = testCursor.moveToFirst();

            if (result == true) {
                int productNameIndex = testCursor.getColumnIndex(ProductInventoryContract.ProductItem.PRODUCT_NAME);
                int priceIndex = testCursor.getColumnIndex(ProductInventoryContract.ProductItem.PRICE);
                int quantityIndex = testCursor.getColumnIndex(ProductInventoryContract.ProductItem.QUANTITY);
                int supplieNameIndex = testCursor.getColumnIndex(ProductInventoryContract.ProductItem.SUPPLIER_NAME);
                int suppliePhoneNumberIndex = testCursor.getColumnIndex(ProductInventoryContract.ProductItem.SUPPLIER_PHONE_NUMBER);

                Log.i("ProductName value-index", productNameIndex + "");
                Log.i("Second value", testCursor.getString(1));

                Log.e("ProductName Value", testCursor.getString(productNameIndex));
                Log.e("Price-Value", testCursor.getInt(priceIndex) + "");
                Log.e("Quantity Value", testCursor.getInt(quantityIndex) + "");
                Log.e("SupplierName Value", testCursor.getString(supplieNameIndex) + "");
                Log.e("SupplierPhoneNum Value", testCursor.getString(suppliePhoneNumberIndex) + "");
            }
        } finally {
            testCursor.close();
        }
        */
    }
}
