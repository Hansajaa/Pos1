package Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrdersEntity {
    private String id;
    private String date;
    private String customerId;

}
