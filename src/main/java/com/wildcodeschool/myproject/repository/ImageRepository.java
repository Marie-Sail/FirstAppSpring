package com.wildcodeschool.myproject.repository;

import com.wildcodeschool.myproject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
