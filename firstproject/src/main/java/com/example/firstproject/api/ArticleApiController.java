package com.example.firstproject.api;

import com.example.firstproject.dto.ArticlesForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // 데이터를 반환(JSON)
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles") // 전체 조회
    public List<Article> index() {
        return articleService.index();  // 서비스에서 index 메소드 만든거
    }

    @GetMapping("/api/articles/{id}") // 하나 조회
    public Article show(@PathVariable Long id) {
        return articleService.show(id); // 서비스에서 show 메소드 만든거
    }


    // POST
    @PostMapping("/api/articles") // 생성 create
    public ResponseEntity<Article> create(@RequestBody ArticlesForm dto) { // DTO로 받아주면 됌. json으로 보낼 땐 @RequestBody 꼭 써줘야함 form일 떄와는 다름
        Article created = articleService.create(dto);
        return (created != null) ?    // 삼항연산자로 처리
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticlesForm dto) {

        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백! 실습
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticlesForm> dtos) {

        List<Article> createdList = articleService.createArticles(dtos);

        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
