package com.wildcodeschool.myproject.repository;

import com.wildcodeschool.myproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
