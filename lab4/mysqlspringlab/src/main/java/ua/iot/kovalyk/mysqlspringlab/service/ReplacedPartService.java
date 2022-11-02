package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.ReplacedPartDao;
import ua.iot.kovalyk.mysqlspringlab.domain.ReplacedPart;
import ua.iot.kovalyk.mysqlspringlab.domain.ReplacedPartsQuery;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReplacedPartService extends GeneralService<ReplacedPart, Integer> {

    private ReplacedPartDao replacedPartDao;

    @Autowired
    public ReplacedPartService(ReplacedPartDao replacedPartDao) {
        generalDao = this.replacedPartDao = replacedPartDao;
    }

    public List<ReplacedPartsQuery> getReplacedPartsThroughoutPeriod(Timestamp start, Timestamp end) {
        return replacedPartDao.getReplacedPartsThroughoutPeriod(start, end);
    }
}

