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

public class MainActivity extends AppCompatActivity {
    // "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadJson dj = new DownloadJson();
        dj.execute("https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test");
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

        protected void onPostExecute(String serverResponse) {
            super.onPostExecute(serverResponse);

            try {
                JSONObject serverResponseJson = new JSONObject(serverResponse);

                JSONObject payloadJson = serverResponseJson.getJSONObject("response");
                JSONArray newsResultsJson = payloadJson.getJSONArray("results");

                Log.i("Website Content", newsResultsJson.toString());

                JSONObject first = newsResultsJson.getJSONObject(0);
                Log.i("First Object", first.getString("webTitle"));
            } catch(JSONException exception) {
                exception.printStackTrace();
            }
        }
    }
}
