package com.wildcodeschool.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wildcodeschool.myproject.model.Article;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);

    List<Article> findByContentContaining(String word);

    List<Article> findByCreatedAtAfter(LocalDateTime createdAt);

    List<Article> findTop5ByOrderByCreatedAtDesc();


}
