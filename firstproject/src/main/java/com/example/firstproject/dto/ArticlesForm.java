package com.example.firstproject.dto;


import com.example.firstproject.entity.Article;

public class ArticlesForm {
    private String title;
    private String content;

    public ArticlesForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticlesForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
