package michael.example.com.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<NewsArticle> newsArticles = new ArrayList<>();
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

    private void handleNoDataAvailable() {
        Intent intent = new Intent(getApplicationContext(), no_data.class);
        startActivity(intent);
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

        String urlString = "http://content.guardianapis.com/search?q=debates&api-key=test";

        DownloadJson dj = new DownloadJson();
        dj.execute(urlString);
    }

    public class DownloadJson extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection == null) {
                    Intent intent = new Intent(getApplicationContext(), no_internet.class);
                    startActivity(intent);
                }

                InputStream in = urlConnection.getInputStream();

                if (in == null) {
                    Intent intent = new Intent(getApplicationContext(), no_data.class);
                    startActivity(intent);
                }

                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;
            } catch (MalformedURLException exception) {
                handleNoDataAvailable();
            } catch (IOException exception) {
                handleNoDataAvailable();
            } catch (Exception exception) {
                handleNoDataAvailable();
            }

            return "";
        }

        protected void onPostExecute(String serverResponse) { // serverResponse is the return-value of doInBackground().
            super.onPostExecute(serverResponse);

            try {
                JSONObject serverResponseJson = new JSONObject(serverResponse);

                if (!serverResponseJson.has("response")) {
                    handleNoDataAvailable();
                }

                JSONObject payloadJson = serverResponseJson.getJSONObject("response");
                JSONArray newsResultsJson = payloadJson.getJSONArray("results");

                for (int i = 0; i < newsResultsJson.length(); i++) {
                    JSONObject currentArticle = newsResultsJson.getJSONObject(i);
                    String articleTitle = currentArticle.getString("webTitle");
                    String sectionName = currentArticle.getString("sectionName");
                    String contributor = "Contributor not known.";

                    if (currentArticle.has("contributor")) {
                        contributor = currentArticle.getString("contributor");
                    }

                    String publishingDate = "Publ.-date unknown.";

                    if (currentArticle.has("webPublicationDate")) {
                        publishingDate = currentArticle.getString("webPublicationDate");
                    }

                    String fullArticleUrl = currentArticle.getString("webUrl");

                    newsArticles.add(new NewsArticle(articleTitle, sectionName,
                            publishingDate, fullArticleUrl, contributor));
                }
            } catch (JSONException exception) {
                handleNoDataAvailable();
            }

            NewsArticleAdapter newsArticleAdapter = new NewsArticleAdapter(getApplicationContext(), newsArticles);
            final ListView listView = findViewById(R.id.newsArticlesListView);
            listView.setAdapter(newsArticleAdapter);
        }
    }
}
