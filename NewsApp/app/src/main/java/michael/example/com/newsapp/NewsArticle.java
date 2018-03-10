package michael.example.com.newsapp;

/**
 * Created by michael on 10.03.18.
 */

public class NewsArticle {
    private String articleTitle;
    private String sectionName;
    private String publishingDate;
    private String fullArticleUrl;

    public NewsArticle(String articleTitle, String sectionName, String publishingDate, String fullArticleUrl) {
        this.articleTitle = articleTitle;
        this.sectionName = sectionName;
        this.publishingDate = publishingDate;
        this.fullArticleUrl = fullArticleUrl;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }

    public String getFullArticleUrl() {
        return fullArticleUrl;
    }

    public void setFullArticleUrl(String fullArticleUrl) {
        this.fullArticleUrl = fullArticleUrl;
    }
}
