package michael.example.com.inventoryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.util.ArrayList;

import michael.example.com.inventoryapp.data.CrudHelper;
import michael.example.com.inventoryapp.data.ProductInventoryDatabaseHelper;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private final ProductInventoryDatabaseHelper dbHelper;
    private final CrudHelper crudHelper;

    public ProductAdapter(Activity context, ArrayList<Product> songList) {
        super(context, 0, songList);
        this.context = context;
        dbHelper = new ProductInventoryDatabaseHelper(getContext());
        crudHelper = new CrudHelper(dbHelper);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.item_product, parent, false);

        Button saleButton = customView.findViewById(R.id.button_sale);

        Product product = getItem(position);
        TextView nameView = customView.findViewById(R.id.name);
        TextView quantityView = customView.findViewById(R.id.quantity);
        TextView priceView = customView.findViewById(R.id.price);

        final int id = product.getId();
        final String name = product.getName();
        final int quantity = product.getQuantity();
        final double price = product.getPrice();

        nameView.setText("Productname: " + name);
        quantityView.setText("Quantity available: " + quantity);
        priceView.setText("Price: " + price);

        customView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("id", id + "");
                context.startActivity(intent);
            }
        });

        saleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                crudHelper.updateQuantity(id, quantity, -1);

                Intent intent = new Intent(getContext(), MainActivity.class);
                context.startActivity(intent);
            }
        });

        return customView;
    }
}
