package com.wildcodeschool.myproject.mapper;

import com.wildcodeschool.myproject.dto.ArticleDTO;
import com.wildcodeschool.myproject.dto.CategoryDTO;
import com.wildcodeschool.myproject.model.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDTO convertCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        if(category.getArticles() != null) {
            categoryDTO.setArticles(category.getArticles().stream().map(article -> {
                ArticleDTO articleDTO = new ArticleDTO();
                articleDTO.setId(article.getId());
                articleDTO.setTitle(article.getTitle());
                articleDTO.setContent(article.getContent());
                articleDTO.setUpdatedAt(article.getUpdatedAt());
                articleDTO.setCategoryName(article.getCategory().getName());
                return articleDTO;
            }).collect(Collectors.toList()));
        }
        return categoryDTO;
    }
}
