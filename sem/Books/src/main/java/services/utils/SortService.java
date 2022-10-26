package services.utils;

import dto.BookDto;

import java.util.Comparator;
import java.util.List;

public class SortService {
    private MapService mapService;

    public SortService(MapService mapService) {
        this.mapService = mapService;
    }

    private List<BookDto> sortBooksByAscendingPrice() {
        List<BookDto> books = mapService.convertAllBooksToBooksDtoFromBooks();
        books.sort(new Comparator<BookDto>() {
            @Override
            public int compare(BookDto o1, BookDto o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
        return books;
    }

    private List<BookDto> sortBooksByDescendingPrice() {
        List<BookDto> books = mapService.convertAllBooksToBooksDtoFromBooks();
        books.sort(new Comparator<BookDto>() {
            @Override
            public int compare(BookDto o1, BookDto o2) {
                return o2.getPrice() - o1.getPrice();
            }
        });
        return books;
    }

    private List<BookDto> sortBooksFromZtoA() {
        List<BookDto> books = mapService.convertAllBooksToBooksDtoFromBooksSortById();
        books.sort(new Comparator<BookDto>() {
            @Override
            public int compare(BookDto o1, BookDto o2) {
                return o2.getTitle().compareToIgnoreCase(o1.getTitle());
            }
        });
        return books;
    }

    private List<BookDto> sortBooksFromAtoZ() {
        List<BookDto> books = mapService.convertAllBooksToBooksDtoFromBooksSortById();
        books.sort(new Comparator<BookDto>() {
            @Override
            public int compare(BookDto o1, BookDto o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
        return books;
    }

    public List<BookDto> sortBy(String sortBy) {
        switch (sortBy) {
            case "priceAscend":
                return sortBooksByAscendingPrice();
            case "priceDescend":
                return sortBooksByDescendingPrice();
            case "az":
                return sortBooksFromAtoZ();
            case "za":
                return sortBooksFromZtoA();
        }
        return mapService.convertAllBooksToBooksDtoFromBooksSortById();
    }

}
