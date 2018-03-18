package michael.example.com.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import michael.example.com.inventoryapp.data.ProductInventoryContract.ProductItem;

public class ProductInventoryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    public ProductInventoryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database operations", "Database Created.");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + ProductItem.TABLE_NAME + " (" +
                ProductItem._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ProductItem.PRODUCT_NAME +
                " TEXT NOT NULL, " + ProductItem.PRICE + " REAL NOT NULL, " + ProductItem.QUANTITY +
                " INTEGER NOT NULL, " + ProductItem.SUPPLIER_NAME + " TEXT, " +
                ProductItem.SUPPLIER_PHONE_NUMBER + " TEXT)";

        Log.i("CREATE TABLE", createTable);
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}