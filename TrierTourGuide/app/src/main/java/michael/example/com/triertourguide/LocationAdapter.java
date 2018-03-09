package michael.example.com.triertourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter<Location> {

    public LocationAdapter(@NonNull Context context, ArrayList<Location> locations) {
        super(context, 0, locations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        int imageId = R.drawable.ic_launcher_background;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.location, parent, false);
        }

        Location location = getItem(position);

        if (location.getTitle().toLowerCase().equals("kaiserthermen")) {
            imageId = R.drawable.thermen;
        } else if (location.getTitle().toLowerCase().equals("karl marx haus")) {
            imageId = R.drawable.haus; // java.lang.RuntimeException: Canvas: trying to draw too large(170515128bytes) bitmap. Had to remove the extra-large images.
        } else if (location.getTitle().toLowerCase().equals("amphitheater")) {
            imageId = R.drawable.amphi;
        }

        ImageView locationImage = listItemView.findViewById(R.id.locationImage);
        locationImage.setImageResource(imageId);

        TextView title = listItemView.findViewById(R.id.title);
        title.setText(location.getTitle());

        TextView street = listItemView.findViewById(R.id.street);
        street.setText("Street: " + location.getStreet());

        TextView phoneNumber = listItemView.findViewById(R.id.phone);
        phoneNumber.setText("Phone Number: " + location.getPhoneNumber());

        TextView email = listItemView.findViewById(R.id.email);
        email.setText("Email: " + location.getEmail());

        TextView website = listItemView.findViewById(R.id.website);
        website.setText("Web: " + location.getWebsite());

        return listItemView;
    }
}
