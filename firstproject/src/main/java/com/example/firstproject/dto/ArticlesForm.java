package com.example.firstproject.dto;


import com.example.firstproject.entity.Article;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor

public class ArticlesForm {

    private Long id; // id 필드 추가
    private String title;
    private String content;


//    public ArticlesForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "ArticlesForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
