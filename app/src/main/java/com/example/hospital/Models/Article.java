package com.example.hospital.Models;

public class Article {

    private String articleHeading ,articleImage ,articleDescription,articleId ;

    public Article(String articleHeading, String articleImage, String articleDescription, String articleId) {
        this.articleHeading = articleHeading;
        this.articleImage = articleImage;
        this.articleDescription = articleDescription;
        this.articleId = articleId;
    }


    public String getArticleDescription() {
        return articleDescription;
    }

    public String getArticleHeading() {
        return articleHeading;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getArticleImage() {
        return articleImage;
    }


}
