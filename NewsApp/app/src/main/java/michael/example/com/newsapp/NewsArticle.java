package michael.example.com.newsapp;

/**
 * Created by michael on 10.03.18.
 */

public class NewsArticle {
    private String articleTitle;
    private String sectionName;
    private String publishingDate;
    private String fullArticleUrl;
    private String contributor;

    public NewsArticle(String articleTitle, String sectionName,
                       String publishingDate, String fullArticleUrl, String contributor) {
        this.articleTitle = articleTitle;
        this.sectionName = sectionName;
        this.publishingDate = publishingDate;
        this.fullArticleUrl = fullArticleUrl;
        this.contributor = contributor;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public String getFullArticleUrl() {
        return fullArticleUrl;
    }

    public String getContributor() {
        return this.contributor;
    }
}
