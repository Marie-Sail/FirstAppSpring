package com.wildcodeschool.myproject.service;

import com.wildcodeschool.myproject.dto.AuthorDTO;
import com.wildcodeschool.myproject.mapper.AuthorMapper;
import com.wildcodeschool.myproject.model.Author;
import com.wildcodeschool.myproject.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::convertToDTO).collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return null;
        }
        return authorMapper.convertToDTO(author);
    }

    public AuthorDTO createAuthor(Author author) {

        author.setFirstname(author.getFirstname());
        author.setLastname(author.getLastname());
        authorRepository.save(author);

        return authorMapper.convertToDTO(author);
    }

    public AuthorDTO updateAuthor(Long id, Author newAuthor) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return null;
        }

        author.setFirstname(newAuthor.getFirstname());
        author.setLastname(newAuthor.getLastname());

        Author updatedAuthor = authorRepository.save(author);

        return authorMapper.convertToDTO(updatedAuthor);
    }

    public boolean deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return false;
        }
        authorRepository.delete(author);
        return true;
    }
}
