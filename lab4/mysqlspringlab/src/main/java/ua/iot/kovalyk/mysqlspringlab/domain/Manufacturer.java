package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Manufacturer {
    private Integer id;
    private String name;
}
