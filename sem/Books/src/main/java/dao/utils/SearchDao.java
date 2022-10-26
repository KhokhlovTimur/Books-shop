package dao.utils;

import models.Book;
import providers.MyDriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchDao {
    //language=SQL
    private static final String SQL = "select books.id as id from books join authors a on a.id = books.author_id\n" +
            "join (select books.id as book_id, concat(title, ' ', authors.name, ' ', authors.surname) as names from books join authors  on books.author_id=authors.id) as tab on tab.book_id = books.id";

    private String search(String input) {
        StringBuilder select = new StringBuilder(SQL);
        String[] inputSplit = input.split(" ");
        List<String> bookInfo = Arrays.stream(inputSplit).filter(x -> x.length() > 0).collect(Collectors.toList());
        if (bookInfo.size() > 1) {
            select.append(" where names ");
            for (String info : bookInfo) {
                if (!info.chars().allMatch(Character::isDigit)) {
                    select.append("ilike " + "'%").append(info).append("%'").append(" and names ");
                } else {
                    select.append("like " + "'%").append(info).append("%'").append(" and names ");
                }
            }
            select.delete(select.length() - 10, select.length() - 1);
        } else if (bookInfo.size() == 1) {
            select.append(" where names ");
            if (!bookInfo.get(0).chars().allMatch(Character::isDigit)) {
                select.append("ilike " + "'%").append(bookInfo.get(0)).append("%'");
            }
            else {
                select.append("like " + "'%").append(bookInfo.get(0)).append("%'");
            }
        }
        return select.toString();
    }

    public List<Long> getSearchResult(String input){
        String SQL_SELECT = search(input);
        List<Long> ids = new ArrayList<>();
        try (PreparedStatement statement = MyDriverManager.getConnection().prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return ids;
    }
}
