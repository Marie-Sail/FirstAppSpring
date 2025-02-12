package com.wildcodeschool.myproject.mapper;

import com.wildcodeschool.myproject.dto.ImageDTO;
import com.wildcodeschool.myproject.dto.validatorDTO.ImageValidDTO;
import com.wildcodeschool.myproject.model.Article;
import com.wildcodeschool.myproject.model.Image;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ImageMapper {

    public ImageDTO convertToDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        if (image.getArticles() != null) {
            imageDTO.setArticleIds(image.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
        }
        return imageDTO;
    }

    public Image convertToEntity(ImageValidDTO imageValidDTO) {
        Image image = new Image();
//        if (imageValidDTO.getId() != null) {
//            image.setId(imageValidDTO.getId());
//        }
        image.setUrl(imageValidDTO.getUrl());

        return image;
    }
}
