package Dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Item {
    private String Code;
    private String description;
    private double unitPrice;
    private int quantityOnHand;

}
