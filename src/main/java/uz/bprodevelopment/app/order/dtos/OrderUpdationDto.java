package uz.bprodevelopment.app.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdationDto {

    private Double latitude;
    private Double longitude;

}
