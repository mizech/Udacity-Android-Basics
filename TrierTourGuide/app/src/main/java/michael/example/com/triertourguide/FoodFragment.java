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

public class FoodFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sights, container, false);

        ArrayList<Location> location = new ArrayList<Location>();

        location.add((new Location("Historischer Keller", "Simeonstrasse 46", "0651 469-496", "info@historischer-keller.de", "http://www.historischer-keller.de/")));
        location.add((new Location("L'Osteria", "Fleischstraße 56", "0651 14546888", "-", "losteria.de")));

        LocationAdapter locationAdapter = new LocationAdapter(getContext(), location);
        final ListView sightsView = view.findViewById(R.id.sights);
        sightsView.setAdapter(locationAdapter);

        return view;
    }
}
