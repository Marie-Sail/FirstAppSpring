package com.wildcodeschool.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wildcodeschool.myproject.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
