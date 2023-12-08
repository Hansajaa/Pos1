package Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerEntity {
    private String customerId;
    private String name;
    private String address;
    private double salary;
}
