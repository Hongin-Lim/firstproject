package com.example.firstproject.api;


import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
public class CommnetApiController {

    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/commnets")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/commnets")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) { // 리퀘스트 바디 dto에 json 데이터가 담겨져있음

        // 서비스에게 위임
        CommentDto createdDto = commentService.create(articleId, dto);

        // 결과 응답

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    // 댓글 수정
    @PatchMapping("/api/commnets/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) { // 리퀘스트 바디 dto에 ex. postman에 json 데이터가 담겨져있음 json으로 보낼떄 씀

        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);

        // 결과 응답

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/commnets/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {

        // 서비스에게 위임
        CommentDto updatedDto = commentService.delete(id);

        // 결과 응답

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);

    }
}
