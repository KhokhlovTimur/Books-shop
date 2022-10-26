package dao.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDao {
    //language=SQL
    private static StringBuilder SQL = new StringBuilder("select books.id, books.title as title, a.name as a_name, a.surname as a_surname from books join authors a on a.id = books.author_id\n" +
            "    join (select books.id as book_id, concat(title, ' ', authors.name, ' ', authors.surname) as names from books join authors  on books.author_id=authors.id) as tab on tab.book_id = books.id\n" +
            "where names ");

    public void search(String input) {
        String[] inputSplit = input.split(" ");
        List<String> bookInfo = Arrays.stream(inputSplit).filter(x -> x.length() > 0).collect(Collectors.toList());
        if (bookInfo.size() > 1) {
            for (String info : bookInfo) {
                    SQL.append("like " + "'%").append(info).append("%").append(" and names ");
            }
        }
        else if(bookInfo.size() == 1){
            SQL.append("like " + "'%").append(bookInfo.get(0)).append("%");
        }
        else {

        }
    }


}
