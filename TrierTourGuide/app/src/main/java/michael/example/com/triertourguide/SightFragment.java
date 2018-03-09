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

        location.add((new Location("Kaiserthermen", "Am Ostring 5", "012345", "trier.info@telekom.de", "http://www.trier-info.de/kaiserthermen-info")));
        location.add((new Location("Karl Marx Haus", "Brückenstraße 10", "0163453", "Karl-Marx-Haus@fes.de", "https://www.fes.de/museum-karl-marx-haus/")));
        location.add((new Location("Amphitheater", "Olewiger Strasse", "0163453", "-", "http://www.trier-info.de/amphitheater-info")));

        LocationAdapter locationAdapter = new LocationAdapter(getContext(), location);
        final ListView sightsView = view.findViewById(R.id.sights);
        sightsView.setAdapter(locationAdapter);

        return view;
    }
}
