package com.wildcodeschool.myproject.controller;

import com.wildcodeschool.myproject.dto.ArticleDTO;
import com.wildcodeschool.myproject.dto.validatorDTO.ArticleCreateDTO;
import com.wildcodeschool.myproject.model.*;
import com.wildcodeschool.myproject.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
;


@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
     this.articleService = articleService;
    }

// GET
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        ArticleDTO article = articleService.getArticleById(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

//Post
    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleCreateDTO articleCreateDTO) {
        ArticleDTO savedArticle = articleService.createArticle(articleCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id,@Valid @RequestBody ArticleCreateDTO articleCreateDTO) {
        ArticleDTO updatedArticle = articleService.updateArticle(id, articleCreateDTO);
        if (updatedArticle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.deleteArticle(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}