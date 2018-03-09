package d.n.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SongInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);

        Intent intent = getIntent();

        String artist = intent.getStringExtra("artist");
        String title = intent.getStringExtra("title");
        String releaseYear = intent.getStringExtra("releaseYear");
        String info = intent.getStringExtra("info");

        TextView artistTextView = findViewById(R.id.artist);
        artistTextView.setText("Artist: " + artist);

        TextView titleTextView = findViewById(R.id.title);
        titleTextView.setText("Title: " + title);

        TextView relaseYearTextView = findViewById(R.id.releaseYear);
        relaseYearTextView.setText("Release Year: " + releaseYear);

        TextView backgroundInfoTextView = findViewById(R.id.backgroundInfo);
        backgroundInfoTextView.setText("Background: " + info);

        Button backToCurrentlyPlaying = findViewById(R.id.backToCurrentlyPlaying);
        backToCurrentlyPlaying.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
