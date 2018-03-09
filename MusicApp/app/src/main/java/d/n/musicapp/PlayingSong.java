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

import java.util.ArrayList;

public class PlayingSong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_song);

        Intent intent = getIntent();

        String songTitle = intent.getStringExtra("listItem");
        String[]  songInfos = songTitle.split(" - ");

        int i = 0;
        ArrayList<Song> songList = SongList.getSongList();
        String title = "";
        String artist = "";
        String album = "";
        String playLength = "";
        String releaseYear = "";
        String backgroundInfo = "";

        while (i < songList.size()) {
            Song song = songList.get(i++);

            if (song.getArtist().equals(songInfos[0]) && song.getTitle().equals(songInfos[1])) {
                title = song.getTitle();
                artist = song.getArtist();
                album = song.getAlbum();
                playLength = song.getPlayLength();
                releaseYear = song.getReleaseYear();
                backgroundInfo = song.getInfo();
            }
        }

        TextView artistTextView = findViewById(R.id.artist);
        artistTextView.setText("Artist: " + artist);

        TextView titleTextView = findViewById(R.id.title);
        titleTextView.setText("Title: " + title);

        TextView albumTextView = findViewById(R.id.album);
        albumTextView.setText("Album: " + album);

        TextView playLengthTextView = findViewById(R.id.playLength);
        playLengthTextView.setText("Play Length: " + playLength);

        Button backToSonglist = findViewById(R.id.backToSonglist);
        backToSonglist.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        final String TITLE = title;
        final String ARTIST = artist;
        final String RELEASE_YEAR = releaseYear;
        final String INFO = backgroundInfo;

        Button goToInfo = findViewById(R.id.goToInfo);
        goToInfo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SongInfo.class);
                intent.putExtra("title", TITLE);
                intent.putExtra("artist", ARTIST);
                intent.putExtra("releaseYear", RELEASE_YEAR);
                intent.putExtra("info", INFO);
                startActivity(intent);
            }
        });
    }
}
