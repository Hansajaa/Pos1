package Dto.Tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDetailTm extends RecursiveTreeObject<OrderDetailTm> {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
}
