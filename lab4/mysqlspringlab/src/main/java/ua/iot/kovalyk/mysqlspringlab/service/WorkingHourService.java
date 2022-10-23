package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.WorkingHourDao;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHours;

import java.util.List;

@Service
public class WorkingHourService extends GeneralService<WorkingHours, Integer> {

    private WorkingHourDao workingHourDao;

    @Autowired
    public WorkingHourService(WorkingHourDao workingHourDao) {
        generalDao = this.workingHourDao = workingHourDao;
    }

    public List<WorkingHours> getRepairmanSchedule(Integer repairmanId) {
        return workingHourDao.getRepairmanSchedule(repairmanId);
    }
}

