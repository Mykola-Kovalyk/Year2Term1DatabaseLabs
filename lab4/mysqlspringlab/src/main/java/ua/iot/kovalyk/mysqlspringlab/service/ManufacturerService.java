package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.ManufacturerDao;
import ua.iot.kovalyk.mysqlspringlab.domain.Manufacturer;

@Service
public class ManufacturerService extends GeneralService<Manufacturer, Integer> {

    private ManufacturerDao manufacturerDao;

    @Autowired
    public ManufacturerService(ManufacturerDao manufacturerDao) {
        generalDao = this.manufacturerDao = manufacturerDao;
    }
}
