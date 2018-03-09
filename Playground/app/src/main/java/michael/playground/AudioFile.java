package michael.playground;

/**
 * Created by michael on 05.03.18.
 */

public class AudioFile {
    private int resid;
    private String title;

    public AudioFile(int resid, String title) {
        this.resid = resid;
        this.title = title;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
