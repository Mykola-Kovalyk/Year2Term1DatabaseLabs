package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.RepairOptionDao;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairOption;

import java.util.List;

@Service
public class RepairOptionService extends GeneralService<RepairOption, Integer> {

    private RepairOptionDao repairOptionDao;

    @Autowired
    public RepairOptionService(RepairOptionDao repairOptionDao) {
        generalDao = this.repairOptionDao = repairOptionDao;
    }

    public List<RepairOption> getAvailableOptions(Float maxPrice, Integer deviceId) {
        return repairOptionDao.getAvailableOptions(maxPrice, deviceId);
    }
}

