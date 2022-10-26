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
        authorsRepository.updateAuthor(author);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorsRepository.findAllAuthors();
    }

    @Override
    public List<Author> findAuthorBySurname(String surname) {
        return findAllAuthors().stream()
                .filter(x->x.getSurname().toLowerCase(Locale.ROOT).equals(surname.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> findAuthorByNoFullSurname(String surname) {
        return findAllAuthors().stream()
                .filter(x->x.getSurname().toLowerCase(Locale.getDefault()).contains(surname.toLowerCase(Locale.getDefault())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> findAuthorByName(String name) {
        return findAllAuthors().stream()
                .filter(x -> x.getName().toLowerCase().equals(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> findAuthorByNoFullName(String name) {
        return findAllAuthors().stream()
                .filter(x -> x.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
