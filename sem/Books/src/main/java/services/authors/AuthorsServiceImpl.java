package services.authors;

import dao.authorsDao.AuthorsRepository;
import models.Author;

import java.util.List;
import java.util.Optional;

public class AuthorsServiceImpl implements AuthorService{
    private final AuthorsRepository authorsRepository;
    public AuthorsServiceImpl(AuthorsRepository authorsRepository){
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
}
