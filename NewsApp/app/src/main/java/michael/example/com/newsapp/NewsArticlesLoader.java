package michael.example.com.newsapp;

import android.content.Context;
import android.content.Intent;
import android.content.AsyncTaskLoader;
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
import java.util.List;

public class NewsArticlesLoader extends AsyncTaskLoader<List<NewsArticle>> {
    private static String usedUrl;

    public NewsArticlesLoader(Context context, String urlToUse) {
        super(context);
        this.usedUrl = urlToUse;
    }

    private void handleNoDataAvailable() {
        Intent intent = new Intent(getContext(), no_data.class);
        getContext().startActivity(intent);
    }

    private String doHttpRequest() {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(this.usedUrl);
            urlConnection = (HttpURLConnection) url.openConnection();

            if (urlConnection == null) {
                Intent intent = new Intent(getContext(), no_internet.class);
                getContext().startActivity(intent);
            }

            InputStream in = urlConnection.getInputStream();

            if (in == null) {
                Intent intent = new Intent(getContext(), no_data.class);
                getContext().startActivity(intent);
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

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsArticle> loadInBackground() {
        ArrayList<NewsArticle> list = new ArrayList<>();
        String requestAsString = doHttpRequest();

        try {
            JSONObject serverResponseJson = new JSONObject(requestAsString);

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

                list.add(new NewsArticle(articleTitle, sectionName,
                        publishingDate, fullArticleUrl, contributor));
            }
        } catch (JSONException exception) {
            handleNoDataAvailable();
        }

        return list;
    }
}
