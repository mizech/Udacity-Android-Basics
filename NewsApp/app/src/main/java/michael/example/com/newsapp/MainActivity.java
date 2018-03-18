package michael.example.com.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String maxArticles = prefs.getString("max_articles", null);
        String orderBy = prefs.getString("order_by", null);
        int maxCountArticles = 8;

        try {
            if (maxArticles != null && !"".equals(maxArticles)) {
                maxCountArticles = Integer.parseInt(maxArticles);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            maxCountArticles = 8;
        }

        ArrayList<NewsArticle> adaptedNewsList = new ArrayList<>();

        try {
            if (orderBy != null && !"".equals(orderBy) && orderBy.equals("desc")) {
                Collections.reverse(newsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < maxCountArticles; i++) {
            adaptedNewsList.add(newsList.get(i));
        }

        newsArticleAdapter = new NewsArticleAdapter(getApplicationContext(), adaptedNewsList);

        final ListView listView = findViewById(R.id.newsArticlesListView);
        listView.setAdapter(newsArticleAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        newsArticleAdapter = null;
    }

    @Override
    // This method initialize the contents of the Activity's options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // This method is called whenever an item in the options menu is selected.
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
