package Dto.Tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrdersFormTm extends RecursiveTreeObject<OrdersFormTm> {
    private String id;
    private String date;
    private String custId;
}
