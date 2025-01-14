package com.wildcodeschool.myproject.repository;

import com.wildcodeschool.myproject.model.Article;
import com.wildcodeschool.myproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);

    List<Category> findByNameContaining(String word);

}
