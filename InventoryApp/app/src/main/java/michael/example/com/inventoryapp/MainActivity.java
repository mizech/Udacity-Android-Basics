package michael.example.com.inventoryapp;

import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
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
        database = dbHelper.getReadableDatabase();

        Cursor testCursor = database.rawQuery("SELECT * FROM products", null);
        testCursor.moveToFirst();

        ArrayList<Product> products = new ArrayList<Product>();

        int i = 0;
        while (i < testCursor.getCount()) {
            int productNameId =
                    testCursor.getColumnIndex(ProductInventoryContract.ProductItem._ID);
            int productNameIndex =
                    testCursor.getColumnIndex(ProductInventoryContract.ProductItem.PRODUCT_NAME);
            int priceIndex =
                    testCursor.getColumnIndex(ProductInventoryContract.ProductItem.PRICE);
            int quantityIndex =
                    testCursor.getColumnIndex(ProductInventoryContract.ProductItem.QUANTITY);

            int id = testCursor.getInt(productNameId);
            String name = testCursor.getString(productNameIndex);
            String price = testCursor.getString(priceIndex);
            String quantity = testCursor.getString(quantityIndex);

            products.add(new Product(id, name, Integer.parseInt(quantity),  Double.parseDouble(price), "", ""));
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
