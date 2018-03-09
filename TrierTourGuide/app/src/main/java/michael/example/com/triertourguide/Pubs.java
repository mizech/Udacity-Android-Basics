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

public class Pubs extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sights, container, false);

        ArrayList<Location> location = new ArrayList<Location>();

        location.add((new Location("Louisiana", "Johann-Philipp-Straße 5-6", "0651 991 98 99", "louisiana@gmx.de", "http://www.louisiana.de/outlet/trier")));
        location.add((new Location("Kaffee Mohr", "Fleischstraße 55", "0651 74220", "-", "-")));

        LocationAdapter locationAdapter = new LocationAdapter(getContext(), location);
        final ListView sightsView = view.findViewById(R.id.sights);
        sightsView.setAdapter(locationAdapter);

        return view;
    }
}
