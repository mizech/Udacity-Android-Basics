package d.n.musicapp;

/**
 * Created by michael on 03.03.18.
 */

public class Song {
    private String title;
    private String artist;
    private String album;
    private String playLength;
    private String releaseYear;
    private String info;

    public Song(String title, String artist, String album, String playLength, String releaseYear, String info) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.playLength = playLength;
        this.releaseYear = releaseYear;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getPlayLength() {
        return playLength;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getInfo() {
        return info;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setPlayLength(String playLength) {
        this.playLength = playLength;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
