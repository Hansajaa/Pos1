package Dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ItemDto {
    private String Code;
    private String description;
    private double unitPrice;
    private int quantityOnHand;

}
