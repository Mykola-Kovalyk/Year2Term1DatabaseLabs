package ua.iot.kovalyk.mysqlspringlab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Device {
    private Integer id;
    private Integer manufacturer;
    private String serialNumber;
}
