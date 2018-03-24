package michael.example.com.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import michael.example.com.inventoryapp.data.ProductInventoryContract;
import michael.example.com.inventoryapp.data.ProductInventoryContract.ProductItem;

public class ProductsCursorAdapter extends CursorAdapter {

    private Context thisContext;
    private Cursor cursor;

    public ProductsCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_product, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        thisContext = context;

        Log.e("TEST", "IN HERE!!!");

        TextView productName = (TextView) view.findViewById(R.id.name);
        TextView quantity = view.findViewById(R.id.quantity);
        TextView price = view.findViewById(R.id.price);

        cursor.moveToFirst();
        final String name = cursor.getString(cursor.getColumnIndex("product_name"));
        Log.e("QUERY !!!!!!!!!", name);
        // Log.e("THE PRICE", productNameString);

        productName.setText(name);
        quantity.setText("1234");
        price.setText("DEMO");
        // price.setText(priceString);
    }
}
