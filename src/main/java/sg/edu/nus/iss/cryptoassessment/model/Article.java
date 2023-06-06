package sg.edu.nus.iss.cryptoassessment.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Article {

    private String id;
    private long publishDate;
    private String title;
    private String url;
    private String imageUrl;
    private String body;
    private String tags;
    private String categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", publishDate=" + publishDate + ", title=" + title + ", url=" + url
                + ", imageUrl=" + imageUrl + ", body=" + body + ", tags=" + tags + ", categories=" + categories + "]";
    }

    public Article create(String json) throws Exception {
        Article a = new Article();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();

            a.setId(o.getString("id"));
            a.setPublishDate(o.getJsonNumber("published_on").longValue());
            a.setTitle(o.getString("title"));
            a.setUrl(o.getString("url"));
            a.setImageUrl(o.getString("imageurl"));
            a.setBody(o.getString("body"));
            a.setTags(o.getString("tags"));
            a.setCategories(o.getString("categories"));
        }

        return a;
    }

}
