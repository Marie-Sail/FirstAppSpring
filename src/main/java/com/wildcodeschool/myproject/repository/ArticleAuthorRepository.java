package com.wildcodeschool.myproject.repository;

import com.wildcodeschool.myproject.model.ArticleAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleAuthorRepository extends JpaRepository<ArticleAuthor, Long> {
}
