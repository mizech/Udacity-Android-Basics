package d.n.musicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;

/**
 * Created by michael on 04.03.18.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    private Context context;
    private ArrayList<Song> songList;

    public SongAdapter(Activity context, ArrayList<Song> songList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, songList);
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.song, parent, false);

        Song song = getItem(position);
        TextView textView = customView.findViewById(R.id.artist_title);

        final String artistTitle = song.getArtist() + " - " + song.getTitle();
        textView.setText(artistTitle);

        customView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlayingSong.class);
                intent.putExtra("listItem", artistTitle);
                context.startActivity(intent);
            }
        });

        return customView;
    }
}
