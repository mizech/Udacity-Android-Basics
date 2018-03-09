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

public class Shopping extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sights, container, false);

        ArrayList<Location> location = new ArrayList<Location>();

        location.add((new Location("Karstadt", "Simeonstr. 46", "0651 469 - 0", "info@karstadt.de", "https://www.karstadt.de/on/demandware.store/Sites-Karstadt-Site/de/Stores-Details?StoreID=001261&src=90L100001")));
        location.add((new Location("Unverpackt Trier", "Paulinstraße 65", "0651 56142563", "unverpackt@telekom.de", "https://www.unverpackt-trier.de/")));

        LocationAdapter locationAdapter = new LocationAdapter(getContext(), location);
        final ListView sightsView = view.findViewById(R.id.sights);
        sightsView.setAdapter(locationAdapter);

        return view;
    }
}
