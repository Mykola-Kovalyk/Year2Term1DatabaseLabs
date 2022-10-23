package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkingHours {
    private Integer id;
    private Integer repairman;
    private String day;
    private Time start;
    private Time end;
}
