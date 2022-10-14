package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식 가능! (해당 클래스로 테이블을 만듬!)
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Article {

    @Id // 대표값 지정 유니크함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID를 자동으로 생성 한다. 1,2,3 ~ 자동으로 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

//    public Long getId() {
//        return id;
//    }

//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
