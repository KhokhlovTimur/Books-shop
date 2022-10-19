package dao.booksDao.impl;

import dao.booksDao.BooksRepository;
import models.Book;
import models.OrderBook;
import providers.MyDriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BooksRepositoryImpl implements BooksRepository {
    private final Connection connection = MyDriverManager.getConnection();

    //language=SQL
    private static final String SQL_SAVE_BOOK = "insert into books(title, author_id, year_of_publication,  price) VALUES (?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_FIND_BOOK_BY_ID = "select * from books where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BOOKS = "select * from books";

    //language=SQL
    private static final String SQL_DELETE_BOOK_BY_ID = "delete from books where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_BOOK_BY_ID = "update books set title=?, author_id=?, year_of_publication=?  where id=?";

    private final static Function<ResultSet, Book> bookMapper = row ->{
        try {
            return Book.builder().id(row.getLong("id"))
                    .title(row.getString("title"))
                    .yearOfPublication(row.getInt("year_of_publication"))
                    .authorId(row.getLong("author_id"))
                    .price( row.getInt("price"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };


    @Override
    public void saveBook(Book book) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_BOOK, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setLong(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getYearOfPublication());
            preparedStatement.setInt(4, book.getPrice());

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new SQLException("Can't save user");
            }
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                book.setId(keys.getLong("id"));
            } else {
                throw new SQLException();
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BOOK_BY_ID)) {

            statement.setLong(1, id);
            statement.execute();

            ResultSet book = statement.executeQuery();
            if (book.next()) {
                return Optional.of(bookMapper.apply(book));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    @Override
    public void deleteBookById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BOOK_BY_ID)) {
            try {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void updateBook(Book book) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BOOK_BY_ID)) {
            try {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setLong(2, book.getAuthorId());
                preparedStatement.setInt(3, book.getYearOfPublication());
                preparedStatement.setLong(4, book.getId());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_BOOKS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(bookMapper.apply(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return books;
    }
}
