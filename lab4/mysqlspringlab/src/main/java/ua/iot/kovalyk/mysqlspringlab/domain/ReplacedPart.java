package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplacedPart {
    private Integer id;
    private Integer repairCase;
    private Integer replacedPart;
}
