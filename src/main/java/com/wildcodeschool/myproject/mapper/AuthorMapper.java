package com.wildcodeschool.myproject.mapper;

import com.wildcodeschool.myproject.dto.AuthorDTO;
import com.wildcodeschool.myproject.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstname(author.getFirstname());
        authorDTO.setLastname(author.getLastname());

        return authorDTO;
    }
}
