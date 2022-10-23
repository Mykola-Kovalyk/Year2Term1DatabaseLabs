package ua.iot.kovalyk.mysqlspringlab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Part {
    private Integer id;
    private String partNumber;
    private Integer device;
    private Integer manufacturer;
    private Integer amount;
}
