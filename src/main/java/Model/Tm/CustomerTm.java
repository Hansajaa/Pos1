package Model.Tm;

import javafx.scene.control.Button;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private double salary;
    private Button btn;
}
