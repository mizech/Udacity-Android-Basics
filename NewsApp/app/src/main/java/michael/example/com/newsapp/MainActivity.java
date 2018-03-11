package michael.example.com.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {
    private final String URL = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private final int ID_LOADER = 1;
    private NewsArticleAdapter newsArticleAdapter = null;

    private boolean isInternetAvailable() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isInternetAvailable() == false) {
            Intent intent = new Intent(getApplicationContext(), no_internet.class);
            startActivity(intent);
            return;
        }

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(ID_LOADER, null, this);;
    }

    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int i, Bundle bundle) {
        return new NewsArticlesLoader(getApplicationContext(), this.URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> newsList) {
        newsArticleAdapter = new NewsArticleAdapter(getApplicationContext(), (ArrayList)newsList);
        final ListView listView = findViewById(R.id.newsArticlesListView);
        listView.setAdapter(newsArticleAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        newsArticleAdapter = null;
    }
}
