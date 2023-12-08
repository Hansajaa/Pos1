package Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemEntity {
    private String code;
    private String description;
    private  double unitPrice;
    private int qtyOnHand;

}
