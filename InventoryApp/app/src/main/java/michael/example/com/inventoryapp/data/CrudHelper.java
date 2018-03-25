package michael.example.com.inventoryapp.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    }

    public void deleteProduct(int id) {
        database.delete(ProductItem.TABLE_NAME, "_id = " + id, null);
    }

    public void updateQuantity(int id, int currentQuantity, int direction) {
        int newQuantity;

        if (currentQuantity <= 0) {
            return;
        }

        if (direction >= 0) {
            newQuantity = currentQuantity + 1;
        } else {
            newQuantity = currentQuantity - 1;
        }

        String strSQL = "UPDATE products SET quantity = " + newQuantity + "  WHERE _id = " + id;

        database.execSQL(strSQL);
    }

    public Cursor queryData(){
        String[] projection = {
                ProductItem._ID,
                ProductItem.PRODUCT_NAME,
                ProductItem.PRICE,
                ProductItem.QUANTITY,
                ProductItem.SUPPLIER_NAME,
                ProductItem.SUPPLIER_PHONE_NUMBER
        };

        Cursor cursor =
                database.query(ProductItem.TABLE_NAME, projection,
                               null, null, null,
                        null, null);

        return cursor;
    }

}
