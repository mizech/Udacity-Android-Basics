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

public class SightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sights, container, false);

        ArrayList<Location> location = new ArrayList<Location>();

        location.add((new Location( getResources().getString(R.string.title_thermen),
                                    getResources().getString(R.string.street_thermen),
                                    getResources().getString(R.string.phone_thermen),
                                    getResources().getString(R.string.email_thermen),
                                    getResources().getString(R.string.website_thermen))));
        location.add((new Location( getResources().getString(R.string.title_marx_karl),
                                    getResources().getString(R.string.street_marx_karl),
                                    getResources().getString(R.string.phone_marx_karl),
                                    getResources().getString(R.string.email_marx_karl),
                                    getResources().getString(R.string.website_marx_karl))));
        location.add((new Location( getResources().getString(R.string.title_amphi),
                                    getResources().getString(R.string.street_amphi),
                                    getResources().getString(R.string.phone_amphi),
                                    getResources().getString(R.string.email_amphi),
                                    getResources().getString(R.string.website_amphi))));

        LocationAdapter locationAdapter = new LocationAdapter(getContext(), location);
        final ListView sightsView = view.findViewById(R.id.sights);
        sightsView.setAdapter(locationAdapter);

        return view;
    }
}
