package michael.playground;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by michael on 05.03.18.
 */

public class AudioFileAdapter extends ArrayAdapter<AudioFile> {
    private Context context;
    private ArrayList<AudioFile> files;

    public AudioFileAdapter(Context context, ArrayList<AudioFile> audioFiles) {
        super(context, R.layout.demo2, audioFiles);
        this.context = context;
        this.files = audioFiles;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.demo2, null);

        AudioFile file = this.files.get(position);
        String title = file.getTitle();
        final int resid = file.getResid();

        TextView textView = row.findViewById(R.id.file_row);
        textView.setText(title);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), resid);
                mediaPlayer.start();
            }
        });

        return row;
    }
}
