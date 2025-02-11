package com.wildcodeschool.myproject.controller;

import com.wildcodeschool.myproject.dto.CategoryDTO;
import com.wildcodeschool.myproject.dto.ArticleDTO;
import com.wildcodeschool.myproject.model.Category;
import com.wildcodeschool.myproject.repository.CategoryRepository;
import com.wildcodeschool.myproject.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }


//    GET
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @GetMapping("/search-name")
    public ResponseEntity<List<CategoryDTO>> getCategoryByTitle(@RequestParam String searchName) {
        List<CategoryDTO> categories = categoryService.getCategoryByTitle(searchName);
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categories);
    }

//    POST
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category) {

        CategoryDTO savedCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

//    PUT
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        CategoryDTO category = categoryService.updateCategory(id, categoryDetails);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category);
    }

//    DELET
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
