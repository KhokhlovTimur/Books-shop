package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookDto {
    private Long orderId;
    private Long priceAll;
    private List<BookDto> books;
}
