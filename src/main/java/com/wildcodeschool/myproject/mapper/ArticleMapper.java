package com.wildcodeschool.myproject.mapper;

import com.wildcodeschool.myproject.dto.ArticleDTO;
import com.wildcodeschool.myproject.dto.AuthorDTO;
import com.wildcodeschool.myproject.dto.validatorDTO.ArticleCreateDTO;
import com.wildcodeschool.myproject.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleDTO.setCategoryName(article.getCategory().getName());
        }
        if (article.getImages() != null) {
            articleDTO.setImagesUrls(article.getImages().stream().map(Image::getUrl).collect(Collectors.toList()));
        }
        if (article.getArticleAuthors() != null) {
            articleDTO.setAuthors(article.getArticleAuthors().stream()
                    .filter(articleAuthor -> articleAuthor.getAuthor() != null)
                    .map(articleAuthor -> {
                        AuthorDTO authorDTO = new AuthorDTO();
                        authorDTO.setId(articleAuthor.getAuthor().getId());
                        authorDTO.setFirstname(articleAuthor.getAuthor().getFirstname());
                        authorDTO.setLastname(articleAuthor.getAuthor().getLastname());
                        return authorDTO;
                    })
                    .collect(Collectors.toList()));
        }
        return articleDTO;
    }

    public Article convertToEntity(ArticleCreateDTO articleCreateDTO) {
        Article article = new Article();
        article.setTitle(articleCreateDTO.getTitle());
        article.setContent(articleCreateDTO.getContent());

        if (articleCreateDTO.getCategoryId() != null) {
            Category category = new Category();
            category.setId(articleCreateDTO.getCategoryId());
            article.setCategory(category);
        }

        if (articleCreateDTO.getImages() != null) {
            List<Image> images = articleCreateDTO.getImages().stream()
                    .map(image -> {
                        Image newImage = new Image();
                        newImage.setUrl(image.getUrl());

                        return newImage;
                    } ).toList();

            article.setImages(images);
        }

        if (articleCreateDTO.getAuthors() != null) {
            List<ArticleAuthor> authors = articleCreateDTO.getAuthors().stream()
                    .map(authorContributionDTO -> {
                        ArticleAuthor newArticleAuthor = new ArticleAuthor();
                        Author author = new Author();

                        author.setId(authorContributionDTO.getAuthorId());
                        newArticleAuthor.setAuthor(author);
                        newArticleAuthor.setContribution(authorContributionDTO.getContribution());

                        return newArticleAuthor;
                    }).toList();

            article.setArticleAuthors(authors);
        }

        return article;
    }
}
