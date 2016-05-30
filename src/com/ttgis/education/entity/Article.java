package com.ttgis.education.entity;

import java.util.Date;

public class Article {
    private String articleId;

    private String ltbqId;

    private String articleName;

    private String articleAuthor;

    private Integer articleViewer;

    private Date articleTime;

    private String articleType;

    private Date articleAt;

    private Integer articleDel;

    private String articleContent;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getLtbqId() {
        return ltbqId;
    }

    public void setLtbqId(String ltbqId) {
        this.ltbqId = ltbqId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Integer getArticleViewer() {
        return articleViewer;
    }

    public void setArticleViewer(Integer articleViewer) {
        this.articleViewer = articleViewer;
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public Date getArticleAt() {
        return articleAt;
    }

    public void setArticleAt(Date articleAt) {
        this.articleAt = articleAt;
    }

    public Integer getArticleDel() {
        return articleDel;
    }

    public void setArticleDel(Integer articleDel) {
        this.articleDel = articleDel;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}