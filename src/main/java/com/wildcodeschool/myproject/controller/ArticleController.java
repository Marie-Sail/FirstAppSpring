package com.wildcodeschool.myproject.controller;

import com.wildcodeschool.myproject.dto.ArticleDTO;
import com.wildcodeschool.myproject.model.Article;
import com.wildcodeschool.myproject.model.Category;
import com.wildcodeschool.myproject.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wildcodeschool.myproject.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public ArticleController(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    private ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleDTO.setCategoryName(article.getCategory().getName());
        }
        return articleDTO;
    }

// GET
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(article));
    }

    @GetMapping("/search-title")
    public ResponseEntity<List<ArticleDTO>> getArticlesByTitle(@RequestParam String searchTerms) {
        List<Article> articles = articleRepository.findByTitle(searchTerms);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(this::convertToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/search-content")
    public ResponseEntity<List<ArticleDTO>> getArticlesByContent(@RequestParam String searchContent) {
        List<Article> articles = articleRepository.findByContentContaining(searchContent);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(this::convertToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/search-date")
    public ResponseEntity<List<ArticleDTO>> getArticlesCreateAfter(@RequestParam String searchDate) {
        LocalDateTime date = LocalDateTime.parse(searchDate);
        List<Article> articles = articleRepository.findByCreatedAtAfter(date);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(this::convertToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/search-last-article")
    public ResponseEntity<List<ArticleDTO>> getFiveLastArticles() {
        List<Article> articles = articleRepository.findTop5ByOrderByCreatedAtDesc();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(this::convertToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/search-by-category")
    public ResponseEntity<List<ArticleDTO>> getArticlesByCategory(@RequestParam String searchCategory) {

        List<Article> articles = articleRepository.findByCategoryName(searchCategory);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(this::convertToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/search-by-category/{searchCategoryId}")
    public ResponseEntity<List<ArticleDTO>> getArticlesByCategoryId(@PathVariable Long searchCategoryId) {

        List<Article> articles = articleRepository.findByCategoryId(searchCategoryId);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(this::convertToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(articlesDTO);
    }

//Post
    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody Article article) {
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());

        // Ajout de la catégorie
        if (article.getCategory() != null) {
            Category category = categoryRepository.findById(article.getCategory().getId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().body(null);
            }
            article.setCategory(category);
        }

        Article savedArticle = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedArticle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {

        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        // Mise à jour de la catégorie
        if (articleDetails.getCategory() != null) {
            Category category = categoryRepository.findById(articleDetails.getCategory().getId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().body(null);
            }
            article.setCategory(category);
        }

        Article updatedArticle = articleRepository.save(article);
        return ResponseEntity.ok(convertToDTO(updatedArticle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {

        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        articleRepository.delete(article);
        return ResponseEntity.noContent().build();
    }

}