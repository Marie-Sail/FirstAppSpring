package com.wildcodeschool.myproject.dto.validatorDTO;

import org.hibernate.validator.constraints.URL;

public class ImageValidDTO {

    private Long id;
    @URL(message = "L'URL de l'image doit être valide")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
