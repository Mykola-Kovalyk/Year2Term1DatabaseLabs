package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepairOption {
    private Integer id;
    private Integer device;
    private String optionName;
    private String details;
    private Float price;
}
