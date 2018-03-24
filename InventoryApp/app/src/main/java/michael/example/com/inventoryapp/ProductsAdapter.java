package michael.example.com.inventoryapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by michael on 24.03.18.
 */

public class ProductsAdapter extends ArrayAdapter<Product> {
    private Context context;
    private ArrayList<Product> productsList;

    public ProductsAdapter(Activity context, ArrayList<Product> productsList) {
        super(context, 0, productsList);
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;

        if (customView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            customView = inflater.inflate(R.layout.product, parent, false);
        }

        Product product = getItem(position);
        TextView name = customView.findViewById(R.id.name);

        final String nameString = product.getName();
        name.setText(nameString);

        return customView;
    }
}
