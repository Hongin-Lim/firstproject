package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticlesForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticlesController {

    @Autowired // 스프링부트가 미리 생성해 높은 리파지터리 객체를 가져옴(DI)
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticlesForm form) {
        log.info(form.toString());
        // System.out.println(form.toString());  --> 로깅 기능으로 대체!

        // 1. Dto를 Entity 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());

        // 2. Repository에게 Entity를 DB로 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "";
    }
    @GetMapping("/articles/{id}") // 데이터 조회 1개
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1: id로 데이터를 가져온다
        Article articleEntity = articleRepository.findById(id).orElse(null);
//        Optional<Article> articleEntity = articleRepository.findById(id);

        // 2: 가여온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }
    @GetMapping("/articles") // 데이터 전체 조회 index
    public String index(Model model) {

        // 1. 모든 아티클 데이터를 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 아티클 묶음을 뷰로 전달한다
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지 설정
        return "articles/index";
    }

}
