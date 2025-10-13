package example.실습.실습4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private int id;
    private String title;
    private int stock;
}
