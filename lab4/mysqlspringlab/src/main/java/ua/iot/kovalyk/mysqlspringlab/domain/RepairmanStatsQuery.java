package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepairmanStatsQuery {
    private Integer id;
    private String name;
    private Integer repairCaseCount;
    private Float successRatio;
}
