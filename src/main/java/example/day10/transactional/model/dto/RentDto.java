package example.day10.transactional.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentDto {
    private int id;
    private int book_id;
    private String member;
    private String rent_date;
    private String return_date;
}
