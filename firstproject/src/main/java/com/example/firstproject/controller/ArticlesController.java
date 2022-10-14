package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticlesForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticlesController {

    @Autowired // 스프링부트가 미리 생성해 높은 리파지터리 객체를 가져옴(DI)
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new") // read 읽기
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create") // creat 작성 dto에 담아서
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
        return "redirect:/articles/" + saved.getId();
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

    @GetMapping("/articles/{id}/edit") // update 수정
    public String edit(@PathVariable Long id, Model model) {

        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록

        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update") // 수정 수정 할때는 dto에 먼저 담아서
    public String update(ArticlesForm form) {
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환한다
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. 엔티티를 DB로 저장한다
        // 2-1 DB에서 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2 기존 데이터의 값을 갱신한다.
        if (target != null) {}
            articleRepository.save(articleEntity); // 엔티티가 DB로 갱신 (엔티티를 넣어야함)

        // 3. 수정 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다.");

        // 1. 삭제 대상을 가져온다
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 그 대상을 삭제한다.
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다!");
        }

        // 3. 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles";
    }
}
