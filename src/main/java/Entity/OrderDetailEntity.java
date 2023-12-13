package Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailEntity {
    private String id;
    private String code;
    private int qty;
    private double unitPrice;

}
