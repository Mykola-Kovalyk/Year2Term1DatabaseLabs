package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.RepairmanDao;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairmanByCompetenceQuery;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairmanStatsQuery;

import java.sql.Time;
import java.util.List;

@Service
public class RepairmanService extends GeneralService<Repairman, Integer> {

    private RepairmanDao repairmanDao;

    @Autowired
    public RepairmanService(RepairmanDao repairmanDao) {
        generalDao = this.repairmanDao = repairmanDao;
    }

    public List<Integer> getAllFreeRepairmenAsOfTime(String day, Time time) {
        return repairmanDao.getAllFreeRepairmenAsOfTime(day, time);
    }

    public List<RepairmanByCompetenceQuery> getRepairmenSortedByCompetence(Integer deviceId, Integer minimumRepairedAmount) {
        return repairmanDao.getRepairmenSortedByCompetence(deviceId, minimumRepairedAmount);
    }

    public List<RepairmanStatsQuery> getRepairmanStats() {
        return repairmanDao.getRepairmanStats();
    }
}

