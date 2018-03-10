package michael.example.com.triertourguide;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.widget.ListView;

/**
 * Created by michael on 06.03.18.
 */

public class PubsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sights, container, false);

        ArrayList<Location> location = new ArrayList<Location>();

        location.add((new Location( getResources().getString(R.string.title_louisiana),
                                    getResources().getString(R.string.street_louisiana),
                                    getResources().getString(R.string.phone_louisiana),
                                    getResources().getString(R.string.email_louisiana),
                                    getResources().getString(R.string.website_louisiana))));
        location.add((new Location( getResources().getString(R.string.title_mohr),
                                    getResources().getString(R.string.street_mohr),
                                    getResources().getString(R.string.phone_mohr),
                                    getResources().getString(R.string.email_mohr),
                                    getResources().getString(R.string.website_mohr))));

        LocationAdapter locationAdapter = new LocationAdapter(getContext(), location);
        final ListView sightsView = view.findViewById(R.id.sights);
        sightsView.setAdapter(locationAdapter);

        return view;
    }
}
