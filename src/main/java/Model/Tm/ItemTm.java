package Model.Tm;

import javafx.scene.control.Button;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItemTm {
    private String Code;
    private String description;
    private double unitPrice;
    private int quantityOnHand;
    private Button deleteButton;
}
