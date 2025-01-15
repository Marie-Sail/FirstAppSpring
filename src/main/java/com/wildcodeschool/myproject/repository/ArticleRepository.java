package com.wildcodeschool.myproject.repository;

import com.wildcodeschool.myproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wildcodeschool.myproject.model.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);

    List<Article> findByContentContaining(String word);

    List<Article> findByCreatedAtAfter(LocalDateTime createdAt);

    List<Article> findTop5ByOrderByCreatedAtDesc();

    List<Article> findByCategoryName(String categoryName);

    List<Article> findByCategoryId(Long categoryId);

}
