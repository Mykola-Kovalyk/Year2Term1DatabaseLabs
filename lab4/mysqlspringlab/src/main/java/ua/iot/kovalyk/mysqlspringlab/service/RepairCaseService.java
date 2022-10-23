package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.RepairCaseDao;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;

import java.util.List;

@Service
public class RepairCaseService extends GeneralService<RepairCase, Integer> {

    private RepairCaseDao repairCaseDao;

    @Autowired
    public RepairCaseService(RepairCaseDao repairCaseDao) {
        generalDao = this.repairCaseDao = repairCaseDao;
    }

    public List<RepairCase> getAllCasesForRepairman(Integer repairmanId) {
        return repairCaseDao.getAllCasesForRepairman(repairmanId);
    }
}

