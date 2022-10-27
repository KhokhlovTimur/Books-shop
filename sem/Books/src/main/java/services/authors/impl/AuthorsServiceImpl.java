package services.authors.impl;

import dao.authorsDao.AuthorsRepository;
import models.Author;
import services.authors.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthorsServiceImpl implements AuthorService {
    private final AuthorsRepository authorsRepository;

    public AuthorsServiceImpl(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    @Override
    public void saveAuthor(Author author) {
        authorsRepository.saveAuthor(author);
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return authorsRepository.findAuthorById(id);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorsRepository.deleteAuthorById(id);
    }

    @Override
    public void updateAuthor(Author author) {
        if (authorsRepository.findAuthorById(author.getId()).isPresent()) {
            authorsRepository.updateAuthor(author);
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorsRepository.findAllAuthors();
    }
}
