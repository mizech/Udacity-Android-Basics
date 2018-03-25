package michael.example.com.inventoryapp;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

import michael.example.com.inventoryapp.data.CrudHelper;
import michael.example.com.inventoryapp.data.ProductInventoryContract;
import michael.example.com.inventoryapp.data.ProductInventoryDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private ProductInventoryDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ProductInventoryDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        final CrudHelper crudHelper = new CrudHelper(dbHelper);

        crudHelper.insert();

        /*
        crudHelper.insert();
        crudHelper.insert();
        crudHelper.insert();
        */
        Cursor cursor = database.rawQuery("SELECT * FROM products", null);

        int countRecords = cursor.getCount();

        if (countRecords == 0) {
            Intent intent = new Intent(this, NoRecords.class);
            startActivity(intent);
        }

        cursor.moveToFirst();

        ArrayList<Product> products = new ArrayList<Product>();

        int i = 0;
        while (i < cursor.getCount()) {
            int productNameId =
                    cursor.getColumnIndex(ProductInventoryContract.ProductItem._ID);
            int productNameIndex =
                    cursor.getColumnIndex(ProductInventoryContract.ProductItem.PRODUCT_NAME);
            int priceIndex =
                    cursor.getColumnIndex(ProductInventoryContract.ProductItem.PRICE);
            int quantityIndex =
                    cursor.getColumnIndex(ProductInventoryContract.ProductItem.QUANTITY);

            int id = cursor.getInt(productNameId);
            String name = cursor.getString(productNameIndex);
            String price = cursor.getString(priceIndex);
            String quantity = cursor.getString(quantityIndex);

            products.add(new Product(id, name, Integer.parseInt(quantity),  Double.parseDouble(price), "", ""));
            cursor.moveToNext();
            i++;
        }

        final ListView productListView = (ListView) findViewById(R.id.products_list);
        ProductAdapter productAdapter = new ProductAdapter(this, products);
        productListView.setAdapter(productAdapter);

        Button addProduct = findViewById(R.id.add_product);
        addProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("BUTTON", "Has been clicked.");
            }
        });
    }
}
