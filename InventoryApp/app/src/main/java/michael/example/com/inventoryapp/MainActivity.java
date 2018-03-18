package michael.example.com.inventoryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import michael.example.com.inventoryapp.data.CrudHelper;
import michael.example.com.inventoryapp.data.ProductInventoryContract;
import michael.example.com.inventoryapp.data.ProductInventoryDatabaseHelper;
import michael.example.com.inventoryapp.data.ProductInventoryContract.ProductItem;

public class MainActivity extends AppCompatActivity {
    private ProductInventoryDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    CrudHelper crudHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}
