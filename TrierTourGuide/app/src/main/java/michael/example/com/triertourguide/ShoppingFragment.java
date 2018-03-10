package michael.example.com.triertourguide;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by michael on 06.03.18.
 */

public class ShoppingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sights, container, false);

        ArrayList<Location> location = new ArrayList<Location>();

        location.add((new Location( getResources().getString(R.string.title_karstadt),
                                    getResources().getString(R.string.street_karstadt),
                                    getResources().getString(R.string.phone_karstadt),
                                    getResources().getString(R.string.email_karstadt),
                                    getResources().getString(R.string.website_karstadt)
        )));
        location.add((new Location( getResources().getString(R.string.title_unverpackt),
                                    getResources().getString(R.string.street_unverpackt),
                                    getResources().getString(R.string.phone_unverpackt),
                                    getResources().getString(R.string.email_unverpackt),
                                    getResources().getString(R.string.website_unverpackt))));

        LocationAdapter locationAdapter = new LocationAdapter(getContext(), location);
        final ListView sightsView = view.findViewById(R.id.sights);
        sightsView.setAdapter(locationAdapter);

        return view;
    }
}
