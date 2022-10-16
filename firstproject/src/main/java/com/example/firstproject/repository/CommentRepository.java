package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> { //entity와 id타입

    // 특정 게시글의 모든 댓글 조회
    @Query(value =
            "SELECT * " +
            "FROM comment " +
            "WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId); //articleId를 찾지 못해 에러 발생 시, @Parm 어노테이션으로 파라미터 정보 추가

    // 특정 닉네임의 모든 댓글 조회 *** 얘는 쿼리어노테이션으로 만들어보지 않고 orm.xml으로 만드는 방법으로 해보았음.
    List<Comment> findByNickname(String nickname);
}
