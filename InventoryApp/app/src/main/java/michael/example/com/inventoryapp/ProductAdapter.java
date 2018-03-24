package michael.example.com.inventoryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.util.ArrayList;

/**
 * Created by michael on 24.03.18.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;

    public ProductAdapter(Activity context, ArrayList<Product> songList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, songList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.item_product, parent, false);

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


        return customView;
    }
}
