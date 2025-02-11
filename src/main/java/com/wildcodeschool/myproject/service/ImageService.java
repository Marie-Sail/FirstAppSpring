package com.wildcodeschool.myproject.service;

import com.wildcodeschool.myproject.dto.ImageDTO;
import com.wildcodeschool.myproject.exeption.ResourceNotFoundException;
import com.wildcodeschool.myproject.mapper.ImageMapper;
import com.wildcodeschool.myproject.model.Image;
import com.wildcodeschool.myproject.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public List<ImageDTO> getAllImages(){
        List<Image> images = imageRepository.findAll();
        return images.stream().map(imageMapper:: convertToDTO).collect(Collectors.toList());
    }

    public ImageDTO getImageById(Long id){
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'image avec l'id " + id + " n'a pas été trouvé"));
        if (image == null) {
            return null;
        }
        return imageMapper.convertToDTO(image);
    }

    public ImageDTO createImage(Image image){
        Image savedImage = imageRepository.save(image);
        return imageMapper.convertToDTO(savedImage);
    }

    public ImageDTO updateImage(Long id, Image imageDetails){
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'image avec l'id " + id + " n'a pas été trouvé"));
        if (image == null) {
            return null;
        }
        image.setUrl(imageDetails.getUrl());
        Image updateImage = imageRepository.save(image);
        return imageMapper.convertToDTO(updateImage);
    }

    public boolean deleteImage(Long id){
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'image ne peut être supprimé, l'id " + id + " n'a pas été trouvé"));
        if (image == null) {
            return false;
        }

        imageRepository.delete(image);
        return true;
    }
}