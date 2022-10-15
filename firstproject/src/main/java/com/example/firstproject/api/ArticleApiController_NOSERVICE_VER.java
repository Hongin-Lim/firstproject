//package com.example.firstproject.api;
//
//import com.example.firstproject.dto.ArticlesForm;
//import com.example.firstproject.entity.Article;
//import com.example.firstproject.repository.ArticleRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController // 데이터를 반환(JSON)
//public class ArticleApiController_NOSERVICE_VER {
//
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    // GET
//    @GetMapping("/api/articles") // 전체 조회
//    public List<Article> index() {
//        return articleRepository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}") // 하나 조회
//    public Article index(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//
//    // POST
//    @PostMapping("/api/articles") // 생성 create
//    public Article create(@RequestBody ArticlesForm dto) { // DTO로 받아주면 됌. json으로 보낼 땐 @RequestBody 꼭 써줘야함 form일 떄와는 다름
//        Article article = dto.toEntity();
//
//        return articleRepository.save(article);
//    }

//    // POST Response 까지 적용한거 샘플
//
//    @PostMapping("/api/articles") // 생성 create
//    public ResponseEntity<Article> create(@RequestBody ArticlesForm dto) { // DTO로 받아주면 됌. json으로 보낼 땐 @RequestBody 꼭 써줘야함 form일 떄와는 다름
//        log.info(dto.toString());
//        Article saved = aritleRepository.save(dto.toEntity());
//        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//        }


//
//
//    // PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticlesForm dto) {
//        // 1. 수정 용 엔티티를 생성
//        Article article = dto.toEntity();
//        log.info("id: {}", "article: {}", id, article.toString());
//
//        // 2. 대상 엔티티를 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리(대상이 업거나, id가 다른 경우)
//        if (target == null || id != article.getId()) {
//            // 400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id: {}","article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // ResponseEntity<Article> -> http 상태코드를 같이 실어줘서 보낼 수 있다.
//
//        // 4. 업데이트 및 정상 응답(200)
//        target.patch(article); // json 바디에 일부값만 ex. title만 뺴고 같을 경우를 대비 --> article entity에 클래스 만듬
////        Article updated = articleRepository.save(article); -->
//        Article updated = articleRepository.save(target);
//            return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 대상 삭제
//        articleRepository.delete(target);
//
//        // 데이터 반환
//        return ResponseEntity.status(HttpStatus.OK).build();
//
//
//    }
//
//}
