package dao.books.impl;

import dao.books.BooksRepository;
import models.Book;
import providers.MyDriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BooksRepositoryImpl implements BooksRepository {
    private final Connection connection = MyDriverManager.getConnection();

    //language=SQL
    private static final String SQL_SAVE_BOOK = "insert into books(tittle, author_id, year_of_publication, quantity, price) VALUES (?, ?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_FIND_BOOK_BY_ID = "select * from books where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BOOKS = "select * from books";

    //language=SQL
    private static final String SQL_DELETE_BOOK_BY_ID = "delete from books where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_BOOK_BY_ID = "update books set tittle=?, author_id=?, year_of_publication=?, quantity = ?  where id=?";


    @Override
    public void saveBook(Book book) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_BOOK, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, book.getTittle());
            preparedStatement.setLong(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getYearOfPublication());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getPrice());

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
                return Optional.of(Book.builder()
                        .id(book.getLong("id"))
                        .tittle(book.getString("tittle"))
                        .authorId(book.getLong("author_id"))
                        .price(book.getInt("price"))
                        .yearOfPublication(book.getInt("year_of_publication"))
                        .quantity(book.getInt("quantity"))
                        .build());
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
                preparedStatement.setString(1, book.getTittle());
                preparedStatement.setLong(2, book.getAuthorId());
                preparedStatement.setInt(3, book.getYearOfPublication());
                preparedStatement.setInt(4, book.getQuantity());
                preparedStatement.setLong(5, book.getId());
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
                books.add(Book.builder().id(resultSet.getLong("id"))
                        .tittle(resultSet.getString("tittle"))
                        .authorId(resultSet.getLong("author_id"))
                        .price(resultSet.getInt("price"))
                        .yearOfPublication(resultSet.getInt("year_of_publication"))
                        .quantity(resultSet.getInt("quantity"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return books;
    }
}
