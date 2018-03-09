package d.n.musicapp;

import java.util.ArrayList;

/**
 * Created by michael on 03.03.18.
 */

public class SongList {
    private static ArrayList<Song> songList = new ArrayList<Song>();;

    static {
        songList.add(new Song("Running Up That Hill", "Kate Bush",
                "Hounds Of Love", "5:06", "1985",
                "\"Running Up That Hill\" is a song by the English singer-songwriter Kate Bush. It was the first single from her 1985 album, Hounds of Love, released in the United Kingdom on 5 August 1985. It was her first 12\" single. It was the most successful of Bush's 1980s releases, entering the UK chart at number 9 and eventually peaking at number 3, her second-highest single peak."));
        songList.add(new Song("High Hopes", "Pink Floyd", "The Division Bell",
                "8:32", "1994",
                "\"High Hopes\" is the eleventh and final track from the 1994 Pink Floyd album The Division Bell. Its lyrics speak of the things one may have gained and lost in life, written from Gilmour's autobiographic perspective. Gilmour has said that the song is more about his early days, and leaving his hometown behind, than about the seeds of division supposedly planted in Pink Floyd's early days."));
        songList.add(new Song("This Is Not America", "David Bowie",
                "The Falcon And The Snowman", "3:51", "1985",
                "\"This Is Not America\" is a song by jazz fusion band Pat Metheny Group and rock singer David Bowie, taken from the soundtrack for the film The Falcon and the Snowman."));
        songList.add(new Song("Karma Police", "Radiohead", "OK Computer",
                "1997", "4:24",
                "\"Karma Police\" is a song by the English alternative rock band Radiohead, released as the second single from their third studio album OK Computer (1997) on 25 August 1997. The song's title and lyrics derive from an in-joke among the band, referring to karma, the Hindu theory of cause and effect."));

    }

    public static ArrayList getSongList() {
        return songList;
    }
}
