package michael.example.com.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewsArticleAdapter extends ArrayAdapter<NewsArticle> {
    private Context context;

    public NewsArticleAdapter(@NonNull Context context, ArrayList<NewsArticle> resource) {
        super(context, 0, resource);
        this.context = context;
    }

    public NewsArticleAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<NewsArticle>());
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newsArticleView = convertView;

        if (newsArticleView == null) {
            newsArticleView =
                    LayoutInflater.from(getContext()).inflate(R.layout.news_article_template, parent, false);
        }

        NewsArticle newsArticle = getItem(position);
        TextView articleTitle = newsArticleView.findViewById(R.id.articleTitle);
        articleTitle.setText(newsArticle.getArticleTitle());
        Date date;
        String publishingDateString = newsArticle.getPublishingDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String stringDate = "";
        try {
            date = simpleDateFormat.parse(publishingDateString);
            stringDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            stringDate = "Date not available.";
        }

        TextView publishingDate = newsArticleView.findViewById(R.id.publishingDate);
        publishingDate.setText(stringDate);
        TextView sectionName = newsArticleView.findViewById(R.id.sectionName);
        sectionName.setText("Section: " + newsArticle.getSectionName());
        TextView webUrl = newsArticleView.findViewById(R.id.webUrl);
        final String FULL_ARTICLE_URL = newsArticle.getFullArticleUrl();
        webUrl.setText(FULL_ARTICLE_URL);
        TextView contributor = newsArticleView.findViewById(R.id.contributor);
        contributor.setText(newsArticle.getContributor());

        webUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(FULL_ARTICLE_URL));
                context.startActivity(intent);
            }
        });

        return newsArticleView;
    }
}
