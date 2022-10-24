package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Author;
import models.Book;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private int yearOfPublication;
    private String description;
    private String authorName;
    private String authorSurname;
    private int authorYearOfBirth;
    private int price;
}
