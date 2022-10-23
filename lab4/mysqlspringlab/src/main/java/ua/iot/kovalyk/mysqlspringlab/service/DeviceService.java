package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.DeviceDao;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService extends GeneralService<Device, Integer> {

    private DeviceDao deviceDao;

    @Autowired
    public DeviceService(DeviceDao deviceDao) {
        generalDao = this.deviceDao = deviceDao;
    }


    public List<Device> getDevicesThatCanBeRepaired() {
        return deviceDao.getDevicesThatCanBeRepaired();
    }





}
