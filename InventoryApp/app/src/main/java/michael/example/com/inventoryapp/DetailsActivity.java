package michael.example.com.inventoryapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import michael.example.com.inventoryapp.data.CrudHelper;
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
        final CrudHelper crudHelper = new CrudHelper(dbHelper);

        Intent i = getIntent();
        final String id = i.getStringExtra("id");

        Cursor cursor = database.rawQuery("SELECT * FROM products WHERE _id = " + id, null);
        cursor.moveToFirst();

        int productNameIndex =
                cursor.getColumnIndex(ProductInventoryContract.ProductItem.PRODUCT_NAME);
        String name = cursor.getString(productNameIndex);

        int productQuantityIndex =
                cursor.getColumnIndex(ProductInventoryContract.ProductItem.QUANTITY);
        final String quantity = cursor.getString(productQuantityIndex);

        int priceQuantityIndex =
                cursor.getColumnIndex(ProductInventoryContract.ProductItem.PRICE);
        String price = cursor.getString(priceQuantityIndex);

        int supplierNameIndex =
                cursor.getColumnIndex(ProductInventoryContract.ProductItem.SUPPLIER_NAME);
        String supplierName = cursor.getString(supplierNameIndex);

        int supplierPhoneNumberIndex =
                cursor.getColumnIndex(ProductInventoryContract.ProductItem.SUPPLIER_PHONE_NUMBER);
        final String supplierPhoneNumber = cursor.getString(supplierPhoneNumberIndex);

        TextView nameTextView = findViewById(R.id.product_name);
        TextView quantityTextView = findViewById(R.id.product_quantity);
        TextView priceTextView = findViewById(R.id.product_price);
        TextView supplierNameView = findViewById(R.id.product_supplier_name);
        TextView supplierPhoneNumberView = findViewById(R.id.product_supplier_phone_number);

        Button deleteProduct = findViewById(R.id.delete_product);
        Button increment = findViewById(R.id.increment_product_quantity);
        Button decrement = findViewById(R.id.decrement_product_quantity);
        Button contactSupplier = findViewById(R.id.contact_supplier);

        nameTextView.setText(name);
        quantityTextView.setText(quantity);
        priceTextView.setText(price);
        supplierNameView.setText(supplierName);
        supplierPhoneNumberView.setText(supplierPhoneNumber);

        // increment_product_quantity

        deleteProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                crudHelper.deleteProduct(Integer.parseInt(id));

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                crudHelper.updateQuantity(Integer.parseInt(id), Integer.parseInt(quantity), 1);

                finish();
                startActivity(getIntent());
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                crudHelper.updateQuantity(Integer.parseInt(id), Integer.parseInt(quantity), -1);

                finish();
                startActivity(getIntent());
            }
        });


        contactSupplier.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", supplierPhoneNumber, null));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Log.e("IN", "THERE");
                    ActivityCompat.requestPermissions(DetailsActivity.this, new String[] {Manifest.permission.CALL_PHONE}, 10);
                    return;
                } else {
                    try {
                        view.getContext().startActivity(intent);
                    } catch (android.content.ActivityNotFoundException exception) {
                        Toast.makeText(DetailsActivity.this, "Activity NOT Found!", Toast.LENGTH_SHORT).show();
                    }
                }

                startActivity(intent);
            }
        });
    }
}
