package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.PartDao;
import ua.iot.kovalyk.mysqlspringlab.domain.Part;

import java.util.List;

@Service
public class PartService extends GeneralService<Part, Integer> {

    private PartDao partDao;

    @Autowired
    public PartService(PartDao partDao) {
        generalDao = this.partDao = partDao;
    }

    public List<Part> getAllPartsOfDevice(Integer deviceId) {
        return partDao.getAllPartsOfDevice(deviceId);
    }

    public List<Part> getPartsThatNeedReplenishment() {
        return partDao.getPartsThatNeedReplenishment();
    }
}

