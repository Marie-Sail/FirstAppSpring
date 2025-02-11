package com.wildcodeschool.myproject.service;

import com.wildcodeschool.myproject.dto.CategoryDTO;
import com.wildcodeschool.myproject.exeption.ResourceNotFoundException;
import com.wildcodeschool.myproject.mapper.CategoryMapper;
import com.wildcodeschool.myproject.model.Category;
import com.wildcodeschool.myproject.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::convertCategoryDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categorie avec l'id " + id + " n'a pas été trouvé"));
        if(category == null) {
            return null;
        }

        return categoryMapper.convertCategoryDTO(category);
    }

    public List<CategoryDTO> getCategoryByTitle(String title) {
        List<Category> categories = categoryRepository.findByName(title);
        if(categories == null) {
            return null;
        }

        return categories.stream().map(categoryMapper::convertCategoryDTO).collect(Collectors.toList());
    }

    public CategoryDTO createCategory(Category newCategory) {
        Category savedCategory = categoryRepository.save(newCategory);

        return categoryMapper.convertCategoryDTO(savedCategory);
    }

    public CategoryDTO updateCategory(Long id, Category newCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categorie avec l'id " + id + " n'a pas été trouvé"));
        if(category == null) {
            return null;
        }
        category.setName(newCategory.getName());
        Category updateCategory = categoryRepository.save(category);

        return categoryMapper.convertCategoryDTO(updateCategory);
    }

    public boolean deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categorie ne peut être supprimé, l'id " + id + " n'a pas été trouvé"));
        if(category == null) {
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }

}
