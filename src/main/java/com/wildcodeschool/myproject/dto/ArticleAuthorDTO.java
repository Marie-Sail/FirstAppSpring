package com.wildcodeschool.myproject.dto;

import com.wildcodeschool.myproject.model.Article;
import com.wildcodeschool.myproject.model.Author;

public class ArticleAuthorDTO {

    private Long id;
    private Long authorId;
    private Long articleId;
    private String contribution;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }
}
