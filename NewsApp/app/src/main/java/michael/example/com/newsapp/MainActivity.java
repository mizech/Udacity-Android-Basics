package michael.example.com.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String urlString = "http://content.guardianapis.com/search?q=debates&api-key=test";

        DownloadJson dj = new DownloadJson();
        dj.execute(urlString);
    }

    public class DownloadJson extends AsyncTask<String, Void, String> {
        // Doing asynchronous task in Java.
        // Used documentation: https://developer.android.com/reference/android/os/AsyncTask.html
        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;
            } catch (MalformedURLException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String serverResponse) { // serverResponse is the return-value of doInBackground().
            super.onPostExecute(serverResponse);

            try {
                JSONObject serverResponseJson = new JSONObject(serverResponse);

                JSONObject payloadJson = serverResponseJson.getJSONObject("response");
                JSONArray newsResultsJson = payloadJson.getJSONArray("results");

                for (int i = 0; i < newsResultsJson.length(); i++) {
                    JSONObject currentArticle = newsResultsJson.getJSONObject(i);

                    String articleTitle = currentArticle.getString("webTitle");
                    String sectionName = currentArticle.getString("sectionName");
                    String publishingDate = currentArticle.getString("webPublicationDate");
                    String fullArticleUrl = currentArticle.getString("webUrl");

                    newsArticles.add(new NewsArticle(articleTitle, sectionName,
                                         publishingDate, fullArticleUrl));
                }
            } catch(JSONException exception) {
                exception.printStackTrace();
            }

            Log.i("Test:", newsArticles.get(0).getArticleTitle());
            Log.i("Test:", newsArticles.get(1).getArticleTitle());
        }
    }
}
