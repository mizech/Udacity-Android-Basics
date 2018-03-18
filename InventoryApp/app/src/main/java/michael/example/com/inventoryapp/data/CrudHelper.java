package michael.example.com.inventoryapp.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import michael.example.com.inventoryapp.data.ProductInventoryContract.ProductItem;

public class CrudHelper {
    private ProductInventoryDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public CrudHelper(ProductInventoryDatabaseHelper dhHelper) {
        this.dbHelper = dhHelper;
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void insert() {
        ContentValues values = new ContentValues();

        values.put(ProductItem.PRODUCT_NAME, "TestProductName");
        values.put(ProductItem.PRICE, 9.99);
        values.put(ProductItem.QUANTITY, 7);
        values.put(ProductItem.SUPPLIER_NAME, "TestSupplierName");
        values.put(ProductItem.SUPPLIER_PHONE_NUMBER, "0123456");

        database.insert(ProductItem.TABLE_NAME, null, values);
        /*
        String insertInto = "INSERT INTO " + ProductItem.TABLE_NAME + "(" +
                ProductItem.PRODUCT_NAME + ", "  + ProductItem.PRICE + ", " +
                ProductItem.QUANTITY + ", " + ProductItem.SUPPLIER_NAME + ", " +
                ProductItem.SUPPLIER_PHONE_NUMBER +
                ") VALUES ('TestProductName', 9.99, 8, 'TestSupplierName', '012345')";

        database.execSQL(insertInto);
        */
    }

    /*
    private Cursor queryData(){

    }
    */
}
