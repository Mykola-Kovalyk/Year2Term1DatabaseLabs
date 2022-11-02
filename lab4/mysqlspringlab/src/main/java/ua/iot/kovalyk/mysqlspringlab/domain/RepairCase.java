package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepairCase {
    private Integer id;
    private Integer repairOption;
    private Integer repairman;
    private Timestamp opened;
    private Timestamp closed;
    private Boolean failed;
}
