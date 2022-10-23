package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepairmanByCompetenceQuery {
    private Integer repairman;
    private Integer device;
    private Integer amountRepaired;
}
