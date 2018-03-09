package michael.playground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AudioFileAdapter audioFileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<AudioFile> files = new ArrayList<>();

        files.add(new AudioFile(R.raw.drops, "Water Drops"));
        files.add(new AudioFile(R.raw.phone, "Phone Ring"));
        files.add(new AudioFile(R.raw.sound2, "Some Silly Sound"));
<<<<<<< HEAD
=======
        files.add(new AudioFile(R.raw.color_green, "Color Green"));
>>>>>>> Added TourGuide.

        ListView listView = findViewById(R.id.audio_files_list);

        audioFileAdapter = new AudioFileAdapter(this, files);
        listView.setAdapter(audioFileAdapter);
    }
}
